package com.APiWithMongo.payloads;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {
    private String message;
    private Date date;
    public ErrorDetails(String message) {
        this.message = message;
        this.date = new Date();
    }

}
