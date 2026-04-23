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
// when a SensorUnavailableException is thrown.
@Provider
public class SensorUnavailableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {

    // Converts the custom exception into a structured HTTP response.
    @Override
    public Response toResponse(SensorUnavailableException exception) {
        return Response.status(Response.Status.FORBIDDEN) // Return HTTP 403 when the sensor is not allowed to be used.
                .entity(Map.of(
                        "error", "Sensor is unavailable", // Main error message.
                        "message", exception.getMessage() // Detailed explanation of why it is unavailable.
                ))
                .type(MediaType.APPLICATION_JSON) // Ensures the response is returned in JSON format.
                .build(); // Builds and returns the response.
    }
}