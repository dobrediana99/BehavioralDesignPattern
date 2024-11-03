package org.example.model;

public class PaymentDetails {

    private String paymentMethod;
    private String cardNumber; // Or other necessary payment fields

    // Constructor
    public PaymentDetails(String paymentMethod, String cardNumber) {
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
    }

    // Getters and setters
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
