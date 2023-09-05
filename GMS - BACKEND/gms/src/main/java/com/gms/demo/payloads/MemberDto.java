package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gms.demo.entity.Department;
import com.gms.demo.entity.Role;
import com.gms.demo.entity.Ticket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Represents an Member Login DTO.
 *
 * @author Sachin Singh Rajpoot
 *
 * @version 1.0
 * @since Begining of time
 */

public class MemberDto {
	/**
	 * The unique identifier for the member.
	 */
	private Integer memberId;

	/**
	 * The name of the member.
	 */
	@NotEmpty
	private String name;

	/**
	 * The email address of the member.
	 */
	@NotEmpty
	@Email(message = "Email is not valid.")
	@Column(unique = true)
	private String email;

	/**
	 * The minimum sixe for password.
	 */
	private static final int MIN_SIZE_PASSWORD = 5;

	/**
	 * The password associated with the member's account. Password must be between 5
	 * and 12 characters in length.
	 */
	@NotEmpty
	@Size(min = MIN_SIZE_PASSWORD, message = "PASSWORD MUST BE MINIMUM 5 CHARACTER.")
	private String password;

	/**
	 * The Check for the first login of the member.
	 */
//  @NotEmpty
	private Boolean isFirstLogin;

	/**
	 * The role of the member in the system.
	 */
	// @NotEmpty
	@Enumerated(EnumType.STRING)
	private Role role;

	/**
	 * The department associated with the member.
	 */
	@JsonIgnore
	@ManyToOne
	private DepartmentDto department;

	/**
	 * The list of tickets associated with the member.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TicketDto> tickets = new ArrayList<>();

	/**
	 * Get the member ID.
	 *
	 * @return The member ID.
	 */
	public Integer getMemberId() {
		return memberId;
	}

	/**
	 * Set the member ID.
	 *
	 * @param memberId The member ID to set.
	 */
	public void setMemberId(final Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * Get the name of the member.
	 *
	 * @return The member name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the member.
	 *
	 * @param name The member name to set.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get the email of the member.
	 *
	 * @return The member email.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Set the email of the member.
	 *
	 * @param email The member email to set.
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Get the password of the member.
	 *
	 * @return The member password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Set the password of the member.
	 *
	 * @param password The member password to set.
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Get the role of the member.
	 *
	 * @return The member role.
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Set the role of the member.
	 *
	 * @param role The member role to set.
	 */
	public void setRole(final Role role) {
		this.role = role;
	}

	/**
	 * Get the department of the member.
	 *
	 * @return The member department.
	 */
	public DepartmentDto getDepartment() {
		return department;
	}

	/**
	 * Set the department of the member.
	 *
	 * @param department The member department to set.
	 */
	public void setDepartment(final DepartmentDto DepartmentDto) {
		this.department = DepartmentDto;
	}
	
	

	/**
	 * Get the list of tickets associated with the member.
	 *
	 * @return The list of tickets.
	 */
	public List<TicketDto> getTickets() {
		return tickets;
	}

	/**
	 * Set the list of tickets associated with the member.
	 *
	 * @param tickets The list of tickets to set.
	 */
	public void setTickets(final List<TicketDto> tickets) {
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", isFirstLogin=" + isFirstLogin + ", role=" + role + ", department=" + department + ", tickets="
				+ tickets + "]";
	}

	/**
	 * Constructor for creating a MemberDto object.
	 *
	 * @param memberId   The member ID.
	 * @param name       The name of the member.
	 * @param email      The email of the member.
	 * @param password   The password of the member.
	 * @param role       The role of the member.
	 * @param department The department of the member.
	 * @param tickets    The list of tickets associated with the member.
	 */
	public MemberDto(final Integer memberId, @NotEmpty final String name, @NotEmpty @Email final String email,
			@NotEmpty @Size(min = '5', message = "Password Must be minimum 5 and maximum 12 character.") final String password,
			@NotEmpty final Role role, final DepartmentDto department, final List<TicketDto> tickets) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.department = department;
		this.tickets = tickets;
	}

	/**
	 * Constructor for creating a MemberDto object.
	 *
	 * @param memberId The member ID.
	 * @param name     The name of the member.
	 * @param email    The email of the member.
	 * @param password The password of the member.
	 */
	public MemberDto(final Integer memberId, @NotEmpty final String name, @NotEmpty @Email final String email,
			@NotEmpty @Size(min = '5', message = "Password Must be minimum 5 and maximum 12 character.") final String password) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/**
	 * Default constructor for creating a MemberDto object.
	 */
	public MemberDto() {
		super();
	}

	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public static int getMinSizePassword() {
		return MIN_SIZE_PASSWORD;
	}

	@Override
	public int hashCode() {
		return Objects.hash(department, email, isFirstLogin, memberId, name, password, role, tickets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberDto other = (MemberDto) obj;
		return Objects.equals(department, other.department) && Objects.equals(email, other.email)
				&& Objects.equals(isFirstLogin, other.isFirstLogin) && Objects.equals(memberId, other.memberId)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password) && role == other.role
				&& Objects.equals(tickets, other.tickets);
	}

	public MemberDto(Integer memberId, @NotEmpty String name,
			@NotEmpty @Email(message = "Email is not valid.") String email,
			@NotEmpty @Size(min = 5, message = "PASSWORD MUST BE MINIMUM 5 CHARACTER.") String password,
			Boolean isFirstLogin, Role role, DepartmentDto department, List<TicketDto> tickets) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isFirstLogin = isFirstLogin;
		this.role = role;
		this.department = department;
		this.tickets = tickets;
	}
	

}
