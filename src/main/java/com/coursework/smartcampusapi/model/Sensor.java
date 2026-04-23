/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author mikeyibiayo
 */
package com.coursework.smartcampusapi.model;

// Represents a sensor device in the Smart Campus system.
// Each sensor belongs to a room and records environmental data.
public class Sensor {

    private String id; // Unique identifier for the sensor (e.g. TEMP-001)
    private String type; // Type of sensor (e.g. temperature, humidity, CO2)
    private String status; // Current status (e.g. ACTIVE, MAINTENANCE)
    private double currentValue; // Latest recorded value from the sensor
    private String roomId; // ID of the room this sensor is assigned to

    // Default constructor required for JSON input (deserialization)
    public Sensor() {
    }

    // Constructor used to create a sensor with all its main details
    public Sensor(String id, String type, String status, double currentValue, String roomId) {
        this.id = id; // Set sensor ID
        this.type = type; // Set sensor type
        this.status = status; // Set sensor status
        this.currentValue = currentValue; // Set latest value
        this.roomId = roomId; // Link sensor to a room
    }

    // Returns the sensor ID
    public String getId() {
        return id;
    }

    // Updates the sensor ID
    public void setId(String id) {
        this.id = id;
    }

    // Returns the sensor type
    public String getType() {
        return type;
    }

    // Updates the sensor type
    public void setType(String type) {
        this.type = type;
    }

    // Returns the sensor status
    public String getStatus() {
        return status;
    }

    // Updates the sensor status (important for logic like MAINTENANCE checks)
    public void setStatus(String status) {
        this.status = status;
    }

    // Returns the latest sensor reading value
    public double getCurrentValue() {
        return currentValue;
    }

    // Updates the latest sensor value (used when a new reading is added)
    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    // Returns the ID of the room this sensor belongs to
    public String getRoomId() {
        return roomId;
    }

    // Updates the room assignment of the sensor
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}