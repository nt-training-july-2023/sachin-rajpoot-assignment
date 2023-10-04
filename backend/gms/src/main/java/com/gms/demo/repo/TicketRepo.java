package com.gms.demo.repo;

import com.gms.demo.entity.Status;
import com.gms.demo.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * The TicketRepo interface provides methods
 * to perform CRUD (Create, Read, Update,
 * Delete) operations
 * on Ticket entities in the database.
 * It extends the JpaRepository interface,
 * which provides out-of-the-box methods
 * for interacting with the database.
 *
 */
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
  /**
   *find All By Member Id Order By Status Asc.
   *
   * @param memberId memberId
   * @param pageable pageable
   * @return members
   */
  @Query(
      "SELECT t FROM Ticket t "
       +
      "WHERE t.member.id = :memberId "
       +
      "ORDER BY t.status ASC"
  )
  Page<Ticket> findAllByMemberIdOrderByStatusAsc(
      @Param("memberId") Integer memberId,
      Pageable pageable
  );

  /**
   * find All By Member Id And Status.
   *
   * @param memberId member Id
   * @param status status
   * @param pageable pageable
   * @return members
   */
  @Query(
      "SELECT t FROM Ticket t "
       +
      "WHERE t.member.id = :memberId "
       +
      "AND t.status = :status"
  )
  Page<Ticket> findAllByMemberIdAndStatus(
      @Param("memberId") Integer memberId,
      @Param("status") Status status,
      Pageable pageable
  );

  /**
   * find By Status.
   *
   * @param status status
   * @param pageable pageable
   * @return members
   */
  Page<Ticket> findByStatus(Status status, Pageable pageable);

  /**
   * find All By Department Id And Status.
   *
   * @param departmentId department Id
   * @param status status
   * @param pageable pageable
   * @return members
   */
  @Query(
      "SELECT t FROM Ticket t "
       +
      "WHERE t.department.id = :departmentId "
       +
      "AND t.status = :status"
  )
  Page<Ticket> findAllByDepartmentIdAndStatus(
      @Param("departmentId") Integer departmentId,
      @Param("status") Status status,
      Pageable pageable
  );

  /**
   * find All By Department Id.
   *
   * @param departmentId department Id
   * @param pageable pageable
   * @return members
   */
  @Query("SELECT t FROM Ticket t " + "WHERE t.department.id = :departmentId")
  Page<Ticket> findAllByDepartmentId(
      @Param("departmentId") Integer departmentId,
      Pageable pageable
  );
}
