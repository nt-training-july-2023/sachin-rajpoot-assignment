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
  @NotEmpty(message = "comment content cannot be empty or null")
  private String content;

  /**
   * The username associated with the comment.
   */
  @NotEmpty(message = "comment username cannot be empty or null")
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
   * @return The username of the comment's author.
   */
  public final String getUserName() {
    return userName;
  }

  /**
   * Sets the username associated with the comment.
   *
   * @param userNamex The username of the comment's author.
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
   * @return The associated ticket as a TicketDto object.
   */
  public final TicketDto getTicket() {
    return ticket;
  }

  /**
   * Sets the ticket to which this comment belongs.
   *
   * @param ticketx The associated ticket as a TicketDto object.
   */
  public final void setTicket(final TicketDto ticketx) {
    this.ticket = ticketx;
  }

  /**
   * Returns a string representation of the CommentDto object.
   *
   * @return A string containing comment details.
   */
  @Override
  public final String toString() {
    return
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
      "]";
  }

  /**
   * Constructs a CommentDto object with the specified parameters.
   *
   * @param commentIdx The unique identifier for the comment.
   * @param contentx   The content of the comment.
   * @param userNamex  The username associated with the comment.
   * @param datex      The date and time when the comment was created.
   * @param ticketx    The associated ticket as a TicketDto object.
   */
  public CommentDto(
      final Integer commentIdx,
      @NotEmpty final String contentx,
      @NotEmpty final String userNamex,
      final Date datex,
      final TicketDto ticketx
  ) {
    super();
    this.commentId = commentIdx;
    this.content = contentx;
    this.userName = userNamex;
    this.date = datex;
    this.ticket = ticketx;
  }

  /**
   * Default constructor for CommentDto class.
   */
  public CommentDto() {
    super();
  }
}
