package com.APiWithMongo.payloads;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetails {
    private final String message;
    private final Date date;
    public ErrorDetails(String message) {
        this.message = message;
        this.date = new Date();
    }

}
