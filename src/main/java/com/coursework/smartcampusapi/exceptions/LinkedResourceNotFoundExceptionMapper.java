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

// @Provider tells JAX-RS to automatically use this mapper when the exception is thrown.
@Provider
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {

    // Converts the custom exception into a proper HTTP response.
    @Override
    public Response toResponse(LinkedResourceNotFoundException exception) {
        return Response.status(422) // Return HTTP 422 (Unprocessable Entity) for missing linked resources.
                .entity(Map.of(
                        "error", "Linked resource not found", // Main error title.
                        "message", exception.getMessage() // Detailed message from the exception.
                ))
                .type(MediaType.APPLICATION_JSON) // Ensures the response is returned as JSON.
                .build(); // Builds and returns the response.
    }
}