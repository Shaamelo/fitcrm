package com.example.fitcrm.models;

import lombok.Data;

@Data
public class EmergencyContact {
    private String name;
    private String email;
    private String phone;
    private String relationship;

    public EmergencyContact() {}

    public EmergencyContact(String name, String email, String phone, String relationship) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
