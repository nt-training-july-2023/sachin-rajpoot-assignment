package com.gms.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
  //  @Enumerated(EnumType.STRING)
  private Status status;

  /**
   * The type of the ticket.
   */
  //  @NotEmpty
  //  @Enumerated(EnumType.STRING)
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
  @OneToMany(
      mappedBy = "ticket",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY
  )
  private List<Comment> comments = new ArrayList<>();

  /**
   * Get the ticket ID.
   *
   * @return The ticket ID.
   */
  public final Integer getTicketId() {
    return ticketId;
  }

  /**
   * Set the ticket ID.
   *
   * @param ticketIdx The ticket ID to set.
   */
  public final void setTicketId(final Integer ticketIdx) {
    this.ticketId = ticketIdx;
  }

  /**
   * Get the title of the ticket.
   *
   * @return The ticket title.
   */
  public final String getTitle() {
    return title;
  }

  /**
   * Set the title of the ticket.
   *
   * @param titlex The ticket title to set.
   */
  public final void setTitle(final String titlex) {
    this.title = titlex;
  }

  /**
   * Get the description of the ticket.
   *
   * @return The ticket description.
   */
  public final String getDescription() {
    return description;
  }

  /**
   * Set the description of the ticket.
   *
   * @param descriptionx The ticket description to set.
   */
  public final void setDescription(final String descriptionx) {
    this.description = descriptionx;
  }

  /**
   * Get the creation date of the ticket.
   *
   * @return The creation date.
   */
  public final Date getCreatedOn() {
    return createdOn;
  }

  /**
   * Set the creation date of the ticket.
   *
   * @param createdOnx The creation date to set.
   */
  public final void setCreatedOn(final Date createdOnx) {
    this.createdOn = createdOnx;
  }

  /**
   * Get the last updated date of the ticket.
   *
   * @return The last updated date.
   */
  public final Date getLastUpdatedOn() {
    return lastUpdatedOn;
  }

  /**
   * Set the last updated date of the ticket.
   *
   * @param lastUpdatedOnx The last updated date to set.
   */
  public final void setLastUpdatedOn(final Date lastUpdatedOnx) {
    this.lastUpdatedOn = lastUpdatedOnx;
  }

  /**
   * Get the status of the ticket.
   *
   * @return The ticket status.
   */
  public final Status getStatus() {
    return status;
  }

  /**
   * Set the status of the ticket.
   *
   * @param statusx The ticket status to set.
   */
  public final void setStatus(final Status statusx) {
    this.status = statusx;
  }

  /**
   * Get the ticket type.
   *
   * @return The ticket type.
   */
  public final TicketType getTicketType() {
    return ticketType;
  }

  /**
   * Set the ticket type.
   *
   * @param ticketTypex The ticket type to set.
   */
  public final void setTicketType(final TicketType ticketTypex) {
    this.ticketType = ticketTypex;
  }

  /**
   * Get the department associated with the ticket.
   *
   * @return The department.
   */
  public final Department getDepartment() {
    return department;
  }

  /**
   * Set the department associated with the ticket.
   *
   * @param departmentx The department to set.
   */
  public final void setDepartment(final Department departmentx) {
    this.department = departmentx;
  }

  /**
   * Get the member associated with the ticket.
   *
   * @return The member.
   */
  public final Member getMember() {
    return member;
  }

  /**
   * Set the member associated with the ticket.
   *
   * @param memberx The member to set.
   */
  public final void setMember(final Member memberx) {
    this.member = memberx;
  }

  /**
   * Get the list of comments associated with the ticket.
   *
   * @return The list of comments.
   */
  public final List<Comment> getComments() {
    return comments;
  }

  /**
   * Set the list of comments associated with the ticket.
   *
   * @param commentsx The list of comments to set.
   */
  public final void setComments(final List<Comment> commentsx) {
    this.comments = commentsx;
  }

  /**
   * Add new comment.
   *
   * @param commentx to set.
   */
  public final void addComment(final Comment commentx) {
    if (this.comments == null) {
      this.comments = new ArrayList<>();
    } else {
      this.comments.add(commentx);
    }
  }

  /**
   * Generates a string representation of the Ticket object.
   *
   * @return The string representation.
   */
  @Override
  public final String toString() {
    return (
      "Ticket [ticketId="
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
      ", department="
      +
      department
      +
      ", member="
      +
      member
      +
      ", comments="
      +
      comments
      +
      "]"
      );
  }

  /**
   * Default constructor for creating a Ticket object.
   */
  public Ticket() {
    super();
  }
}
