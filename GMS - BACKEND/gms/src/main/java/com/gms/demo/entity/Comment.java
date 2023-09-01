package com.gms.demo.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
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
  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

  /**
   * The ticket to which this comment belongs.
   */
  @ManyToOne
  @JoinColumn(name = "ticket_id")
  private Ticket ticket;

  /**
   * Get the comment ID.
   *
   * @return The comment ID.
   */
  public Integer getCommentId() {
    return commentId;
  }

  /**
   * Set the comment ID.
   *
   * @param commentId The comment ID to set.
   */
  public void setCommentId(final Integer commentId) {
    this.commentId = commentId;
  }

  /**
   * Get the content of the comment.
   *
   * @return The content of the comment.
   */
  public String getContent() {
    return content;
  }

  /**
   * Set the content of the comment.
   *
   * @param content The content to set.
   */
  public void setContent(final String content) {
    this.content = content;
  }

  /**
   * Get the user name associated with the comment.
   *
   * @return The user name.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Set the user name associated with the comment.
   *
   * @param userName The user name to set.
   */
  public void setUserName(final String userName) {
    this.userName = userName;
  }

  /**
   * Get the date of the comment.
   *
   * @return The comment date.
   */
  public Date getDate() {
    return date;
  }

  /**
   * Set the date of the comment.
   *
   * @param date The date to set.
   */
  public void setDate(final Date date) {
    this.date = date;
  }

  /**
   * Get the associated ticket.
   *
   * @return The associated ticket.
   */
  public Ticket getTicket() {
    return ticket;
  }

  /**
   * Set the associated ticket.
   *
   * @param ticket The ticket to set.
   */
  public void setTicket(final Ticket ticket) {
    this.ticket = ticket;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(commentId, content, date, ticket, userName);
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
    Comment other = (Comment) obj;
    return (Objects.equals(commentId, other.commentId)
        &&
        Objects.equals(content, other.content)
        &&
        Objects.equals(date, other.date)
        &&
        Objects.equals(ticket, other.ticket)
        &&
        Objects.equals(userName, other.userName));
  }

  @Override
  public final String toString() {
    return ("Comment [commentId="
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
        ", ticket="
        +
        ticket
        +
        "]");
  }

  /**
   * Constructor with parameters.
   *
   * @param commentId The comment ID.
   * @param content   The content of the comment.
   * @param userName  The user name associated with the comment.
   * @param date      The date of the comment.
   * @param ticket    The associated ticket.
   */
  public Comment(
      final Integer commentId,
      @NotEmpty final String content,
      @NotEmpty final String userName,
      final Date date,
      final Ticket ticket) {
    super();
    this.commentId = commentId;
    this.content = content;
    this.userName = userName;
    this.date = date;
    this.ticket = ticket;
  }

  /**
   * Default constructor.
   */
  public Comment() {
    super();
  }
}
