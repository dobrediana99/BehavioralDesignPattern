
package org.example.handler;

import org.example.Order;

public class InventoryCheckHandler extends OrderValidationHandler {
    @Autowired
    private InventoryService inventoryService; // Assume this service checks stock availability

    @Override
    public void validate(Order order) {
        // Check inventory for each item in the order
        boolean inventoryAvailable = order.getItems().stream()
                .allMatch(item -> inventoryService.isInStock(item.getProductId(), item.getQuantity()));

        if (!inventoryAvailable) {
            // Insufficient inventory, halt validation and throw an exception
            throw new IllegalStateException("Insufficient inventory for order: " + order.getId());
        }

        // Log successful inventory check
        System.out.println("Inventory check passed for order: " + order.getId());

        // Call the next handler in the chain if validation passes
        if (nextHandler != null) {
            nextHandler.validate(order);
        }
    }
}
