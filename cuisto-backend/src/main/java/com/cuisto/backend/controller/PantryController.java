package com.cuisto.backend.controller;

import com.cuisto.backend.model.PantryItem;
import com.cuisto.backend.service.PantryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pantry")
@RequiredArgsConstructor
public class PantryController {

    private final PantryService pantryService;

    @GetMapping
    public ResponseEntity<List<PantryItem>> getMyPantryItems(Authentication authentication) {
        String userId = authentication.getName();
        return ResponseEntity.ok(pantryService.getUserPantryItems(userId));
    }

    @PostMapping
    public ResponseEntity<PantryItem> addPantryItem(
            @RequestBody PantryItem item,
            Authentication authentication) {
        String userId = authentication.getName();
        return ResponseEntity.ok(pantryService.addPantryItem(item, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PantryItem> updatePantryItem(
            @PathVariable String id,
            @RequestBody PantryItem item) {
        return ResponseEntity.ok(pantryService.updatePantryItem(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePantryItem(@PathVariable String id) {
        pantryService.deletePantryItem(id);
        return ResponseEntity.noContent().build();
    }
}