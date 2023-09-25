package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class TicketGetAllOutDto {

  /**
   * The unique identifier for the ticket.
   */
  private Integer ticketId;

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
//  @CreationTimestamp // Automatically set the creation timestamp
//  @Temporal(TemporalType.TIMESTAMP)
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
  private Date createdOn;

  /**
   * The date and time when the ticket was last updated.
   */
//  @UpdateTimestamp // Automatically set the update timestamp
//  @Temporal(TemporalType.TIMESTAMP)
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

  public final Integer getTicketId() {
    return ticketId;
  }

  public final void setTicketId(final Integer ticketId) {
    this.ticketId = ticketId;
  }

  public final String getTitle() {
    return title;
  }

  public final void setTitle(final String title) {
    this.title = title;
  }

  public final String getDescription() {
    return description;
  }

  public final void setDescription(final String description) {
    this.description = description;
  }

  public final Date getCreatedOn() {
    return createdOn;
  }

  public final void setCreatedOn(final Date createdOn) {
    this.createdOn = createdOn;
  }

  public final Date getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  public final void setLastUpdatedOn(final Date lastUpdatedOn) {
    this.lastUpdatedOn = lastUpdatedOn;
  }

  public final Status getStatus() {
    return status;
  }

  public final void setStatus(final Status status) {
    this.status = status;
  }

  public final TicketType getTicketType() {
    return ticketType;
  }

  public final void setTicketType(final TicketType ticketType) {
    this.ticketType = ticketType;
  }

  public final String getDepartmentName() {
    return departmentName;
  }

  public final void setDepartmentName(final String departmentName) {
    this.departmentName = departmentName;
  }

  public final String getMemberName() {
    return memberName;
  }

  public final void setMemberName(final String memberName) {
    this.memberName = memberName;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(
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
    TicketGetAllOutDto other = (TicketGetAllOutDto) obj;
    return (
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

  @Override
  public final String toString() {
    return (
      "TicketGetAllOutDto [ticketId=" +
      ticketId +
      ", title=" +
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

  

  public TicketGetAllOutDto() {
    super();
    // TODO Auto-generated constructor stub
  }
}