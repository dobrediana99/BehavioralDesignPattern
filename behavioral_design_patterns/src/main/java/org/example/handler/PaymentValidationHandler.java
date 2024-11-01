
package org.example.handler;

import org.example.Order;

public class PaymentValidationHandler extends OrderValidationHandler {
    @Override
    public void validate(Order order) {
        // TODO: Validate payment details
        System.out.println("Payment validation passed.");
        super.validate(order);
    }
}
