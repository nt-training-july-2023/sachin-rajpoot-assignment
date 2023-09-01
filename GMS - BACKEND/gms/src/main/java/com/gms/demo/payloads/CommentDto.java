package com.gms.demo.payloads;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedDate;

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
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    /**
     * The ticket to which this comment belongs.
     */
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketDto ticketDto;

    /**
     * Get the comment's unique identifier.
     *
     * @return The comment ID.
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * Set the comment's unique identifier.
     *
     * @param commentId The comment ID to set.
     */
    public void setCommentId(final Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * Get the content of the comment.
     *
     * @return The comment content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content of the comment.
     *
     * @param content The comment content to set.
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Get the username associated with the comment.
     *
     * @return The username.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set the username associated with the comment.
     *
     * @param userName The username to set.
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * Get the date when the comment was created.
     *
     * @return The creation date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set the date when the comment was created.
     *
     * @param date The creation date to set.
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Get the associated ticket DTO.
     *
     * @return The associated ticket DTO.
     */
    public TicketDto getTicketDto() {
        return ticketDto;
    }

    /**
     * Set the associated ticket DTO.
     *
     * @param ticketDto The ticket DTO to set.
     */
    public void setTicketDto(final TicketDto ticketDto) {
        this.ticketDto = ticketDto;
    }

    /**
     * Constructs a new CommentDto with the specified details.
     *
     * @param commentId The unique identifier for the comment.
     * @param content   The content of the comment.
     * @param userName  The username associated with the comment.
     * @param date      The date when the comment was created.
     * @param ticketDto The associated ticket DTO.
     */
    public CommentDto(final Integer commentId, @NotEmpty final String content, @NotEmpty final String userName,
            final Date date,
            final TicketDto ticketDto) {
        this.commentId = commentId;
        this.content = content;
        this.userName = userName;
        this.date = date;
        this.ticketDto = ticketDto;
    }

    /**
     * Generate a hash code for the CommentDto.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((commentId == null) ? 0 : commentId.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((ticketDto == null) ? 0 : ticketDto.hashCode());
        return result;
    }

    /**
     * Check if this CommentDto is equal to another object.
     *
     * @param obj The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CommentDto other = (CommentDto) obj;
        if (commentId == null) {
            if (other.commentId != null) {
                return false;
            }
        } else if (!commentId.equals(other.commentId)) {
            return false;
        }
        if (content == null) {
            if (other.content != null) {
                return false;
            }
        } else if (!content.equals(other.content)) {
            return false;
        }
        if (userName == null) {
            if (other.userName != null) {
                return false;
            }
        } else if (!userName.equals(other.userName)) {
            return false;
        }
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        if (ticketDto == null) {
            if (other.ticketDto != null) {
                return false;
            }
        } else if (!ticketDto.equals(other.ticketDto)) {
            return false;
        }
        return true;
    }

    /**
     * Generate a string representation of the CommentDto.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "CommentDto [commentId=" + commentId + ", content=" + content + ", userName=" + userName + ", date="
                + date + ", ticketDto=" + ticketDto + "]";
    }

}
