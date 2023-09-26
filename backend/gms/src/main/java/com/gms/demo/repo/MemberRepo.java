package com.gms.demo.repo;

import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Represents a repository interface for Member entities.
 * Provides methods for accessing and managing Member data.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 */
@Repository
public interface MemberRepo extends JpaRepository<Member, Integer> {
  /**
   * Find a member by their email.
   *
   * @param email The email of the member to find.
   * @return The found member, or null if not found.
   */
  Member findByEmail(String email);

  boolean existsByEmailAndPasswordAndRole(
    String email,
    String password,
    Role admin
  );

  boolean existsByEmailAndPassword(String email, String password);
}
