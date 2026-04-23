/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.smartcampusapi.resources;

// JAX-RS imports for REST endpoints
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// Java utilities for storing data
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author mikeyibiayo
 */
@Path("/")   // Maps this class to /api/v1
public class DiscoveryResource {

    /**
     * Handles HTTP GET requests to /api/v1
     * 
     * @return A JSON response containing API metadata
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON) // Ensures response is JSON
    public Map<String, Object> getApiInfo() {

        // Create main response object
        Map<String, Object> response = new HashMap<>();

        // Add API version
        response.put("version", "v1");

        // Add contact information (for admin/support)
        response.put("contact", "mikey@smartcampus.com");

        // Create a map to store available resources
        Map<String, String> resources = new HashMap<>();

        // Add main API endpoints (HATEOAS-style navigation)
        resources.put("rooms", "/api/v1/rooms");

        // Add resources map into main response
        response.put("resources", resources);

        // Return JSON response
        return response;
    }
}