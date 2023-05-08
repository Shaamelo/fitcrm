package com.example.fitcrm.models;

import lombok.Data;

import java.util.List;

@Data
public class MedicInformation {
    private double weight;
    private double height;
    private String gender;
    private List<String> diseases;

    public MedicInformation() {}

    public MedicInformation(double weight, double height, String gender, List<String> diseases) {
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.diseases = diseases;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<String> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<String> diseases) {
        this.diseases = diseases;
    }
}
