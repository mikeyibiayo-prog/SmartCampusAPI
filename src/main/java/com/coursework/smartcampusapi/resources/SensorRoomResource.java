/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.smartcampusapi.resources;

/**
 *
 * @author mikeyibiayo
 */

import com.coursework.smartcampusapi.model.Room;
import com.coursework.smartcampusapi.storage.DataStore;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.coursework.smartcampusapi.exceptions.RoomNotEmptyException;

// Handles all room-related operations in the API.
// This class is mapped to /api/v1/rooms.
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SensorRoomResource {

    // Handles GET requests to return all rooms.
    @GET
    public List<Room> getAllRooms() {
        // Return a list of all rooms currently stored in memory.
        return new ArrayList<>(DataStore.rooms.values());
    }

    // Handles POST requests to create a new room.
    @POST
    public Response createRoom(Room room) {

        // Validate that the request body and room ID are provided.
        if (room == null || room.getId() == null || room.getId().trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("error", "Room ID is required"))
                    .build();
        }

        // Prevent duplicate rooms with the same ID.
        if (DataStore.rooms.containsKey(room.getId())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of("error", "Room with this ID already exists"))
                    .build();
        }

        // Ensure the room has a sensor list (avoid null errors later).
        if (room.getSensorIds() == null) {
            room.setSensorIds(new ArrayList<>());
        }

        // Store the new room in memory.
        DataStore.rooms.put(room.getId(), room);

        // Return the created room with HTTP 201.
        return Response.status(Response.Status.CREATED)
                .entity(room)
                .build();
    }

    // Handles GET requests to retrieve a single room by its ID.
    @GET
    @Path("/{roomId}")
    public Response getRoomById(@PathParam("roomId") String roomId) {

        // Look up the room in the DataStore.
        Room room = DataStore.rooms.get(roomId);

        // If the room does not exist, return HTTP 404.
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Room not found"))
                    .build();
        }

        // Return the room if it exists.
        return Response.ok(room).build();
    }

    // Handles DELETE requests to remove a room.
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {

        // Look up the room in the DataStore.
        Room room = DataStore.rooms.get(roomId);

        // If the room does not exist, return HTTP 404.
        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Room not found"))
                    .build();
        }

        // Check if the room still has sensors assigned.
        // If it does, throw a custom exception (Question 5.1).
        if (room.getSensorIds() != null && !room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room " + roomId + " still has assigned sensors");
        }

        // Remove the room from the DataStore.
        DataStore.rooms.remove(roomId);

        // Return a success message confirming deletion.
        return Response.ok(Map.of(
                "message", "Room deleted successfully",
                "roomId", roomId
        )).build();
    }
}