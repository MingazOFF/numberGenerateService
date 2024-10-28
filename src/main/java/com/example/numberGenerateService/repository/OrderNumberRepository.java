package com.example.numberGenerateService.repository;

import com.example.numberGenerateService.entity.OrderNumber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface OrderNumberRepository extends MongoRepository<OrderNumber, UUID> {
    Optional<OrderNumber> findByValue(String value);
}
