package com.APiWithMongo.service.impl;

import com.APiWithMongo.entity.Registration;
import com.APiWithMongo.exception.ResourceNotFoundException;
import com.APiWithMongo.payloads.RegistrationDto;
import com.APiWithMongo.payloads.RegistrationsResponse;
import com.APiWithMongo.repository.RegistrationRepository;
import com.APiWithMongo.service.RegistrationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private RegistrationRepository repository;

    public RegistrationServiceImpl(RegistrationRepository repository) {
        this.repository = repository;
    }

    @Override
    public RegistrationDto createRegistration(RegistrationDto registration) {
        Registration savedRegistration = repository.save(mapToEntity(registration));
        return mapToDto(savedRegistration);
    }

    @Override
    public void deleteRegistration(String id) {
        repository.deleteById(id);
    }

    @Override
    public RegistrationDto updateRegistration(RegistrationDto registration,String id) {
        Registration reg = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find registration with id " + id));
        reg.setName(registration.getName());
        reg.setEmail(registration.getEmail());
        reg.setMobile(registration.getMobile());
        Registration updatedRegistration = repository.save(reg);
        return mapToDto(updatedRegistration);
    }

    @Override
    public RegistrationsResponse getAllRegistration(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);

        Pageable p = PageRequest.of(pageNo,pageSize,sort);

        Page<Registration> all = repository.findAll(p);
        List<Registration> registrations = all.getContent();
        List<RegistrationDto> dtos = registrations.stream().map(r -> mapToDto(r)).collect(Collectors.toList());

        RegistrationsResponse response = new RegistrationsResponse();
        response.setRegistration(dtos);
        response.setLastPage(all.isLast());
        response.setTotalItems(all.getTotalElements());
        response.setTotalPages(all.getTotalPages());
        response.setCurrentPage(p.getPageNumber());

        return response;
    }

    @Override
    public RegistrationDto getRegistrationById(String id) {
        Registration registration = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find registration with id : " + id));
        return mapToDto(registration);
    }

    private Registration mapToEntity(RegistrationDto dto){
        Registration registration = new Registration();
        registration.setName(dto.getName());
        registration.setEmail(dto.getEmail());
        registration.setMobile(dto.getMobile());
        return registration;
    }

    private RegistrationDto  mapToDto(Registration registration){
        RegistrationDto dto = new RegistrationDto();
        dto.setId(registration.getId());
        dto.setName(registration.getName());
        dto.setEmail(registration.getEmail());
        dto.setMobile(registration.getMobile());
        return dto;
    }
}
