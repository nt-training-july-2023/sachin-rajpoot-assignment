package com.gms.demo.payloads;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;

/**
 * The `CommentOutDto` class represents a Data
 *    Transfer Object (DTO) for comment information.
 * It contains details about a comment, such as content,
 *     author username, and creation date.
 *
 * @version 1.0
 * @since 28-08-2023
 */
public class CommentOutDto {

  /**
   * The unique identifier for the comment.
   */
  private Integer commentId;

  /**
   * Gets the comment.
   *
   * @return commentId comment Id
   */
  public final Integer getCommentId() {
    return commentId;
  }

  /**
   * Sets the comment.
   *
   *@param commentIdx comment Id
   */
  public final void setCommentId(final Integer commentIdx) {
    this.commentId = commentIdx;
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
  public final String getContent() {
    return content;
  }

  /**
   * Sets the content of the comment.
   *
   * @param contentx The content to set for the comment.
   */
  public final void setContent(final String contentx) {
    this.content = contentx;
  }

  /**
   * Gets the username associated with the comment's author.
   *
   * @return The username of the comment's author.
   */
  public final String getUserName() {
    return userName;
  }

  /**
   * Sets the username associated with the comment's author.
   *
   * @param userNamex The username to set for the comment's author.
   */
  public final void setUserName(final String userNamex) {
    this.userName = userNamex;
  }

  /**
   * Gets the creation timestamp of the comment.
   *
   * @return The creation timestamp of the comment.
   */
  public final Date getDate() {
    return date;
  }

  /**
   *Sets the creation timestamp of the comment.
   *
   *@param datex The creation timestamp to set for the comment.
   */
  public final void setDate(final Date datex) {
    this.date = datex;
  }

  /**
   *Returns a string representation of the CommentOutDto object.
   *
   *@return A string containing comment details.
   */
  @Override
  public final String toString() {
    return
      "CommentOutDto [content="
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
   *Constructs a CommentOutDto object with the specified content,
   *    author username,
   *and creation date.
   *
   *@param contentx  The content of the comment.
   *@param userNamex The username of the comment's author.
   *@param datex     The creation date of the comment.
   */
  public CommentOutDto(
      final String contentx,
      final String userNamex,
      final Date datex
  ) {
    super();
    this.content = contentx;
    this.userName = userNamex;
    this.date = datex;
  }

  /**
   * Default constructor for CommentOutDto class.
   */
  public CommentOutDto() {
    super();
  }

  /**
   *constructor for CommentOutDto class.
   *
   *@param commentIdx  comment
   *@param contentx  content
   *@param userNamex user Name
   *@param datex date
   */

  public CommentOutDto(
      final Integer commentIdx,
      final String contentx,
      final String userNamex,
      final Date datex
  ) {
    super();
    this.commentId = commentIdx;
    this.content = contentx;
    this.userName = userNamex;
    this.date = datex;
  }

  /**
   *hashCode for CommentOutDto class.
   */
  @Override
  public final int hashCode() {
    return Objects.hash(commentId, content, date, userName);
  }

  /**
   *equals for CommentOutDto class.
   */
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
    return
      Objects.equals(commentId, other.commentId)
      &&
      Objects.equals(content, other.content)
      &&
      Objects.equals(date, other.date)
      &&
      Objects.equals(userName, other.userName);
  }
}
