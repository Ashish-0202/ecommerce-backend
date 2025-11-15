package com.ashish.E_Commerce_Mono_App.Controller;

import com.ashish.E_Commerce_Mono_App.DTO.OrderResponseDTO;
import com.ashish.E_Commerce_Mono_App.DTO.OrderTrackDTO;
import com.ashish.E_Commerce_Mono_App.DTO.OrderUpdateDTO;
import com.ashish.E_Commerce_Mono_App.DTO.PlaceOrderDTO;
import com.ashish.E_Commerce_Mono_App.Entity.orders;
import com.ashish.E_Commerce_Mono_App.Service.OrderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody PlaceOrderDTO placeOrderDTO) throws MessagingException {
        orders od1 = orderService.placeOrder(placeOrderDTO);
        OrderResponseDTO responseDTO = orderService.orderResponseDTO(od1);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/view/user/{user_id}")
    public ResponseEntity<List<OrderResponseDTO>> getOrderByUser(@PathVariable("user_id") int user_id){
        return ResponseEntity.ok(orderService.getUserOrders(user_id));
    }

    @GetMapping("/byId/{orderID}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable("orderID") int orderId){
        return ResponseEntity.ok(orderService.getOrderByID(orderId));
    }

    @PostMapping("/{id}/updateStatus")
    public ResponseEntity<String> updateOrderStatus(@PathVariable("id") int orderId,@RequestBody OrderUpdateDTO orderUpdateDTO){
        return ResponseEntity.ok(orderService.updateOrderStatus(orderUpdateDTO,orderId));
    }

    @GetMapping("/{id}/track")
    public ResponseEntity<OrderTrackDTO> trackOrder(@PathVariable("id") int orderId){
        return new ResponseEntity<>(orderService.trackOrder(orderId), HttpStatus.OK);
    }
}
