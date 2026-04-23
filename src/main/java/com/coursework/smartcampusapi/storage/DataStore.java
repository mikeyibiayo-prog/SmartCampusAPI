/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.smartcampusapi.storage;

import com.coursework.smartcampusapi.model.Room;
import com.coursework.smartcampusapi.model.Sensor;
import com.coursework.smartcampusapi.model.SensorReading;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Acts as an in-memory database for the API.
// Stores all rooms, sensors, and sensor readings during runtime.
public class DataStore {

    // Stores all rooms using the room ID as the key
    public static Map<String, Room> rooms = new HashMap<>();

    // Stores all sensors using the sensor ID as the key
    public static Map<String, Sensor> sensors = new HashMap<>();

    // Stores sensor readings grouped by sensor ID
    // Each sensor ID maps to a list of its readings (history)
    public static Map<String, List<SensorReading>> sensorReadings = new HashMap<>();

    // Static block runs once when the application starts
    static {
        // Create sample room objects so the API has initial data
        Room room1 = new Room("LAB-301", "Cavendish Room", 40);
        Room room2 = new Room("LAB-101", "Marylebone Room", 30);

        // Add the sample rooms into the rooms map
        rooms.put(room1.getId(), room1);
        rooms.put(room2.getId(), room2);
    }
}