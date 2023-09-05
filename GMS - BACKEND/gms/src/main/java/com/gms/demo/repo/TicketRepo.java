package com.gms.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gms.demo.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Integer> {

}