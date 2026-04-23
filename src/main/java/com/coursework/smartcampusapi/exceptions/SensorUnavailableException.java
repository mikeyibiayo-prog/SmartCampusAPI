/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.smartcampusapi.exceptions;

/**
 *
 * @author mikeyibiayo
 */

// Custom exception used when a sensor cannot be used.
// In this project, this happens when the sensor status is MAINTENANCE.
public class SensorUnavailableException extends RuntimeException {

    // Constructor allows a custom error message to be passed in.
    public SensorUnavailableException(String message) {
        super(message); // Passes the message to the parent RuntimeException class.
    }
}