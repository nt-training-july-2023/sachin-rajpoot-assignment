package com.gms.demo.entity;

/**
 * Represents a Status of an employee.
 *
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
  BEING_ADDRESSED,
  /**
   * Indicates that the task/ticket is closed or resolved.
   */
  RESOLVED,
  /**
   * Indicates ALL tickets.
   */
  ALL
}
