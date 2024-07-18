package com.APiWithMongo.payloads;

import lombok.Data;

import java.util.List;

@Data
public class RegistrationsResponse {
    private List<RegistrationDto> registration;
    private long totalItems;
    private boolean isLastPage;
    private int currentPage;
    private int totalPages;

}
