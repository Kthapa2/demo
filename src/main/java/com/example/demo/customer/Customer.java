package com.example.demo.customer;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Customer {
    @Id
    private String id;
    private String fullname;
    private String username;
    @Indexed(unique = true)
    private String email;
    private String password;
    private LocalDate dob;
}
