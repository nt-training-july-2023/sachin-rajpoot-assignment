package com.gms.demo.payloads;

import com.gms.demo.entity.Status;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;

/**
 * A DTO (Data Transfer Object) class representing
 * a ticket status update request.
 * It contains the new status and an optional
 * comment associated with the update.
 */

public class TicketUpdateStatusInDto {

  /**
  * The ticket Id for ticket.
  */
  private Integer ticketId;
  /**
   * The new status for the ticket.
   */
  private Status status;

  /**
   * An optional comment associated with the status update.
   */
  private CommentDto comment;

  /**
   * Get the new status.
   *
   * @return The new status.
   */
  public final Status getStatus() {
    return status;
  }

  /**
   * Set the new status.
   *
   * @param statusx The new status to set.
   */
  public final void setStatus(final Status statusx) {
    this.status = statusx;
  }

  /**
   * Get the associated comment.
   *
   * @return The comment associated with the status update.
   */
  public final CommentDto getComment() {
    return comment;
  }

  /**
   * Set the associated comment.
   *
   * @param commentx The comment to associate with the status update.
   */
  public final void setComment(final CommentDto commentx) {
    this.comment = commentx;
  }

  /**
   * Generate a hash code for this object based on its properties.
   *
   * @return The hash code for this object.
   */
  @Override
  public final int hashCode() {
    return Objects.hash(comment, status);
  }

  /**
   * Compare this object to another object for equality.
   *
   * @param obj The object to compare with.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public final boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    TicketUpdateStatusInDto other = (TicketUpdateStatusInDto) obj;
    return Objects.equals(comment, other.comment) && status == other.status;
  }

  /**
   * Get a string representation of this object.
   *
   * @return A string representation of the
   *     TicketUpdateStatusInDto.
   */
  @Override
  public final String toString() {
    return (
      "TicketUpdateStatusInDto [status=" + status + ", comment=" + comment + "]"
      );
  }

  /**
   * Create a new instance of TicketUpdateStatusInDto
   *     with the specified status and comment.
   *
   * @param statusx The new status for the ticket.
   * @param commentx An optional comment associated
   *     with the status update.
   */
  public TicketUpdateStatusInDto(
      @NotEmpty final Status statusx,
      @NotEmpty final CommentDto commentx
  ) {
    super();
    this.status = statusx;
    this.comment = commentx;
  }

  /**
   * Create a new instance of TicketUpdateStatusInDto with default values.
   */
  public TicketUpdateStatusInDto() {
    super();
  }
}
