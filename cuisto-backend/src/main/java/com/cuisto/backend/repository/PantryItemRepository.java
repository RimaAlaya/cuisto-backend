package com.cuisto.backend.repository;

import com.cuisto.backend.model.PantryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PantryItemRepository extends MongoRepository<PantryItem, String> {
    List<PantryItem> findByUserId(String userId);
    List<PantryItem> findByUserIdAndCategory(String userId, String category);
}