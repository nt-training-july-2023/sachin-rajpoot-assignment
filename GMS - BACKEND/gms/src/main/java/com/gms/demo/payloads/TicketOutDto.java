package com.gms.demo.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.gms.demo.entity.Comment;
import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.TicketType;

public class TicketOutDto {

	private String title;

	private String description;

	@CreationTimestamp // Automatically set the creation timestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@UpdateTimestamp // Automatically set the update timestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedOn;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private TicketType ticketType;

	private String departmentName;

	private String memberName;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public TicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(createdOn, departmentName, description, lastUpdatedOn, memberName, status, ticketType,
				title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketOutDto other = (TicketOutDto) obj;
		return Objects.equals(createdOn, other.createdOn) && Objects.equals(departmentName, other.departmentName)
				&& Objects.equals(description, other.description) && Objects.equals(lastUpdatedOn, other.lastUpdatedOn)
				&& Objects.equals(memberName, other.memberName) && status == other.status
				&& ticketType == other.ticketType && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "TicketOutDto [title=" + title + ", description=" + description + ", createdOn=" + createdOn
				+ ", lastUpdatedOn=" + lastUpdatedOn + ", status=" + status + ", ticketType=" + ticketType
				+ ", departmentName=" + departmentName + ", memberName=" + memberName + "]";
	}

	public TicketOutDto(String title, String description, Date createdOn, Date lastUpdatedOn, Status status,
			TicketType ticketType, String departmentName, String memberName) {
		super();
		this.title = title;
		this.description = description;
		this.createdOn = createdOn;
		this.lastUpdatedOn = lastUpdatedOn;
		this.status = status;
		this.ticketType = ticketType;
		this.departmentName = departmentName;
		this.memberName = memberName;
	}

	public TicketOutDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
