package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;
import java.util.Date;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
/**
 * TicketDto for the ticket entity.
 */

public class TicketDto {

  /**
   * The unique identifier for the ticket.
   */
  private Integer ticketId;

  /**
   * The title of the ticket.
   */
  @NotEmpty
  private String title;

  /**
   * The description of the ticket.
   */
  @NotEmpty
  private String description;

  /**
   * The date and time when the ticket was created.
   */
  @CreationTimestamp // Automatically set the creation timestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn;

  /**
   * The date and time when the ticket was last updated.
   */
  @UpdateTimestamp // Automatically set the update timestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastUpdatedOn;

  /**
   * The status of the ticket.
   */

  //  @NotEmpty
  @NotNull
  @Enumerated(EnumType.STRING)
  private Status status;

  /**
   * The type of the ticket.
   */
  @NotNull
  @Enumerated(EnumType.STRING)
  private TicketType ticketType;

  /**
   * The department associated with the ticket.
   */

  private DepartmentDto department;

  /**
   * The member associated with the ticket.
   */
  @JsonIgnore
  private MemberDto member;

  /**
   * Gets the unique identifier for the ticket.
   *
   * @return The ticket's unique identifier.
   */
  public final Integer getTicketId() {
    return ticketId;
  }

  /**
   * Sets the unique identifier for the ticket.
   *
   * @param ticketIdx The unique identifier to set for the ticket.
   */
  public final void setTicketId(final Integer ticketIdx) {
    this.ticketId = ticketIdx;
  }

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
   * @param titlex The title to set for the ticket.
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
   * @param descriptionx The description to set for the ticket.
   */
  public final void setDescription(final String descriptionx) {
    this.description = descriptionx;
  }

  /**
   * Gets the date and time when the ticket was created.
   *
   * @return The creation date and time of the ticket.
   */
  public final Date getCreatedOn() {
    return createdOn;
  }

  /**
   * Sets the date and time when the ticket was created.
   *
   * @param createdOnx The creation date and time to set for the ticket.
   */
  public final void setCreatedOn(final Date createdOnx) {
    this.createdOn = createdOnx;
  }

  /**
   * Gets the date and time when the ticket was last updated.
   *
   * @return The last updated date and time of the ticket.
   */
  public final Date getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  /**
   * Sets the date and time when the ticket was last updated.
   *
   * @param lastUpdatedOnx The last updated date and time to set for the ticket.
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
   * @param statusx The status to set for the ticket.
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
   * @param ticketTypex The type to set for the ticket.
   */
  public final void setTicketType(final TicketType ticketTypex) {
    this.ticketType = ticketTypex;
  }

  /**
   * Gets the department associated with the ticket.
   *
   * @return The department associated with the ticket.
   */
  public final DepartmentDto getDepartment() {
    return department;
  }

  /**
   * Sets the department associated with the ticket.
   *
   * @param departmentx The department to set for the ticket.
   */
  public final void setDepartment(final DepartmentDto departmentx) {
    this.department = departmentx;
  }

  /**
   * Gets the member associated with the ticket.
   *
   * @return The member associated with the ticket.
   */
  public final MemberDto getMember() {
    return member;
  }

  /**
   * Sets the member associated with the ticket.
   *
   * @param memberx The member to set for the ticket.
   */
  public final void setMember(final MemberDto memberx) {
    this.member = memberx;
  }

  /**
   * Creates an empty TicketDto object.
   */
  public TicketDto() {
    super();
  }

  /**
   * Overrides the default toString() method to provide a
   * custom string representation of the object.
   *
   * @return A string representation of the TicketDto object.
   */
  @Override
  public final String toString() {
    return
      "TicketDto [ticketId="
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
      "]";
  }
}
