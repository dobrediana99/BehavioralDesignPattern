package org.example.service;

import org.example.Order;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public boolean validatePayment(Order order) {
        // Validate the payment (e.g., check card details, balance, etc.)
        // This is a stub method; implement payment validation logic here
        return order.getPaymentDetails() != null && order.getPaymentDetails().isValid();
    }
}

