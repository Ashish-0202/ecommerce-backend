package com.ashish.E_Commerce_Mono_App.Service;

import com.ashish.E_Commerce_Mono_App.Constant.OrderStatus;
import com.ashish.E_Commerce_Mono_App.DTO.*;
import com.ashish.E_Commerce_Mono_App.Entity.*;
import com.ashish.E_Commerce_Mono_App.Event.SendMailEvent;
import com.ashish.E_Commerce_Mono_App.ExceptionHandling.OrderException;
import com.ashish.E_Commerce_Mono_App.Repository.*;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserAddressRepository addressRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private OrderTrackingRepository  orderTrackingRepository;

    private final ApplicationEventPublisher  applicationEventPublisher;

    public OrderService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Transactional
    public orders placeOrder(PlaceOrderDTO placeOrderDTO) throws MessagingException {
        //find the user
        users user = userRepository.findById(placeOrderDTO.getUser_id())
                .orElseThrow(()->new RuntimeException("User not found..!"));

        orders od1 = new orders();
        od1.setUser(user);
        od1.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        od1.setStatus(OrderStatus.placed);

        //Address details
        od1.setShipment_address(addressRepository.findAddByUserId(placeOrderDTO.getShipment_address_id(), placeOrderDTO.getUser_id()));
        BigDecimal totalAmount = BigDecimal.ZERO;

        List<order_items> orderItemList = new ArrayList<>();

        for (PlaceOrderItemsDTO itemsDTO : placeOrderDTO.getOrderItemsDTOS()){
            products p1 = productRepository.findById(itemsDTO.getProduct_id())
                    .orElseThrow(()->new RuntimeException("Product Not found"));

            order_items items = new order_items();
            items.setProduct(p1);
            items.setOrder(od1);
            items.setQuantity(itemsDTO.getQuantity());
            BigDecimal price = p1.getPrice().multiply(BigDecimal.valueOf(itemsDTO.getQuantity()));
            items.setPrice(price);
            totalAmount=totalAmount.add(price);

            orderItemList.add(items);

            //Updating quantity
            p1.setStock_avail(p1.getStock_avail()- items.getQuantity());
            productRepository.save(p1);
        }
        od1.setOrderItemsList(orderItemList);
        od1.setTotal_amount(totalAmount);
        orders placedOrder =orderRepository.save(od1);

        //Tracking entity update
        order_tracking tracking = new order_tracking();
        tracking.setOrder(placedOrder);
        tracking.setOrder_status(OrderStatus.placed);
        tracking.setDescription("Order has been placed successfully");
        orderTrackingRepository.save(tracking);

        //Email notification
        notificationService.orderPlacedNotification(placedOrder);

        //SMS Notification
        String message = String.format(
                "Dear %s, your order #%s has been placed successfully! 🎉 Expected delivery: %s. Thank you for shopping with OurStore.",
                user.getUsername(), od1.getOrder_id(), "25th Sep 2025"
        );
        String sid = smsService.sendSms("+91"+user.getPhone(),message);
        System.out.println("Message notification sent successfully SID:"+sid);
        return placedOrder;
    }

    public static OrderResponseDTO orderResponseDTO(orders order){
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setMessage("Your order has been successfully placed");
        orderResponseDTO.setOrderDate(order.getCreated_at());
        orderResponseDTO.setTotalAmount(order.getTotal_amount());

        List<OrderItemResponseDTO> orderItemResponseDTOS = getOrderItemResponseDTOS(order);
        orderResponseDTO.setOrderItemResponseDTOS(orderItemResponseDTOS);
        orderResponseDTO.setShipment_address(order.getShipment_address().getCity()+" "+order.getShipment_address().getState());
        return orderResponseDTO;
    }

    private static List<OrderItemResponseDTO> getOrderItemResponseDTOS(orders order) {
        List<OrderItemResponseDTO> orderItemResponseDTOS = new ArrayList<>();

        for(order_items items: order.getOrderItemsList()){
            OrderItemResponseDTO orderItemResponseDTO = new OrderItemResponseDTO();
            orderItemResponseDTO.setProduct_name(items.getProduct().getProduct_name());
            orderItemResponseDTO.setPrice(items.getPrice());
            orderItemResponseDTO.setQuantity(items.getQuantity());

            orderItemResponseDTOS.add(orderItemResponseDTO);
        }
        return orderItemResponseDTOS;
    }

    public List<OrderResponseDTO> getAll(){
        List<orders> ordersList = orderRepository.findAll();

        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for (orders order : ordersList){
            OrderResponseDTO responseDTO = orderResponseDTO(order);
            orderResponseDTOS.add(responseDTO);
        }
        return orderResponseDTOS;
    }

    public List<OrderResponseDTO> getUserOrders(int user_id){
        List<orders> ordersList = orderRepository.getOrderByUserId(user_id);

        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
        for (orders order : ordersList){
            OrderResponseDTO responseDTO = orderResponseDTO(order);
            orderResponseDTOS.add(responseDTO);
        }
        return orderResponseDTOS;
    }

    public OrderResponseDTO getOrderByID(int orderId){
        return orderRepository.findById(orderId)
                .map(OrderService::orderResponseDTO)
                .orElseThrow(() ->new OrderException("Order Not found with ID: "+orderId));
    }

    public void deleteById(int orderId){
        log.info("Delete order with ID :"+orderId);
        orderRepository.deleteById(orderId);
    }

    public void exceptionMethod(int orderId){
        throw new OrderException("Order Not found with ID: "+orderId);
    }

    @Transactional
    public String updateOrderStatus(OrderUpdateDTO orderUpdateDTO, int orderId){
        log.info("Updating order with ID :{}", orderId);
        orders od1 = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderException("Order Not found with ID: "+orderId));

        //setting up tracking
        order_tracking tracking = new order_tracking();
        log.info("Setting up order tracking");
        tracking.setOrder(od1);
        tracking.setOrder_status(OrderStatus.valueOf(orderUpdateDTO.getOrderStatus()));
        tracking.setDescription(orderUpdateDTO.getDescription());
        orderTrackingRepository.save(tracking);

        //updated order status
        od1.setStatus(OrderStatus.valueOf(orderUpdateDTO.getOrderStatus()));
        orderRepository.save(od1);
        log.info("Order Status updated to:{}",orderUpdateDTO.getOrderStatus());

        //Send notification
        log.info("Sending status update notification for orderID: {}",od1.getOrder_id());
        applicationEventPublisher.publishEvent(new SendMailEvent(od1));

        return "Order status updated successfully";
    }

    public OrderTrackDTO trackOrder(int orderId){
        log.info("Tracking order with ID :{}", orderId);
        orders od1 = orderRepository.findById(orderId)
                .orElseThrow(()->new OrderException("Order Not found with ID: "+orderId));

        OrderTrackDTO orderTrackDTO = new OrderTrackDTO();

        orderTrackDTO.setOrderId(od1.getOrder_id());
        orderTrackDTO.setCustomerId(od1.getUser().getUser_id());
        orderTrackDTO.setTotalPrice(od1.getTotal_amount());

        List<order_tracking> orderTracking = orderTrackingRepository.findByOrderId(orderId);

        List<TrackingDetailsDTO> trackingDetailsDTOS = new ArrayList<>();

        for (order_tracking orderTrack : orderTracking) {
            TrackingDetailsDTO trackingDetailsDTO = new TrackingDetailsDTO();
            trackingDetailsDTO.setStatus(orderTrack.getOrder_status().toString());
            trackingDetailsDTO.setLast_update(orderTrack.getUpdatedAt());
            trackingDetailsDTO.setDescription(orderTrack.getDescription());
            trackingDetailsDTOS.add(trackingDetailsDTO);
        }
        orderTrackDTO.setTrackingDetails(trackingDetailsDTOS);

        return orderTrackDTO;
    }
}
