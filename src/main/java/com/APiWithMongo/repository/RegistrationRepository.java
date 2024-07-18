package com.APiWithMongo.repository;

import com.APiWithMongo.entity.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RegistrationRepository extends MongoRepository<Registration,String> {
}
