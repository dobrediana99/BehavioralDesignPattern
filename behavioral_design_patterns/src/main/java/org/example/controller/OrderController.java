package org.example.controller;

import org.example.dto.OrderDTO;
import org.example.dto.PaymentDetailsDTO;
import org.example.dto.ItemDTO;
import org.example.model.Order;
import org.example.model.PaymentDetails;
import org.example.model.Item;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService; // Assume you have a service for business logic

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(toDTO(order)); // Convert entity to DTO
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = toEntity(orderDTO); // Convert DTO to entity
        Order createdOrder = orderService.placeOrder(order);
        return ResponseEntity.ok(toDTO(createdOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = toEntity(orderDTO); // Convert DTO to entity
        Order updatedOrder = orderService.updateOrder(id, order);
        return ResponseEntity.ok(toDTO(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    private OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCustomerName(order.getCustomerName());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalAmount(order.getTotalAmount());

        // Mapping payment details
        PaymentDetailsDTO paymentDetailsDTO = new PaymentDetailsDTO();
        paymentDetailsDTO.setPaymentMethod(order.getPaymentDetails().getPaymentMethod());
        paymentDetailsDTO.setCardNumber(order.getPaymentDetails().getCardNumber());
        orderDTO.setPaymentDetails(paymentDetailsDTO);

        // Mapping items
        List<ItemDTO> items = order.getItems().stream()
                .map(item -> {
                    ItemDTO itemDTO = new ItemDTO();
                    itemDTO.setProductId(item.getProductId());
                    itemDTO.setQuantity(item.getQuantity());
                    return itemDTO;
                }).collect(Collectors.toList());
        orderDTO.setItems(items);

        return orderDTO;
    }

    private Order toEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setStatus(orderDTO.getStatus());
        order.setTotalAmount(orderDTO.getTotalAmount());

        // Mapping payment details
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setPaymentMethod(String.valueOf(orderDTO.getPaymentDetails()));
        paymentDetails.setCardNumber(String.valueOf(orderDTO.getPaymentDetails()));
        order.setPaymentDetails(paymentDetails);

        // Mapping items
        List<Item> items = orderDTO.getItems().stream()
                .map(itemDTO -> {
                    Item item = new Item();
                    item.setProductId(itemDTO.getProductId());
                    item.setQuantity(itemDTO.getQuantity());
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);

        return order;
    }
}
