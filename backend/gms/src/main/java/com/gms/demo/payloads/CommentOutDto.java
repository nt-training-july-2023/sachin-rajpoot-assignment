package com.gms.demo.payloads;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The `CommentOutDto` class represents a Data Transfer Object (DTO) for comment information.
 * It contains details about a comment, such as content, author username, and creation date.
 *
 * @version 1.0
 * @since 28-08-2023
 */
public class CommentOutDto {

  /**
   * The unique identifier for the comment.
   */
  private Integer commentId;

  public final Integer getCommentId() {
    return commentId;
  }

  public final void setCommentId(final Integer commentId) {
    this.commentId = commentId;
  }

  /**
   * The content of the comment.
   */
  private String content;

  /**
   * The username associated with the comment's author.
   */
  private String userName;

  /**
   * The creation timestamp of the comment.
   */
  @CreationTimestamp // Automatically set the creation timestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date date;

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
   * @param content The content to set for the comment.
   */
  public void setContent(final String content) {
    this.content = content;
  }

  /**
   * Gets the username associated with the comment's author.
   *
   * @return The username of the comment's author.
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets the username associated with the comment's author.
   *
   * @param userName The username to set for the comment's author.
   */
  public void setUserName(final String userName) {
    this.userName = userName;
  }

  /**
   * Gets the creation timestamp of the comment.
   *
   * @return The creation timestamp of the comment.
   */
  public Date getDate() {
    return date;
  }

  /**
   * Sets the creation timestamp of the comment.
   *
   * @param date The creation timestamp to set for the comment.
   */
  public void setDate(final Date date) {
    this.date = date;
  }

  /**
   * Returns a string representation of the CommentOutDto object.
   *
   * @return A string containing comment details.
   */
  @Override
  public String toString() {
    return (
      "CommentOutDto [content=" +
      content +
      ", userName=" +
      userName +
      ", date=" +
      date +
      "]"
    );
  }

  /**
   * Constructs a CommentOutDto object with the specified content, author username, and creation date.
   *
   * @param content  The content of the comment.
   * @param userName The username of the comment's author.
   * @param date     The creation date of the comment.
   */
  public CommentOutDto(
    final String content,
    final String userName,
    final Date date
  ) {
    super();
    this.content = content;
    this.userName = userName;
    this.date = date;
  }

  /**
   * Default constructor for CommentOutDto class.
   */
  public CommentOutDto() {
    // Default constructor
  }

  public CommentOutDto(
    final Integer commentId,
    final String content,
    final String userName,
    final Date date
  ) {
    super();
    this.commentId = commentId;
    this.content = content;
    this.userName = userName;
    this.date = date;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(commentId, content, date, userName);
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
    CommentOutDto other = (CommentOutDto) obj;
    return (
      Objects.equals(commentId, other.commentId) &&
      Objects.equals(content, other.content) &&
      Objects.equals(date, other.date) &&
      Objects.equals(userName, other.userName)
    );
  }
}
