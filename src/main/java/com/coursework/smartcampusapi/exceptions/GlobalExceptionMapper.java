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

// @Provider tells JAX-RS to automatically use this class when handling exceptions.
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    // This method catches any unexpected error that was not handled elsewhere
    // and converts it into a clean JSON response.
    @Override
    public Response toResponse(Throwable exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // Return HTTP 500 for unexpected server errors.
                .entity(Map.of(
                        "error", "Internal server error", // Short main error message.
                        "message", "An unexpected error occurred" // Safe message returned to the client.
                ))
                .type(MediaType.APPLICATION_JSON) // Makes sure the response is sent back as JSON.
                .build(); // Builds and returns the final response.
    }
}