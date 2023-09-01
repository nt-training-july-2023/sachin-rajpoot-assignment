package com.gms.demo.payloads;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;

public class TicketDto {
    /**
     * The unique identifier for the ticket.
     */
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
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    /**
     * The date and time when the ticket was last updated.
     */
    @UpdateTimestamp
    private Date lastUpdatedOn;

    /**
     * The status of the ticket.
     */
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * The type of the ticket.
     */
    @NotEmpty
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    /**
     * The department associated with the ticket.
     */
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentDto departmentDto;

    /**
     * The member associated with the ticket.
     */
    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberDto memberDto;

    /**
     * Get the ticket ID.
     *
     * @return The ticket ID.
     */
    public Integer getTicketId() {
        return ticketId;
    }

    /**
     * Set the ticket ID.
     *
     * @param ticketId The ticket ID to set.
     */
    public void setTicketId(final Integer ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * Get the title of the ticket.
     *
     * @return The ticket title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title of the ticket.
     *
     * @param title The ticket title to set.
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Get the description of the ticket.
     *
     * @return The ticket description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the ticket.
     *
     * @param description The ticket description to set.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Get the creation date of the ticket.
     *
     * @return The creation date.
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * Set the creation date of the ticket.
     *
     * @param createdOn The creation date to set.
     */
    public void setCreatedOn(final Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Get the last updated date of the ticket.
     *
     * @return The last updated date.
     */
    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    /**
     * Set the last updated date of the ticket.
     *
     * @param lastUpdatedOn The last updated date to set.
     */
    public void setLastUpdatedOn(final Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    /**
     * Get the status of the ticket.
     *
     * @return The ticket status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Set the status of the ticket.
     *
     * @param status The ticket status to set.
     */
    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
     * Get the ticket type.
     *
     * @return The ticket type.
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * Set the ticket type.
     *
     * @param ticketType The ticket type to set.
     */
    public void setTicketType(final TicketType ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * Get the department associated with the ticket.
     *
     * @return The department.
     */
    public DepartmentDto getDepartment() {
        return departmentDto;
    }

    /**
     * Set the department associated with the ticket.
     *
     * @param department The department to set.
     */
    public void setDepartment(final DepartmentDto department) {
        this.departmentDto = department;
    }

    /**
     * Get the member associated with the ticket.
     *
     * @return The member.
     */
    public MemberDto getMember() {
        return memberDto;
    }

    /**
     * Set the member associated with the ticket.
     *
     * @param member The member to set.
     */
    public void setMember(final MemberDto member) {
        this.memberDto = member;
    }

    /**
     * Constructs a new TicketDto with the specified details.
     *
     * @param ticketId      The unique identifier for the ticket.
     * @param title         The title of the ticket.
     * @param description   The description of the ticket.
     * @param createdOn     The date when the ticket was created.
     * @param lastUpdatedOn The date when the ticket was last updated.
     * @param status        The status of the ticket.
     * @param ticketType    The type of the ticket.
     * @param departmentDto The department DTO associated with the ticket.
     * @param memberDto     The member DTO associated with the ticket.
     */
    public TicketDto(final Integer ticketId, @NotEmpty final String title, @NotEmpty final String description,
            final Date createdOn,
            final Date lastUpdatedOn, @NotEmpty final Status status, @NotEmpty final TicketType ticketType,
            final DepartmentDto departmentDto,
            final MemberDto memberDto) {
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.createdOn = createdOn;
        this.lastUpdatedOn = lastUpdatedOn;
        this.status = status;
        this.ticketType = ticketType;
        this.departmentDto = departmentDto;
        this.memberDto = memberDto;
    }

    /**
     * Generate a hash code for the TicketDto.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ticketId == null) ? 0 : ticketId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
        result = prime * result + ((lastUpdatedOn == null) ? 0 : lastUpdatedOn.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((ticketType == null) ? 0 : ticketType.hashCode());
        result = prime * result + ((departmentDto == null) ? 0 : departmentDto.hashCode());
        result = prime * result + ((memberDto == null) ? 0 : memberDto.hashCode());
        return result;
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
        TicketDto other = (TicketDto) obj;
        if (ticketId == null) {
            if (other.ticketId != null) {
                return false;
            }
        } else if (!ticketId.equals(other.ticketId)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (createdOn == null) {
            if (other.createdOn != null) {
                return false;
            }
        } else if (!createdOn.equals(other.createdOn)) {
            return false;
        }
        if (lastUpdatedOn == null) {
            if (other.lastUpdatedOn != null) {
                return false;
            }
        } else if (!lastUpdatedOn.equals(other.lastUpdatedOn)) {
            return false;
        }
        if (status != other.status) {
            return false;
        }
        if (ticketType != other.ticketType) {
            return false;
        }
        if (departmentDto == null) {
            if (other.departmentDto != null) {
                return false;
            }
        } else if (!departmentDto.equals(other.departmentDto)) {
            return false;
        }
        if (memberDto == null) {
            if (other.memberDto != null) {
                return false;
            }
        } else if (!memberDto.equals(other.memberDto)) {
            return false;
        }
        return true;
    }

}
