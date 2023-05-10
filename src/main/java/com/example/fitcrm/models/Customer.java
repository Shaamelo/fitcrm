package com.example.fitcrm.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Data
@Document(collection = "fitcrm_customers")
public class Customer {
    @Id
    private String id;
    @NotBlank(message = "Name is mandatory")
    @Length(min = 3, message = "Name should have at least 3 characters")
    private String name;
    @NotBlank(message = "Document Type is mandatory")
    private String documentType;
    @Positive(message = "Document number is mandatory")
    @Indexed(unique = true)
    private long documentNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private int age;
    private String address;
    @NotBlank(message = "Email is mandatory")
    @Email
    @Indexed(unique = true)
    private String email;
    @PositiveOrZero(message = "Phone is mandatory")
    private long phone;
    @PositiveOrZero(message = "Remaining days are mandatory")
    private int remainingDays;
    private EmergencyContact emergencyContact;
    private MedicInformation medicInformation;

    public Customer(String name, String documentType, long documentNumber, Date birthDate, String address, String email, long phone, int remainingDays, EmergencyContact emergencyContact, MedicInformation medicInformation) {
        this.name = name;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.birthDate = birthDate;
        this.age = calculateAge(this.birthDate);
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.remainingDays = remainingDays;
        this.emergencyContact = emergencyContact;
        this.medicInformation = medicInformation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public long getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(long documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.age = calculateAge(birthDate);
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = calculateAge(getBirthDate());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public EmergencyContact getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public MedicInformation getMedicInformation() {
        return medicInformation;
    }

    public void setMedicInformation(MedicInformation medicInformation) {
        this.medicInformation = medicInformation;
    }

    private int calculateAge(Date birthDate) {
        LocalDate birthD = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthD, now);
        return period.getYears();
    }
}

