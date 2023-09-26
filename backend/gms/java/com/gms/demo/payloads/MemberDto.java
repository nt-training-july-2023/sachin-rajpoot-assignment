package com.gms.demo.payloads;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gms.demo.entity.Department;
import com.gms.demo.entity.Role;
import com.gms.demo.entity.Ticket;
import java.util.ArrayList;
import java.util.List;
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
	public Boolean getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(Boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

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
//	@NotEmpty
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
	@ManyToOne
	private DepartmentDto department;

	/**
	 * The list of tickets associated with the member.
	 */
//	
//	@JsonManagedReference
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ticket> tickets = new ArrayList<>();

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
	public void setDepartment(final DepartmentDto department) {
		this.department = department;
	}

	/**
	 * Get the list of tickets associated with the member.
	 *
	 * @return The list of tickets.
	 */
	public List<Ticket> getTickets() {
		return tickets;
	}

	/**
	 * Set the list of tickets associated with the member.
	 *
	 * @param tickets The list of tickets to set.
	 */
	public void setTickets(final List<Ticket> tickets) {
		this.tickets = tickets;
	}

	/**
	 * Generates a string representation of the MemberDto object.
	 *
	 * @return The string representation.
	 */
	@Override
	public String toString() {
		return ("Member [memberId=" + memberId + ", name=" + name + ", email=" + email + ", password=" + password + ", isFirstLogin=" +isFirstLogin +
				 ", role=" + role + ", department=" + department + ", tickets=" + tickets + "]");
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
			final Boolean isFirstLogin ,@NotEmpty final Role role, final DepartmentDto department, final List<Ticket> tickets) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.isFirstLogin=isFirstLogin;
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
			@NotEmpty @Size(min = 5, message = "Password Must be minimum 5 and maximum 12 character.") final String password) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
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
			@NotEmpty @Size(min = 5, message = "Password Must be minimum 5 and maximum 12 character.") final String password,
			DepartmentDto departmentDto) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.department = departmentDto;
	}
	
	/**
	 * Default constructor for creating a MemberDto object.
	 */
	public MemberDto() {
		super();
	}

}
