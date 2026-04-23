/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mikeyibiayo
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mikeyibiayo
 */

package com.coursework.smartcampusapi.resources;

import com.coursework.smartcampusapi.model.Sensor;
import com.coursework.smartcampusapi.model.SensorReading;
import com.coursework.smartcampusapi.storage.DataStore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.coursework.smartcampusapi.exceptions.SensorUnavailableException;

// Sub-resource class that handles reading data for a specific sensor.
// This is accessed via /sensors/{sensorId}/readings
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorReadingResource {

    private String sensorId; // Stores the ID of the sensor this resource is linked to

    // Constructor receives the sensorId from the parent resource (SensorResource)
    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    // Handles GET requests to retrieve all readings for a specific sensor
    @GET
    public Response getAllReadings() {

        // Look up the sensor to ensure it exists
        Sensor sensor = DataStore.sensors.get(sensorId);

        // If the sensor does not exist, return HTTP 404
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Sensor not found"))
                    .build();
        }

        // Retrieve the list of readings for this sensor
        List<SensorReading> readings = DataStore.sensorReadings.get(sensorId);

        // If no readings exist yet, return an empty list instead of null
        if (readings == null) {
            readings = new ArrayList<>();
        }

        // Return the list of readings as JSON
        return Response.ok(readings).build();
    }

    // Handles POST requests to add a new reading for the sensor
    @POST
    public Response addReading(SensorReading reading) {

        // Look up the sensor to ensure it exists
        Sensor sensor = DataStore.sensors.get(sensorId);

        // If the sensor does not exist, return HTTP 404
        if (sensor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Sensor not found"))
                    .build();
        }

        // Check if the sensor is in MAINTENANCE mode
        // If so, do not allow new readings to be added
        if ("MAINTENANCE".equalsIgnoreCase(sensor.getStatus())) {
            throw new SensorUnavailableException("Sensor " + sensorId + " is in maintenance and cannot accept readings");
        }

        // Validate the incoming reading object
        if (reading == null || reading.getId() == null || reading.getId().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Reading ID is required"))
                    .build();
        }

        // Get the existing list of readings for this sensor
        List<SensorReading> readings = DataStore.sensorReadings.get(sensorId);

        // If no list exists yet, create a new one and store it
        if (readings == null) {
            readings = new ArrayList<>();
            DataStore.sensorReadings.put(sensorId, readings);
        }

        // Add the new reading to the sensor's history
        readings.add(reading);

        // Update the sensor's current value to match the latest reading
        // This keeps the real-time value consistent with the history
        sensor.setCurrentValue(reading.getValue());

        // Return the created reading with HTTP 201
        return Response.status(Response.Status.CREATED)
                .entity(reading)
                .build();
    }
}