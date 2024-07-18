package com.APiWithMongo.service;

import com.APiWithMongo.payloads.RegistrationDto;
import com.APiWithMongo.payloads.RegistrationsResponse;

import java.util.List;

public interface RegistrationService {

    //create new registration
    RegistrationDto createRegistration(RegistrationDto registration);

    //delete registration
    void deleteRegistration(String id);

    //update registration
    RegistrationDto updateRegistration(RegistrationDto registration ,String id);

    //get allRegistration
    RegistrationsResponse getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir);

    RegistrationDto getRegistrationById(String id);
}
