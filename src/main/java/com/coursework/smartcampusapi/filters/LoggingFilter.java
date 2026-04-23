/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.coursework.smartcampusapi.filters;


import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;


import jakarta.ws.rs.ext.Provider;


import java.io.IOException;


import java.util.logging.Logger;



@Provider // Marks this class so Jersey automatically detects it
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    // Create a logger instance for this class
    // Used to print logs to the console (Tomcat log)
    private static final Logger LOGGER = Logger.getLogger(LoggingFilter.class.getName());

    /**
     * This method runs BEFORE the request reaches your resource (e.g. SensorResource)
     * 
     * @param requestContext contains details about the incoming HTTP request
     */
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Log the HTTP method (GET, POST, etc.)
        // and the full request URL
        LOGGER.info(
            "Request: " 
            + requestContext.getMethod() + " " 
            + requestContext.getUriInfo().getRequestUri()
        );
    }

    /**
     * This method runs AFTER the response has been created
     * 
     * @param requestContext contains request info
     * @param responseContext contains response info
     */
    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        // Log the HTTP response status (e.g. 200, 404, 500)
        LOGGER.info(
            "Response status: " 
            + responseContext.getStatus()
        );
    }
}