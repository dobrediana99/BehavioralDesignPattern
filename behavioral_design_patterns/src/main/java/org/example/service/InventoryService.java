package org.example.service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public boolean isInStock(Long productId, int quantity) {
        // Check if the inventory has enough stock for the requested product
        Inventory inventory = inventoryRepository.findByProductId(productId);
        return inventory != null && inventory.getAvailableQuantity() >= quantity;
    }
}

