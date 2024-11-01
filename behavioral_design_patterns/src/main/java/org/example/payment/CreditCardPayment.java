
package org.example.payment;

public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        // TODO: Implement credit card payment logic
        System.out.println("Paid $" + amount + " with Credit Card.");
    }
}
