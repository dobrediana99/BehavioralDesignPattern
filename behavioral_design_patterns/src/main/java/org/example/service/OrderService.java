package org.example.service;

import org.example.model.Order;
import org.example.model.OrderStatus;
import org.example.payment.PaymentStrategy;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private PaymentStrategy paymentStrategy;
    @Autowired
    private OrderRepository orderRepository;

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public void placeOrder(Order order) {
        order.setStatus(String.valueOf(OrderStatus.PLACED));
        orderRepository.save(order);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean processOrderPayment(double amount) {
        return paymentStrategy.pay(amount);
    }
}
