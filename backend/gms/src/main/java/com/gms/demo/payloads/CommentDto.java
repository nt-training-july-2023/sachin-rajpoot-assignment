package com.gms.demo.payloads;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;

/**
 * Class for Comment In DTO.
 */
public class CommentDto {

  /**
   * The unique identifier for the comment.
   */
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
  private TicketDto ticket;

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
   * @param commentId The comment's unique identifier.
   */
  public final void setCommentId(final Integer commentId) {
    this.commentId = commentId;
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
   * @param content The content of the comment.
   */
  public final void setContent(final String content) {
    this.content = content;
  }

  /**
   * Gets the username associated with the comment.
   *
   * @return The username of the comment's author.
   */
  public final String getUserName() {
    return userName;
  }

  /**
   * Sets the username associated with the comment.
   *
   * @param userName The username of the comment's author.
   */
  public final void setUserName(final String userName) {
    this.userName = userName;
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
   * @param date The date and time of comment creation.
   */
  public final void setDate(final Date date) {
    this.date = date;
  }

  /**
   * Gets the ticket to which this comment belongs.
   *
   * @return The associated ticket as a TicketDto object.
   */
  public final TicketDto getTicket() {
    return ticket;
  }

  /**
   * Sets the ticket to which this comment belongs.
   *
   * @param ticket The associated ticket as a TicketDto object.
   */
  public final void setTicket(final TicketDto ticket) {
    this.ticket = ticket;
  }

  /**
   * Returns a string representation of the CommentDto object.
   *
   * @return A string containing comment details.
   */
  @Override
  public final String toString() {
    return (
      "CommentDto [commentId="
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

  /**
   * Constructs a CommentDto object with the specified parameters.
   *
   * @param commentId The unique identifier for the comment.
   * @param content   The content of the comment.
   * @param userName  The username associated with the comment.
   * @param date      The date and time when the comment was created.
   * @param ticket    The associated ticket as a TicketDto object.
   */
  public CommentDto(
      final Integer commentId,
      @NotEmpty final String content,
      @NotEmpty final String userName,
      final Date date,
      final TicketDto ticket
  ) {
    super();
    this.commentId = commentId;
    this.content = content;
    this.userName = userName;
    this.date = date;
    this.ticket = ticket;
  }

  /**
   * Default constructor for CommentDto class.
   */
  public CommentDto() {
    // Default constructor
  }
}
