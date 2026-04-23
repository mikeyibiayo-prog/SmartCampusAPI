/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.smartcampusapi.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mikeyibiayo
 */

// Represents a room in the Smart Campus system.
// Each room can contain multiple sensors.
public class Room {

    private String id; // Unique identifier for the room (e.g. LAB-101)
    private String name; // Human-readable name of the room
    private int capacity; // Maximum number of people the room can hold

    // List of sensor IDs assigned to this room
    // Used to track which sensors belong to the room
    private List<String> sensorIds = new ArrayList<>();

    // Default constructor required for JSON deserialization
    public Room() {
    }

    // Constructor used when creating a room without existing sensors
    public Room(String id, String name, int capacity) {
        this.id = id; // Set room ID
        this.name = name; // Set room name
        this.capacity = capacity; // Set capacity
        this.sensorIds = new ArrayList<>(); // Start with an empty sensor list
    }

    // Constructor used when all data including sensors is already known
    public Room(String id, String name, int capacity, List<String> sensorIds) {
        this.id = id; // Set room ID
        this.name = name; // Set room name
        this.capacity = capacity; // Set capacity
        this.sensorIds = sensorIds; // Set existing sensor list
    }

    // Returns the room ID
    public String getId() {
        return id;
    }

    // Updates the room ID
    public void setId(String id) {
        this.id = id;
    }

    // Returns the room name
    public String getName() {
        return name;
    }

    // Updates the room name
    public void setName(String name) {
        this.name = name;
    }

    // Returns the room capacity
    public int getCapacity() {
        return capacity;
    }

    // Updates the room capacity
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // Returns the list of sensor IDs assigned to this room
    public List<String> getSensorIds() {
        return sensorIds;
    }

    // Replaces the list of sensor IDs
    public void setSensorIds(List<String> sensorIds) {
        this.sensorIds = sensorIds;
    }
}