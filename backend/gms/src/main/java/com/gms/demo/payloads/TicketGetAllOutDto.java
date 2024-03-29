package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


/**
 * TicketGetAllOutDto for ticket entity.
 */
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
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
  private Date createdOn;

  /**
   * The date and time when the ticket was last updated.
   */
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
   * gets ticketId of the the ticket.
   *
   *@return  ticketId ticket Id
   */
  public final Integer getTicketId() {
    return ticketId;
  }

  /**
   * sets ticketId of the the ticket.
   *
   *@param ticketIdx ticket Id
   */
  public final void setTicketId(final Integer ticketIdx) {
    this.ticketId = ticketIdx;
  }

  /**
   * gets title of the the ticket.
   *
   *@return  title title
   */
  public final String getTitle() {
    return title;
  }

  /**
   * sets title of the the ticket.
   *
   *@param titlex title
   */
  public final void setTitle(final String titlex) {
    this.title = titlex;
  }

  /**
   * gets description of the the ticket.
   *
   *@return  description description
   */
  public final String getDescription() {
    return description;
  }

  /**
   * sets description of the the ticket.
   *
   *@param descriptionx description
   */
  public final void setDescription(final String descriptionx) {
    this.description = descriptionx;
  }

  /**
   * gets createdOn of the the ticket.
   *
   *@return  createdOn createdOn
   */
  public final Date getCreatedOn() {
    return createdOn;
  }

  /**
   * sets createdOn of the the ticket.
   *
   *@param createdOnx created On
   */
  public final void setCreatedOn(final Date createdOnx) {
    this.createdOn = createdOnx;
  }

  /**
   * gets lastUpdatedOn of the the ticket.
   *
   *@return lastUpdatedOn last Updated On
   */
  public final Date getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  /**
   * sets lastUpdatedOn of the the ticket.
   *
   *@param lastUpdatedOnx lastUpdatedOn
   */
  public final void setLastUpdatedOn(final Date lastUpdatedOnx) {
    this.lastUpdatedOn = lastUpdatedOnx;
  }

  /**
   * gets status of the the ticket.
   *
   *@return  status status
   */
  public final Status getStatus() {
    return status;
  }

  /**
   * sets status of the the ticket.
   *
   *@param statusx status
   */
  public final void setStatus(final Status statusx) {
    this.status = statusx;
  }

  /**
   * gets status of the the ticket.
   *
   *@return ticketType ticketType
   */
  public final TicketType getTicketType() {
    return ticketType;
  }

  /**
   * sets ticketType of the the ticket.
   *
   *@param ticketTypex ticketType
   */
  public final void setTicketType(final TicketType ticketTypex) {
    this.ticketType = ticketTypex;
  }

  /**
   * gets departmentName of the the ticket.
   *
   *@return departmentName departmentName
   */
  public final String getDepartmentName() {
    return departmentName;
  }

  /**
   * sets departmentName of the the ticket.
   *
   *@param departmentNamex departmentName
   */
  public final void setDepartmentName(final String departmentNamex) {
    this.departmentName = departmentNamex;
  }

  /**
   * gets status of the the ticket.
   *
   *@return ticketType ticketType
   */
  public final String getMemberName() {
    return memberName;
  }

  /**
   * sets memberName of the the ticket.
   *
   *@param memberNamex memberName
   */
  public final void setMemberName(final String memberNamex) {
    this.memberName = memberNamex;
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
    return
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

  @Override
  public final String toString() {
    return
      "TicketGetAllOutDto [ticketId="
      +
      ticketId
      +
      ", title="
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
   *Default constructor.
   */
  public TicketGetAllOutDto() {
    super();
  }
}
