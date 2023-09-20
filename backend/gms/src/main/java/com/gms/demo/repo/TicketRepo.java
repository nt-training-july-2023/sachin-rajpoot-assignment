package com.gms.demo.repo;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Status;
import com.gms.demo.entity.Ticket;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The TicketRepo interface provides methods to perform CRUD (Create, Read, Update, Delete) operations
 * on Ticket entities in the database.
 *
 * It extends the JpaRepository interface, which provides out-of-the-box methods for interacting with the database.
 *
 */
public interface TicketRepo extends JpaRepository<Ticket, Integer> {

//	 @Query("SELECT t FROM Ticket t ORDER BY " +
//	           "CASE t.status " +
//	           "WHEN 'OPEN' THEN 1 " +
//	           "WHEN 'PROGRESS' THEN 2 " +
//	           "WHEN 'CLOSED' THEN 3 " +
//	           "ELSE 4 " +
//	           "END")
//	 Page<Ticket> getAllTicketsSortedByStatus(Pageable pageable);
	 
//	 Page<Ticket> findByStatus(Status status, Pageable pageable);
	
//	 @Query("SELECT t FROM ticket t ORDER BY " +
//    "CASE t.status " + 
//    "WHEN 'OPEN' THEN 1 " +
//    "WHEN 'PROGRESS' THEN 2 " +
//    "WHEN 'CLOSED' THEN 3 " +
//    "ELSE 4 " +
//    "END") 
//	 Page<Ticket> findByMember(Member member, Pageable pageable);
	
//	@Query("SELECT * FROM ticket  WHERE ticket.member_id = :member_ " +
//		       "ORDER BY t.status")
//		Page<Ticket> findByMember(Member member, Pageable pageable);
	
	 @Query("SELECT t FROM Ticket t " +
	           "WHERE t.member.id = :memberId " +
	           "ORDER BY t.status ASC")
	    Page<Ticket> findAllByMemberIdOrderByStatusAsc(
	            @Param("memberId") Integer memberId, Pageable pageable);


//	Page<Ticket> findByMember(Member member, PageRequest of);
	 
	 @Query("SELECT t FROM Ticket t " +
	           "WHERE t.member.id = :memberId " +
	           "AND t.status = :status")
	    Page<Ticket> findAllByMemberIdAndStatus(
	            @Param("memberId") Integer memberId,
	            @Param("status") Integer status,
	            Pageable pageable);
	 
	 Page<Ticket> findByStatus(Status status, Pageable pageable);
	 
//	 Page<Ticket> findByDepartment(String departmentName);
	 @Query("SELECT t FROM Ticket t " +
	           "WHERE t.department.id = :departmentId " +
	           "AND t.status = :status")
	    Page<Ticket> findAllByDepartmentIdAndStatus(
	            @Param("departmentId") Integer departmentId,
	            @Param("status") Integer status,
	            Pageable pageable);
	 
	 @Query("SELECT t FROM Ticket t " +
	           "WHERE t.department.id = :departmentId")
	    Page<Ticket> findAllByDepartmentId(
	            @Param("departmentId") Integer departmentId,
	            Pageable pageable);
	 
}
