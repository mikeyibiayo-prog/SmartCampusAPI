/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.smartcampusapi.model;

// Represents a single reading recorded by a sensor.
// Used to store historical data rather than just the current value.
public class SensorReading {

    private String id; // Unique identifier for the reading
    private long timestamp; // Time the reading was taken (stored as a long value)
    private double value; // Actual sensor reading value (e.g. temperature)

    // Default constructor required for JSON deserialization
    public SensorReading() {
    }

    // Constructor used when creating a new reading with full details
    public SensorReading(String id, long timestamp, double value) {
        this.id = id; // Set reading ID
        this.timestamp = timestamp; // Set time of reading
        this.value = value; // Set reading value
    }

    // Returns the reading ID
    public String getId() {
        return id;
    }

    // Updates the reading ID
    public void setId(String id) {
        this.id = id;
    }

    // Returns the timestamp of the reading
    public long getTimestamp() {
        return timestamp;
    }

    // Updates the timestamp
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // Returns the recorded value
    public double getValue() {
        return value;
    }

    // Updates the recorded value
    public void setValue(double value) {
        this.value = value;
    }
}