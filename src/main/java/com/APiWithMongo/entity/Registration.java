package com.APiWithMongo.entity;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("registration")
public class Registration {
    @Id
    private String id;

    private String name;
    private String email;
    private String mobile;

}
