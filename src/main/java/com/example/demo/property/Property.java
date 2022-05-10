package com.example.demo.property;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Property {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private String description;
    private String price;
    private String type;
    private String houserules;
    private String amenities;
    private String location;
    private Boolean isFeatured;
}
