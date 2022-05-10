package com.example.demo.property;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    public Map<String, Object> getProperties(){
        Map<String, Object> map = new HashMap<>();
        map.put("status",200);
        map.put("body",propertyRepository.findAll());
        map.put("message","Success");
        return map;
    }

    public Map<String, Object> getFeaturedProperties(){
        Map<String, Object> map = new HashMap<>();
        map.put("status",200);
        map.put("body",propertyRepository.featuredProperty());
        map.put("message","Success");
        return map;
    }
    public Map<String, Object> createParticularProperty(Property data){
        Map<String, Object> map = new HashMap<>();

        propertyRepository.findByTitle(data.getTitle())
                .ifPresentOrElse(u -> {
                    System.out.println("Property already exists");
                    map.put("status", 401);
                    map.put("message", "Property Already Exists");


                }, () -> {
                    propertyRepository.insert(data);
                    map.put("status", 200);
                    map.put("message", "Property Inserted");

                    System.out.println("Property Inserted");
                });
        return map;
    }

    public Map<String, Object> getParticularProperty(String id) {
        Map<String, Object> map = new HashMap<>();
        propertyRepository.findById(id).ifPresentOrElse((u)-> {
            map.put("status", 200);
            map.put("body", propertyRepository.findById(id));
            map.put("message", "Success");
        }, () -> {
            map.put("status", 400);
            map.put("body", "");
            map.put("message", "Property not found");
        });
        return map;
    }

    public Map<String, Object> updateParticularProperty(Property data, String id){
        Map<String, Object> map = new HashMap<>();
        propertyRepository.findById(id)
                .ifPresentOrElse(u -> {
                    data.setId(id);
                    propertyRepository.save(data);
                    System.out.println("Updated Successfully");
                    map.put("status", 200);
                    map.put("message", "Updated Successfully");
                }, () -> {


                    map.put("status", 401);
                    map.put("message", "Property doesnot exists");

                    System.out.println("Property doesnot exists");
                });;
        return map;
    }
    public Map<String, Object> deleteParticularProperty(String id){
        Map<String, Object> map = new HashMap<>();
        propertyRepository.findById(id).ifPresentOrElse((u)->{
            propertyRepository.deleteById(id);
            map.put("status", 200);
            map.put("body", "");
            map.put("message", "successfully deleted");
        }, () -> {
            map.put("status", 401);
            map.put("body", "");
            map.put("message", "Property doesnot exists");
        });

        return map;
    }

    public Map<String, Object> searchProperty(String queryD){
        Map<String, Object> map = new HashMap<>();

        List<Property> searchData = propertyRepository.searchPropertyByTitle(queryD);
        map.put("query", searchData);
        return map;
    }

}
