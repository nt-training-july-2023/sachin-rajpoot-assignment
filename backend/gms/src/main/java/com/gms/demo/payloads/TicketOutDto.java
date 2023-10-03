package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * TicketOutDto fir ticket entity.
 */

public class TicketOutDto {

  /**
   * The unique identifier for the ticket.
   */
  private Integer ticketId;

  /**
   * gets ticketId of the ticket.
   *
   * @return ticketId
   */
  public final Integer getTicketId() {
    return ticketId;
  }

  /**
   * sets ticketId of the ticket.
   *
   * @param ticketIdx ticket Id
   */
  public final void setTicketId(final Integer ticketIdx) {
    this.ticketId = ticketIdx;
  }

  /**
   * The title of the ticket.
   */
  private String title;

  /**
   * The description of the ticket.
   */
  private String description;

  /**
   * The date and time when the ticket was created.
   */
  @CreationTimestamp // Automatically set the creation timestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
  private Date createdOn;

  /**
   * The date and time when the ticket was last updated.
   */
  @UpdateTimestamp // Automatically set the update timestamp
  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
  private Date lastUpdatedOn;

  /**
   * The status of the ticket.
   */
  @Enumerated(EnumType.STRING)
  private Status status;

  /**
   * The type of the ticket.
   */
  @Enumerated(EnumType.STRING)
  private TicketType ticketType;

  /**
   * The name of the department associated
   * with the ticket.
   */
  private String departmentName;

  /**
   * The name of the member associated with the ticket.
   */
  private String memberName;

  /**
   * A list of CommentOutDto objects representing comments
   *     associated with the ticket.
   */
  private List<CommentOutDto> comments;

  /**
   * Gets the title of the ticket.
   *
   * @return The title of the ticket.
   */
  public final String getTitle() {
    return title;
  }

  /**
   * Sets the title of the ticket.
   *
   * @param titlex The title of the ticket to set.
   */
  public final void setTitle(final String titlex) {
    this.title = titlex;
  }

  /**
   * Gets the description of the ticket.
   *
   * @return The description of the ticket.
   */
  public final String getDescription() {
    return description;
  }

  /**
   * Sets the description of the ticket.
   *
   * @param descriptionx The description of the ticket to set.
   */
  public final void setDescription(final String descriptionx) {
    this.description = descriptionx;
  }

  /**
   * Gets the date when the ticket was created.
   *
   * @return The date when the ticket was created.
   */
  public final Date getCreatedOn() {
    return createdOn;
  }

  /**
   * Sets the date when the ticket was created.
   *
   * @param createdOnx The date when the ticket was created to set.
   */
  public final void setCreatedOn(final Date createdOnx) {
    this.createdOn = createdOnx;
  }

  /**
   * Gets the date when the ticket was last updated.
   *
   * @return The date when the ticket was last updated.
   */
  public final Date getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  /**
   * Sets the date when the ticket was last updated.
   *
   * @param lastUpdatedOnx The date when the ticket was last updated to set.
   */
  public final void setLastUpdatedOn(final Date lastUpdatedOnx) {
    this.lastUpdatedOn = lastUpdatedOnx;
  }

  /**
   * Gets the status of the ticket.
   *
   * @return The status of the ticket.
   */
  public final Status getStatus() {
    return status;
  }

  /**
   * Sets the status of the ticket.
   *
   * @param statusx The status of the ticket to set.
   */
  public final void setStatus(final Status statusx) {
    this.status = statusx;
  }

  /**
   * Gets the type of the ticket.
   *
   * @return The type of the ticket.
   */
  public final TicketType getTicketType() {
    return ticketType;
  }

  /**
   * Sets the type of the ticket.
   *
   * @param ticketTypex The type of the ticket to set.
   */
  public final void setTicketType(final TicketType ticketTypex) {
    this.ticketType = ticketTypex;
  }

  /**
   * Gets the name of the department associated with the ticket.
   *
   * @return The name of the department associated with the ticket.
   */
  public final String getDepartmentName() {
    return departmentName;
  }

  /**
   * Sets the name of the department associated with the ticket.
   *
   * @param departmentNamex The name of the department to set.
   */
  public final void setDepartmentName(final String departmentNamex) {
    this.departmentName = departmentNamex;
  }

  /**
   * Gets the name of the member associated with the ticket.
   *
   * @return The name of the member associated with the ticket.
   */
  public final String getMemberName() {
    return memberName;
  }

  /**
   * Sets the name of the member associated with the ticket.
   *
   * @param memberNamex The name of the member to set.
   */
  public final void setMemberName(final String memberNamex) {
    this.memberName = memberNamex;
  }

  /**
   * Gets the list of comments associated with the ticket.
   *
   * @return The list of comments associated with the ticket.
   */
  public final List<CommentOutDto> getComments() {
    return comments;
  }

  /**
   * Sets the list of comments associated
   * with the ticket.
   *
   * @param commentsx The list of comments to set.
   */
  public final void setComments(
      final List<CommentOutDto> commentsx) {
    this.comments = commentsx;
  }

  /**
   * Returns a string representation of the TicketOutDto object.
   *
   * @return A string containing the values of the title,
   *      description, createdOn, lastUpdatedOn, status, ticketType,
   *      departmentName,and memberName fields.
   */
  @Override
  public final String toString() {
    return
      "TicketOutDto [title="
      +
      title
      +
      ", description="
      +
      description
      +
      ", createdOn="
      +
      createdOn
      +
      ", lastUpdatedOn="
      +
      lastUpdatedOn
      +
      ", status="
      +
      status
      +
      ", ticketType="
      +
      ticketType
      +
      ", departmentName="
      +
      departmentName
      +
      ", memberName="
      +
      memberName
      +
      "]";
  }

  /**
   * Constructs a new empty TicketOutDto object.
   */
  public TicketOutDto() {
    super();
  }

  /**
   * Generates the hashcode.
   */
  @Override
  public final int hashCode() {
    return Objects.hash(
      comments,
      createdOn,
      departmentName,
      description,
      lastUpdatedOn,
      memberName,
      status,
      ticketId,
      ticketType,
      title
    );
  }

  /**
   * compares the object.
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
    TicketOutDto other = (TicketOutDto) obj;
    return
      Objects.equals(comments, other.comments)
      &&
      Objects.equals(createdOn, other.createdOn)
      &&
      Objects.equals(departmentName, other.departmentName)
      &&
      Objects.equals(description, other.description)
      &&
      Objects.equals(lastUpdatedOn, other.lastUpdatedOn)
      &&
      Objects.equals(memberName, other.memberName)
      &&
      status == other.status
      &&
      Objects.equals(ticketId, other.ticketId)
      &&
      ticketType == other.ticketType
      &&
      Objects.equals(title, other.title);
  }

}

