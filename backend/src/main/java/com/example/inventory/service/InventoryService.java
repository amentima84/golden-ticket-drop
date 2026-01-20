package com.example.inventory.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private int inventory;

    public InventoryService() {
        // Initial inventory for the MVP
        this.inventory = 100;
    }

    /**
     * Attempts to buy one item.
     * This method is synchronized to prevent race conditions
     * under concurrent requests.
     */
    public synchronized boolean buyItem() {
        if (inventory > 0) {
            inventory--;
            return true;
        }
        return false;
    }

    /**
     * Returns the current inventory count.
     */
    public synchronized int getInventory() {
        return inventory;
    }

    /**
     * Resets inventory back to the initial value.
     * This endpoint is intended for demo purposes only.
     */
    public synchronized void reset() {
        inventory = 100;
    }
}
