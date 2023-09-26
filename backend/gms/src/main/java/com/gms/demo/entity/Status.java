package com.gms.demo.entity;

/**
 * Represents a Status of an employee.
 * 
 * @author Sachin Singh Rajpoot
 * 
 * @version 1.0
 * @since Beginning of time
 * 
 */
public enum Status {
  /**
   * Indicates that the task/ticket is open and pending.
   */
  OPEN,
  /**
   * Indicates that the task/ticket is in progress.
   */
  PROGRESS,
  /**
   * Indicates that the task/ticket is closed or resolved.
   */
  CLOSED,
  
  ALL
}
