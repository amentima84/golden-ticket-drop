package com.example.inventory.controller;

import com.example.inventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public int inventory() {
    System.out.println("INVENTORY ENDPOINT HIT");
    return inventoryService.getInventory();
}

    @PostMapping("/buy")
    public ResponseEntity<String> buy() {
        boolean success = inventoryService.buyItem();

        if (success) {
            return ResponseEntity.ok("Purchase successful");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Sold out");
    }

    @PostMapping("/reset")
    public void reset() {
    inventoryService.reset();
}
}