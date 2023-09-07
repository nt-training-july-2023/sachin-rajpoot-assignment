package com.gms.demo.repo;

import com.gms.demo.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The TicketRepo interface provides methods to perform CRUD (Create, Read, Update, Delete) operations
 * on Ticket entities in the database.
 *
 * It extends the JpaRepository interface, which provides out-of-the-box methods for interacting with the database.
 *
 */
public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}
