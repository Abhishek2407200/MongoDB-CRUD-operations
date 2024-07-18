package com.APiWithMongo.controller;


import com.APiWithMongo.payloads.RegistrationDto;
import com.APiWithMongo.payloads.RegistrationsResponse;
import com.APiWithMongo.service.impl.RegistrationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/registrations")
public class RegistrationController {

    private RegistrationServiceImpl service;

    public RegistrationController(RegistrationServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createRegistration(@Valid @RequestBody RegistrationDto dto,BindingResult result){


        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        RegistrationDto registration = service.createRegistration(dto);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(@RequestParam String id){
        service.deleteRegistration(id);
        return new ResponseEntity<>("Registration deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateRegistration(@Valid @RequestBody RegistrationDto dto, @RequestParam String id, BindingResult result){


        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.NOT_ACCEPTABLE);
        }

        RegistrationDto updatedRegistration = service.updateRegistration(dto, id);
        return new ResponseEntity<>(updatedRegistration, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<RegistrationsResponse> getAllRegistrations(
            @RequestParam(name = "pageNo", defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(name = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        RegistrationsResponse allRegistration = service.getAllRegistration(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allRegistration, HttpStatus.OK);
    }

    @GetMapping("/byId")
    public ResponseEntity<RegistrationDto> getRegistrationById(@RequestParam String id){
        RegistrationDto registration = service.getRegistrationById(id);
        return new ResponseEntity<>(registration, HttpStatus.OK);
    }

    
}
