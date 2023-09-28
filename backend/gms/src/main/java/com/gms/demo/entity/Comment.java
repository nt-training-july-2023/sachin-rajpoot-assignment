package com.gms.demo.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;


/**
 * Represents a Comment Entity.
 *
 * @version 1.0
 * @since 28-08-2023
 */
@Entity
public class Comment {

  /**
   * The unique identifier for the comment.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer commentId;

  /**
   * The content of the comment.
   */
  @NotEmpty
  private String content;

  /**
   * The username associated with the comment.
   */
  @NotEmpty
  private String userName;

  /**
   * The date and time when the comment was created.
   */
  @CreationTimestamp // Automatically set the creation timestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  /**
   * The ticket to which this comment belongs.
   */
  @ManyToOne
  @JoinColumn(name = "ticket_id")
  private Ticket ticket;

  /**
   * Constructs a Comment object with the specified parameters.
   *
   * @param commentIdx The unique identifier for the comment.
   * @param contentx   The content of the comment.
   * @param userNamex  The username associated with the comment.
   * @param datex      The date and time when the comment was created.
   * @param ticketx    The ticket to which this comment belongs.
   */
  public Comment(
      final Integer commentIdx,
      @NotEmpty final String contentx,
      @NotEmpty final String userNamex,
      final Date datex,
      final Ticket ticketx
  ) {
    this.commentId = commentIdx;
    this.content = contentx;
    this.userName = userNamex;
    this.date = datex;
    this.ticket = ticketx;
  }

  /**
   * Default constructor for Comment class.
   */
  public Comment() {
    // Default constructor
  }

  /**
   * Gets the unique identifier for the comment.
   *
   * @return The comment's unique identifier.
   */
  public final Integer getCommentId() {
    return commentId;
  }

  /**
   * Sets the unique identifier for the comment.
   *
   * @param commentIdx The comment's unique identifier.
   */
  public final void setCommentId(final Integer commentIdx) {
    this.commentId = commentIdx;
  }

  /**
   * Gets the content of the comment.
   *
   * @return The content of the comment.
   */
  public final String getContent() {
    return content;
  }

  /**
   * Sets the content of the comment.
   *
   * @param contentx The content of the comment.
   */
  public final void setContent(final String contentx) {
    this.content = contentx;
  }

  /**
   * Gets the username associated with the comment.
   *
   * @return The username associated with the comment.
   */
  public final String getUserName() {
    return userName;
  }

  /**
   * Sets the username associated with the comment.
   *
   * @param userNamex The username associated with the comment.
   */
  public final void setUserName(final String userNamex) {
    this.userName = userNamex;
  }

  /**
   * Gets the date and time when the comment was created.
   *
   * @return The date and time of comment creation.
   */
  public final Date getDate() {
    return date;
  }

  /**
   * Sets the date and time when the comment was created.
   *
   * @param datex The date and time of comment creation.
   */
  public final void setDate(final Date datex) {
    this.date = datex;
  }

  /**
   * Gets the ticket to which this comment belongs.
   *
   * @return The ticket associated with the comment.
   */
  public final Ticket getTicket() {
    return ticket;
  }

  /**
   * Sets the ticket to which this comment belongs.
   *
   * @param ticketx The ticket associated with the comment.
   */
  public final void setTicket(final Ticket ticketx) {
    this.ticket = ticketx;
  }

  /**
   * Returns a string representation of the Comment object.
   *
   * @return A string containing comment details.
   */
  @Override
  public final String toString() {
    return (
      "Comment [commentId="
      +
      commentId
      +
      ", content="
      +
      content
      +
      ", userName="
      +
      userName
      +
      ", date="
      +
      date
      +
      "]"
      );
  }
}
