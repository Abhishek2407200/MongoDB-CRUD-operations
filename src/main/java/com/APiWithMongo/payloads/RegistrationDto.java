package com.APiWithMongo.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDto {
    private String id;

    @Size(min = 3, message = "Name Should be More Than Two characters")
    private String name;

    @Email(message = "Please Enter a valid Email Address")
    private String email;

    @Size(min = 10,max = 10, message = "Mobile Number Should be 10 digits")
    private String mobile;

}
