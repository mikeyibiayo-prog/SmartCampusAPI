/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.smartcampusapi.exceptions;

/**
 *
 * @author mikeyibiayo
 */

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import java.util.Map;

// @Provider allows JAX-RS to automatically use this mapper
// when a RoomNotEmptyException is thrown.
@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {

    // Converts the custom exception into a structured HTTP response.
    @Override
    public Response toResponse(RoomNotEmptyException exception) {
        return Response.status(Response.Status.CONFLICT) // Return HTTP 409 (Conflict) when deletion is not allowed.
                .entity(Map.of(
                        "error", "Room cannot be deleted because it still has sensors assigned", // Main error message.
                        "message", exception.getMessage() // Specific details about the error.
                ))
                .type(MediaType.APPLICATION_JSON) // Ensures the response is returned in JSON format.
                .build(); // Builds and returns the response.
    }
}