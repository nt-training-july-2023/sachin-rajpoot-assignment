package com.gms.demo.service;

import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.DepartmentOutDto;
import java.util.List;
/**
 * Service Implementation for depaertment.
 */

public interface DepartmentService {
  /**
   * Creates a new department based on the
   * provided department DTO, email, and
   * password.
   *
   * @param departmentDto The DTO containing department information.
   * @param email         The email associated with the department.
   * @param password      The password associated with the department.
   * @return The created department as a DTO.
   */
  DepartmentOutDto createDepartment2(
      DepartmentDto departmentDto,
      String email,
      String password
  );

  /**
   * Retrieves a department by its unique identifier.
   *
   * @param departmentId The unique identifier of the department.
   * @return The department DTO if found, otherwise null.
   */
  DepartmentOutDto getDepartmentById(Integer departmentId);

  /**
   * Retrieves a list of all departments as DepartmentOutDto objects.
   *
   *@param pageNumber page Number.
   *@return A list of DepartmentOutDto objects representing all departments.
   */
  List<DepartmentOutDto> getAllDepartment(Integer pageNumber);

  /**
   * get All Department No Page.
   *
   * @return DepartmentOutDto Department Out Dto
   */
  List<DepartmentOutDto> getAllDepartmentNoPage();

  /**
   * delete Department.
   *
   * @param departmentId department Id
   * @return ApiResponse ApiResponse
   */
  ApiResponse deleteDepartment(Integer departmentId);
}
