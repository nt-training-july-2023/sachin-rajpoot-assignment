package com.gms.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

/**
 * Represents an Department Entity.
 *
 * @author Sachin Singh Rajpoot
 *
 * @version 1.0
 * @since Begining of time
 */

@Entity
public class Ticket {

  /**
   * The unique identifier for the ticket.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn;

  /**
   * The date and time when the ticket was last updated.
   */
  @UpdateTimestamp
  private Date lastUpdatedOn;

  /**
   * The status of the ticket.
   */
//  @NotEmpty
  @Enumerated(EnumType.STRING)
  private Status status;

  /**
   * The type of the ticket.
   */
  @NotEmpty
  @Enumerated(EnumType.STRING)
  private TicketType ticketType;

  /**
   * The department associated with the ticket.
   */
  @ManyToOne
  @JoinColumn(name = "department_id")
 private Department department;

  /**
   * The member who created the ticket.
   */
  @ManyToOne
  @JoinColumn(name = "member_id")
 private Member member;

  /**
   * The list of comments associated with this ticket.
   */
  @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private List<Comment> comments = new ArrayList<>();

  /**
   * Get the ticket ID.
   *
   * @return The ticket ID.
   */
  public Integer getTicketId() {
    return ticketId;
  }

  /**
   * Set the ticket ID.
   *
   * @param ticketId The ticket ID to set.
   */
  public void setTicketId(final Integer ticketId) {
    this.ticketId = ticketId;
  }

  /**
   * Get the title of the ticket.
   *
   * @return The ticket title.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Set the title of the ticket.
   *
   * @param title The ticket title to set.
   */
  public void setTitle(final String title) {
    this.title = title;
  }

  /**
   * Get the description of the ticket.
   *
   * @return The ticket description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Set the description of the ticket.
   *
   * @param description The ticket description to set.
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Get the creation date of the ticket.
   *
   * @return The creation date.
   */
  public Date getCreatedOn() {
    return createdOn;
  }

  /**
   * Set the creation date of the ticket.
   *
   * @param createdOn The creation date to set.
   */
  public void setCreatedOn(final Date createdOn) {
    this.createdOn = createdOn;
  }

  /**
   * Get the last updated date of the ticket.
   *
   * @return The last updated date.
   */
  public Date getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  /**
   * Set the last updated date of the ticket.
   *
   * @param lastUpdatedOn The last updated date to set.
   */
  public void setLastUpdatedOn(final Date lastUpdatedOn) {
    this.lastUpdatedOn = lastUpdatedOn;
  }

  /**
   * Get the status of the ticket.
   *
   * @return The ticket status.
   */
  public Status getStatus() {
    return status;
  }

  /**
   * Set the status of the ticket.
   *
   * @param status The ticket status to set.
   */
  public void setStatus(final Status status) {
    this.status = status;
  }

  /**
   * Get the ticket type.
   *
   * @return The ticket type.
   */
  public TicketType getTicketType() {
    return ticketType;
  }

  /**
   * Set the ticket type.
   *
   * @param ticketType The ticket type to set.
   */
  public void setTicketType(final TicketType ticketType) {
    this.ticketType = ticketType;
  }

  /**
   * Get the department associated with the ticket.
   *
   * @return The department.
   */
  public Department getDepartment() {
    return department;
  }

  /**
   * Set the department associated with the ticket.
   *
   * @param department The department to set.
   */
  public void setDepartment(final Department department) {
    this.department = department;
  }

  /**
   * Get the member associated with the ticket.
   *
   * @return The member.
   */
  public Member getMember() {
    return member;
  }

  /**
   * Set the member associated with the ticket.
   *
   * @param member The member to set.
   */
  public void setMember(final Member member) {
    this.member = member;
  }

  /**
   * Get the list of comments associated with the ticket.
   *
   * @return The list of comments.
   */
  public List<Comment> getComments() {
    return comments;
  }

  /**
   * Set the list of comments associated with the ticket.
   *
   * @param comments The list of comments to set.
   */
  public void setComments(final List<Comment> comments) {
    this.comments = comments;
  }

  /**
   * Generates a string representation of the Ticket object.
   *
   * @return The string representation.
   */
  @Override
  public String toString() {
    return ("Ticket [ticketId="
        + ticketId
        + ", title="
        + title
        + ", description="
        + description
        + ", createdOn="
        + createdOn
        + ", lastUpdatedOn="
        + lastUpdatedOn
        + ", status="
        + status
        + ", ticketType="
        + ticketType
        + ", department="
        + department
        + ", member="
        + member
        + ", comments="
        + comments
        + "]");
  }

  /**
   * Constructor for creating a Ticket object.
   *
   * @param ticketId      The ticket ID.
   * @param title         The title of the ticket.
   * @param description   The description of the ticket.
   * @param createdOn     The creation date of the ticket.
   * @param lastUpdatedOn The last updated date of the ticket.
   * @param status        The status of the ticket.
   * @param ticketType    The type of the ticket.
   * @param department    The department associated with the ticket.
   * @param member        The member associated with the ticket.
   * @param comments      The list of comments associated with the ticket.
   */
  public Ticket(
      final Integer ticketId,
      @NotEmpty final String title,
      @NotEmpty final String description,
      final Date createdOn,
      final Date lastUpdatedOn,
      @NotEmpty final Status status,
      @NotEmpty final TicketType ticketType,
      final Department department,
      final Member member,
      final List<Comment> comments) {
    super();
    this.ticketId = ticketId;
    this.title = title;
    this.description = description;
    this.createdOn = createdOn;
    this.lastUpdatedOn = lastUpdatedOn;
    this.status = status;
    this.ticketType = ticketType;
    this.department = department;
    this.member = member;
    this.comments = comments;
  }

  /**
   * Default constructor for creating a Ticket object.
   */
  public Ticket() {
    super();
  }

}
