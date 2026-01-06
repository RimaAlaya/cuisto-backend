package com.cuisto.backend.service;

import com.cuisto.backend.model.PantryItem;
import com.cuisto.backend.repository.PantryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PantryService {

    private final PantryItemRepository pantryItemRepository;

    public List<PantryItem> getUserPantryItems(String userId) {
        return pantryItemRepository.findByUserId(userId);
    }

    public PantryItem addPantryItem(PantryItem item, String userId) {
        item.setUserId(userId);
        return pantryItemRepository.save(item);
    }

    public PantryItem updatePantryItem(String id, PantryItem item) {
        PantryItem existing = pantryItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pantry item not found"));

        existing.setIngredientName(item.getIngredientName());
        existing.setCategory(item.getCategory());
        existing.setAmount(item.getAmount());
        existing.setUnit(item.getUnit());
        existing.setExpirationDate(item.getExpirationDate());

        return pantryItemRepository.save(existing);
    }

    public void deletePantryItem(String id) {
        pantryItemRepository.deleteById(id);
    }
}