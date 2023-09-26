package com.gms.demo.payloads;

import com.gms.demo.entity.Status;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;

public class TicketUpdateStatusInDto {

  //	@NotEmpty
  Status status;

  //	@NotEmpty
  CommentDto comment;

  Integer ticketId;

  public final Status getStatus() {
    return status;
  }

  public final void setStatus(final Status status) {
    this.status = status;
  }

  public final CommentDto getComment() {
    return comment;
  }

  public final void setComment(final CommentDto comment) {
    this.comment = comment;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(comment, status);
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
    TicketUpdateStatusInDto other = (TicketUpdateStatusInDto) obj;
    return Objects.equals(comment, other.comment) && status == other.status;
  }

  @Override
  public final String toString() {
    return (
      "TicketUpdateStatusInDto [status=" + status + ", comment=" + comment + "]"
    );
  }

  public TicketUpdateStatusInDto(
    @NotEmpty final Status status,
    @NotEmpty final CommentDto comment
  ) {
    super();
    this.status = status;
    this.comment = comment;
  }

  public TicketUpdateStatusInDto() {
    super();
    // TODO Auto-generated constructor stub
  }
}
