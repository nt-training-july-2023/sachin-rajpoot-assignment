package com.gms.demo.repo;

import com.gms.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The DepartmentRepo interface provides methods to perform CRUD (Create, Read, Update, Delete) operations
 * on Department entities in the database.
 *
 * It extends the JpaRepository interface, which provides out-of-the-box methods for interacting with the database.
 *
 */
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
