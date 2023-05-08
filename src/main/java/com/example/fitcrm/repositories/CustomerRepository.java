package com.example.fitcrm.repositories;

import com.example.fitcrm.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findCustomerByDocumentNumber(Long documentNumber);

    Optional<Customer> findCustomerByEmail(String email);

    Optional<Customer> findCustomerById(String id);

    void deleteCustomerById(String id);
}
