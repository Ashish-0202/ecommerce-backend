package com.ashish.E_Commerce_Mono_App.Service;

import com.ashish.E_Commerce_Mono_App.Constant.OrderStatus;
import com.ashish.E_Commerce_Mono_App.DTO.OrderResponseDTO;
import com.ashish.E_Commerce_Mono_App.Entity.*;
import com.ashish.E_Commerce_Mono_App.ExceptionHandling.OrderException;
import com.ashish.E_Commerce_Mono_App.Repository.OrderRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderService orderService;

    @BeforeAll
    public static void init() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    public void initBeforeTest(){
        System.out.println("BeforeEach");
    }

    @Test
    void myFirstTest(){
        System.out.println("My First Unit Test");
    }

    @Test
    void getOrderByIDShouldGetOrderInfoSuccessfully(){
        int orderId = 13;

        //creating user
        users u1= new users();
        u1.setUser_id(1);
        u1.setEmail("email");
        u1.setPassword("password");
        u1.setPhone(223344556);

       //Creating mock order object
        orders mockOrder = new orders();
        mockOrder.setOrder_id(orderId);
        mockOrder.setStatus(OrderStatus.placed);
        mockOrder.setTotal_amount(BigDecimal.valueOf(1113.11));

        List<order_items> orderItems = new ArrayList<>();
        orderItems.add(new order_items(1,mockOrder,new products(1,"pen","pen",BigDecimal.valueOf(111.11),1,new category(1,"station","station"), Timestamp.valueOf(LocalDateTime.now())),1,BigDecimal.valueOf(1111.11)));
        mockOrder.setOrderItemsList(orderItems);
        mockOrder.setShipment_address(new addressInfo(1,"door","street","city","State","country",112233,u1));

        //Mock Repo call
        Mockito.when(orderRepository.findById(13)).thenReturn(Optional.of(mockOrder));

        //Act
        OrderResponseDTO orderResponseDTO = orderService.getOrderByID(orderId);

        assertNotNull(orderResponseDTO);
        assertEquals(BigDecimal.valueOf(1113.11),orderResponseDTO.getTotalAmount());
        assertEquals("city State",orderResponseDTO.getShipment_address());

        System.out.println("In Second test case");
    }

    @Test
    public void deleteOrderById(){
        int orderId=10;
        //Mock
        Mockito.doNothing().when(orderRepository).deleteById(orderId);

        //Act
        orderService.deleteById(orderId);

        //Verification for void methods
        Mockito.verify(orderRepository,Mockito.times(1)).deleteById(orderId);
    }

    @Test
    public void testExceptionMethod(){
        //assert
        OrderException orderException= assertThrows(OrderException.class,()->{
            orderService.exceptionMethod(10);
        });
        assertEquals("Order Not found with ID: 10",orderException.getMessage());
        System.out.println("testExceptionMethod");
    }

    @AfterEach
    public void initAfterTest(){
        System.out.println("initAfterTest");
    }

    @AfterAll
    public static void initAfterAll(){
        System.out.println("initAfterAll");
    }
}
