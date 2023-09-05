package com.gms.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gms.demo.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
