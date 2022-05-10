package com.example.demo.property;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends MongoRepository<Property, String> {
    Optional<Property> findByTitle(String email);

    @Query("{'isFeatured':true}")
    List<Property> featuredProperty();
    @Query("{$or :[{ 'title' : { $regex: ?0 }},{'type':{ $regex: ?0 }}] }")
    List<Property> searchPropertyByTitle(String regexp);



}
