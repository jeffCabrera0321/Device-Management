package com.Device.Manager.Device.Manager.Models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String manufacturer;
    private String versionNumber;
    private String description;
    private String location;

    public Device() {};

    public Device(String name, String manufacturer, String versionNumber, String description, String location){
        this.name = name;
        this.manufacturer = manufacturer;
        this.versionNumber = versionNumber;
        this.description = description;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Device: {" +
                "name: " + this.name + "\n" +
                "manufacturer: " + this.manufacturer + "\n" +
                "versionNumber: " + this.versionNumber + "\n" +
                "description: " + this.description + "\n" +
                "location" + this.location + "\n" +
                "}";
    }
}
