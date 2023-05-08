package com.example.fitcrm.controllers;

import com.example.fitcrm.models.Customer;
import com.example.fitcrm.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
    @Autowired
    private CustomerServices customerServices;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerServices.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Optional<Customer> customerOptional = customerServices.getCustomerById(id);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/document-number/{documentNumber}")
    public ResponseEntity<Customer> getCustomerByDocumentNumber(@PathVariable Long documentNumber) {
        Optional<Customer> customerOptional = customerServices.getCustomerByDocumentNumber(documentNumber);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customerOptional = customerServices.getCustomerByEmail(email);
        if (customerOptional.isPresent()) {
            return ResponseEntity.ok(customerOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerServices.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerServices.getCustomerById(id);
        if (customerOptional.isPresent()) {
            Customer updatedCustomer = customerServices.updateCustomer(id, customer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id) {
        Optional<Customer> customerOptional = customerServices.getCustomerById(id);
        if (customerOptional.isPresent()) {
            customerServices.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void sendReminderMails() {
        customerServices.sendReminderRemainingDays();
    }
}
