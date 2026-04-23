/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.smartcampusapi.exceptions;

/**
 *
 * @author mikeyibiayo
 */

// Custom exception used when a related resource does not exist.
// Example: trying to create a sensor with a roomId that is not in the system.
public class LinkedResourceNotFoundException extends RuntimeException {

    // Constructor that allows a custom error message to be passed in.
    public LinkedResourceNotFoundException(String message) {
        super(message); // Passes the message to the parent RuntimeException class.
    }
}