package com.cuisto.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "pantry_items")
public class PantryItem {
    @Id
    private String id;

    private String userId;
    private String ingredientName;
    private String category; // "vegetables", "meat", "dairy", "spices", etc.
    private String amount;
    private String unit;

    private LocalDate expirationDate;
    private LocalDate addedDate = LocalDate.now();
}