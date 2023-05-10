package com.example.fitcrm.services;

import com.example.fitcrm.exceptions.CustomerAlreadyExistsException;
import com.example.fitcrm.exceptions.ResourceNotFoundException;
import com.example.fitcrm.models.Customer;
import com.example.fitcrm.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomerServices {

    private final CustomerRepository customerRepository;
    private JavaMailSender mailSender;


    @Transactional
    public Customer createCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerByDocumentNumber(customer.getDocumentNumber());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("A customer with document number " + customer.getDocumentNumber() + " already exists.");
        }
        return customerRepository.insert(customer);
    }


    public Customer updateCustomer(String id, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerById(id);
        if (existingCustomer.isPresent()) {
            customer.setId(id);
            customerRepository.deleteCustomerById(id);
            return customerRepository.insert(customer);
        } else {
            throw new ResourceNotFoundException("Customer not found with id " + id);
        }
    }


    public void deleteCustomer(String id) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerById(id);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteCustomerById(id);
        } else {
            throw new ResourceNotFoundException("Customer not found with id " + id);
        }
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findCustomerById(id);
    }


    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findCustomerByEmail(email);
    }


    public Optional<Customer> getCustomerByDocumentNumber(Long documentNumber) {
        return customerRepository.findCustomerByDocumentNumber(documentNumber);
    }


    public void sendReminderRemainingDays() {
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            if (customer.getRemainingDays() <= 5 && customer.getRemainingDays() >= 0) {
                sendReminderMail(customer);
            }
        }
    }


    public void sendReminderMail(Customer customer) {
        String addressee = customer.getEmail();
        String subject = "Remaining days in your gym plan!";
        String messageBody = "You have " + customer.getRemainingDays() + " days left. Remember to renew your plan";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fitcrm.empresariales@gmail.com");
        message.setTo(addressee);
        message.setSubject(subject);
        message.setText(messageBody);
        mailSender.send(message);

    }


    public void decreaseRemainingDays() {
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            int remainingDays = customer.getRemainingDays();
            if (remainingDays > 0) {
                customer.setRemainingDays(remainingDays - 1);
                customerRepository.insert(customer);
            }
        }
    }


    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleDecreaseDaysRemaining() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::decreaseRemainingDays, 0, 1, TimeUnit.DAYS);
    }
}
