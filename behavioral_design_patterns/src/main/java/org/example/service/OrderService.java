package org.example.service;

import org.example.Order;
import org.example.payment.PaymentStrategy;
import org.example.repository.OrderRepository;

@Service
public class OrderService {
    private PaymentStrategy paymentStrategy;
    @Autowired
    private OrderRepository orderRepository;

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }

    public void placeOrder(Order order) {
        order.setStatus(OrderStatus.PLACED);
        orderRepository.save(order);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean processOrderPayment(double amount) {
        return paymentStrategy.pay(amount);
    }
}
