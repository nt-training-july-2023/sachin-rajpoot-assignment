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

public class TicketOutDto {

  /**
   * The unique identifier for the ticket.
   */
  private Integer ticketId;

  public final Integer getTicketId() {
    return ticketId;
  }

  public final void setTicketId(final Integer ticketId) {
    this.ticketId = ticketId;
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
   * The name of the department associated with the ticket.
   */
  private String departmentName;

  /**
   * The name of the member associated with the ticket.
   */
  private String memberName;

  /**
   * A list of CommentOutDto objects representing comments associated with the ticket.
   */
  private List<CommentOutDto> comments;

  /**
   * Gets the title of the ticket.
   *
   * @return The title of the ticket.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the ticket.
   *
   * @param title The title of the ticket to set.
   */
  public void setTitle(final String title) {
    this.title = title;
  }

  /**
   * Gets the description of the ticket.
   *
   * @return The description of the ticket.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the ticket.
   *
   * @param description The description of the ticket to set.
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Gets the date when the ticket was created.
   *
   * @return The date when the ticket was created.
   */
  public Date getCreatedOn() {
    return createdOn;
  }

  /**
   * Sets the date when the ticket was created.
   *
   * @param createdOn The date when the ticket was created to set.
   */
  public void setCreatedOn(final Date createdOn) {
    this.createdOn = createdOn;
  }

  /**
   * Gets the date when the ticket was last updated.
   *
   * @return The date when the ticket was last updated.
   */
  public Date getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  /**
   * Sets the date when the ticket was last updated.
   *
   * @param lastUpdatedOn The date when the ticket was last updated to set.
   */
  public void setLastUpdatedOn(final Date lastUpdatedOn) {
    this.lastUpdatedOn = lastUpdatedOn;
  }

  /**
   * Gets the status of the ticket.
   *
   * @return The status of the ticket.
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Sets the status of the ticket.
   *
   * @param status The status of the ticket to set.
   */
  public void setStatus(final Status status) {
    this.status = status;
  }

  /**
   * Gets the type of the ticket.
   *
   * @return The type of the ticket.
   */
  public TicketType getTicketType() {
    return ticketType;
  }

  /**
   * Sets the type of the ticket.
   *
   * @param ticketType The type of the ticket to set.
   */
  public void setTicketType(final TicketType ticketType) {
    this.ticketType = ticketType;
  }

  /**
   * Gets the name of the department associated with the ticket.
   *
   * @return The name of the department associated with the ticket.
   */
  public String getDepartmentName() {
    return departmentName;
  }

  /**
   * Sets the name of the department associated with the ticket.
   *
   * @param departmentName The name of the department to set.
   */
  public void setDepartmentName(final String departmentName) {
    this.departmentName = departmentName;
  }

  /**
   * Gets the name of the member associated with the ticket.
   *
   * @return The name of the member associated with the ticket.
   */
  public String getMemberName() {
    return memberName;
  }

  /**
   * Sets the name of the member associated with the ticket.
   *
   * @param memberName The name of the member to set.
   */
  public void setMemberName(final String memberName) {
    this.memberName = memberName;
  }

  /**
   * Gets the list of comments associated with the ticket.
   *
   * @return The list of comments associated with the ticket.
   */
  public List<CommentOutDto> getComments() {
    return comments;
  }

  /**
   * Sets the list of comments associated with the ticket.
   *
   * @param comments The list of comments to set.
   */
  public void setComments(final List<CommentOutDto> comments) {
    this.comments = comments;
  }

  /**
   * Returns a string representation of the TicketOutDto object.
   *
   * @return A string containing the values of the title, description, createdOn, lastUpdatedOn,
   *         status, ticketType, departmentName, and memberName fields.
   */
  @Override
  public final String toString() {
    return (
      "TicketOutDto [title=" +
      title +
      ", description=" +
      description +
      ", createdOn=" +
      createdOn +
      ", lastUpdatedOn=" +
      lastUpdatedOn +
      ", status=" +
      status +
      ", ticketType=" +
      ticketType +
      ", departmentName=" +
      departmentName +
      ", memberName=" +
      memberName +
      "]"
    );
  }

  /**
   * Constructs a new TicketOutDto object with the specified attributes.
   *
   * @param title The title of the ticket.
   * @param description The description of the ticket.
   * @param createdOn The date when the ticket was created.
   * @param lastUpdatedOn The date when the ticket was last updated.
   * @param status The status of the ticket.
   * @param ticketType The type of the ticket.
   * @param departmentName The name of the department associated with the ticket.
   * @param memberName The name of the member associated with the ticket.
   * @param comments The list of comments associated with the ticket.
   */
  public TicketOutDto(
    final String title,
    final String description,
    final Date createdOn,
    final Date lastUpdatedOn,
    final Status status,
    final TicketType ticketType,
    final String departmentName,
    final String memberName,
    final List<CommentOutDto> comments
  ) {
    super();
    this.title = title;
    this.description = description;
    this.createdOn = createdOn;
    this.lastUpdatedOn = lastUpdatedOn;
    this.status = status;
    this.ticketType = ticketType;
    this.departmentName = departmentName;
    this.memberName = memberName;
    this.comments = comments;
  }

  /**
   * Constructs a new empty TicketOutDto object.
   */
  public TicketOutDto() {
    super();
    // TODO Auto-generated constructor stub
  }

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
    return (
      Objects.equals(comments, other.comments) &&
      Objects.equals(createdOn, other.createdOn) &&
      Objects.equals(departmentName, other.departmentName) &&
      Objects.equals(description, other.description) &&
      Objects.equals(lastUpdatedOn, other.lastUpdatedOn) &&
      Objects.equals(memberName, other.memberName) &&
      status == other.status &&
      Objects.equals(ticketId, other.ticketId) &&
      ticketType == other.ticketType &&
      Objects.equals(title, other.title)
    );
  }

  public TicketOutDto(
    final Integer ticketId,
    final String title,
    final String description,
    final Date createdOn,
    final Date lastUpdatedOn,
    final Status status,
    final TicketType ticketType,
    final String departmentName,
    final String memberName,
    final List<CommentOutDto> comments
  ) {
    super();
    this.ticketId = ticketId;
    this.title = title;
    this.description = description;
    this.createdOn = createdOn;
    this.lastUpdatedOn = lastUpdatedOn;
    this.status = status;
    this.ticketType = ticketType;
    this.departmentName = departmentName;
    this.memberName = memberName;
    this.comments = comments;
  }
}
