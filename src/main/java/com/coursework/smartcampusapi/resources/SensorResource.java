package com.coursework.smartcampusapi.resources;

/**
 *
 * @author mikeyibiayo
 */


import com.coursework.smartcampusapi.model.Room;
import com.coursework.smartcampusapi.model.Sensor;
import com.coursework.smartcampusapi.storage.DataStore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.coursework.smartcampusapi.exceptions.LinkedResourceNotFoundException;

// Handles all top-level sensor operations in the API.
// This class is mapped to /api/v1/sensors.
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorResource {

    // Handles GET requests to return all sensors,
    // or only sensors of a specific type if a query parameter is provided.
    @GET
    public List<Sensor> getAllSensors(@QueryParam("type") String type) {

        // Create a list to store the sensors that match the request.
        List<Sensor> result = new ArrayList<>();

        // Loop through every sensor currently stored in memory.
        for (Sensor sensor : DataStore.sensors.values()) {

            // If no type filter is given, return every sensor.
            if (type == null || type.isEmpty()) {
                result.add(sensor);
            }
            // If a type filter is given, only return sensors with a matching type.
            else if (sensor.getType().equalsIgnoreCase(type)) {
                result.add(sensor);
            }
        }

        // Return the final list of matching sensors.
        return result;
    }

    // Handles POST requests to create a new sensor.
    @POST
    public Response createSensor(Sensor sensor) {

        // Validate that the request body exists and includes a sensor ID.
        if (sensor == null || sensor.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Sensor ID required"))
                    .build();
        }

        // Prevent duplicate sensors from being created with the same ID.
        if (DataStore.sensors.containsKey(sensor.getId())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("error", "Sensor already exists"))
                    .build();
        }

        // Look up the room that the sensor is supposed to belong to.
        Room room = DataStore.rooms.get(sensor.getRoomId());

        // If the room does not exist, throw a custom exception.
        // This is used for the linked resource validation in Question 5.2.
        if (room == null) {
            throw new LinkedResourceNotFoundException("Room " + sensor.getRoomId() + " does not exist");
        }

        // Add the sensor into the sensors map.
        DataStore.sensors.put(sensor.getId(), sensor);

        // Also add the sensor ID into the room's sensor list
        // so the relationship is stored on both sides.
        room.getSensorIds().add(sensor.getId());

        // Return the created sensor with HTTP 201.
        return Response.status(Response.Status.CREATED)
                .entity(sensor)
                .build();
    }

    // Sub-resource locator for reading-related paths.
    // Sends requests like /sensors/{sensorId}/readings to SensorReadingResource.
    @Path("/{sensorId}/readings")
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReadingResource(sensorId);
    }
}