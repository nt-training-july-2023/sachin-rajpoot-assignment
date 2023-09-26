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
import org.springframework.data.annotation.CreatedDate;

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
   * @param commentId The unique identifier for the comment.
   * @param content   The content of the comment.
   * @param userName  The username associated with the comment.
   * @param date      The date and time when the comment was created.
   * @param ticket    The ticket to which this comment belongs.
   */
  public Comment(
    final Integer commentId,
    @NotEmpty final String content,
    @NotEmpty final String userName,
    final Date date,
    final Ticket ticket
  ) {
    this.commentId = commentId;
    this.content = content;
    this.userName = userName;
    this.date = date;
    this.ticket = ticket;
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
  public Integer getCommentId() {
    return commentId;
  }

  /**
   * Sets the unique identifier for the comment.
   *
   * @param commentId The comment's unique identifier.
   */
  public void setCommentId(final Integer commentId) {
    this.commentId = commentId;
  }

  /**
   * Gets the content of the comment.
   *
   * @return The content of the comment.
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the content of the comment.
   *
   * @param content The content of the comment.
   */
  public void setContent(final String content) {
    this.content = content;
  }

  /**
   * Gets the username associated with the comment.
   *
   * @return The username associated with the comment.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets the username associated with the comment.
   *
   * @param userName The username associated with the comment.
   */
  public void setUserName(final String userName) {
    this.userName = userName;
  }

  /**
   * Gets the date and time when the comment was created.
   *
   * @return The date and time of comment creation.
   */
  public Date getDate() {
    return date;
  }

  /**
   * Sets the date and time when the comment was created.
   *
   * @param date The date and time of comment creation.
   */
  public void setDate(final Date date) {
    this.date = date;
  }

  /**
   * Gets the ticket to which this comment belongs.
   *
   * @return The ticket associated with the comment.
   */
  public Ticket getTicket() {
    return ticket;
  }

  /**
   * Sets the ticket to which this comment belongs.
   *
   * @param ticket The ticket associated with the comment.
   */
  public void setTicket(final Ticket ticket) {
    this.ticket = ticket;
  }

  /**
   * Returns a string representation of the Comment object.
   *
   * @return A string containing comment details.
   */
  @Override
  public String toString() {
    return (
      "Comment [commentId=" +
      commentId +
      ", content=" +
      content +
      ", userName=" +
      userName +
      ", date=" +
      date +
      "]"
    );
  }
}
