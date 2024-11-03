package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Getters and setters
    @Getter
    private String paymentMethod;
    @Getter
    private String cardNumber; // Or other necessary payment fields

    // Constructor
    public PaymentDetails(String paymentMethod, String cardNumber) {
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
