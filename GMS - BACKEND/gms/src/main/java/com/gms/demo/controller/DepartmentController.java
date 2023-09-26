package com.gms.demo.controller;

import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.DepartmentOutDto;
import com.gms.demo.service.DepartmentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The DepartmentController class handles HTTP requests related to departments.
 * It exposes several endpoints for creating and retrieving department
 * information.
 */
@RestController
@RequestMapping("/api/department/")
public class DepartmentController {

  /**
   * The DepartmentService is used to perform business logic related to
   * departments. It is autowired to ensure proper dependency injection.
   */
  @Autowired
  private DepartmentService departmentService;

  /**
   * Creates a new department.
   *
   * @param departmentDto The DepartmentDto object containing department
   *                      information.
   * @return A ResponseEntity containing the created DepartmentDto with HTTP
   *         status 201 (Created).
   */
  @CrossOrigin
  @PostMapping("create")
  ResponseEntity<DepartmentDto> createDepartment(
    @RequestBody @Valid final DepartmentDto departmentDto
  ) {
    return new ResponseEntity<>(
      this.departmentService.createDepartment(departmentDto),
      HttpStatus.CREATED
    );
  }

  /**
   * Creates a new department with email and password authentication.
   *
   * @param departmentDto The DepartmentDto object containing department
   *                      information.
   * @param email         The email associated with the department for
   *                      authentication.
   * @param password      The password associated with the department for
   *                      authentication.
   * @return A ResponseEntity containing the created DepartmentDto with HTTP
   *         status 201 (Created) if authentication is successful; otherwise,
   *         returns HTTP status 401 (Unauthorized).
   */
  @CrossOrigin
  @PostMapping("create/email/{email}/password/{password}")
  ResponseEntity<DepartmentOutDto> createDepartment2(
    @RequestBody @Valid final DepartmentDto departmentDto,
    @PathVariable final String email,
    @PathVariable final String password
  ) {
	  DepartmentOutDto departmentDto2 =
      this.departmentService.createDepartment2(departmentDto, email, password);
    if (departmentDto2 != null) {
      return new ResponseEntity<>(departmentDto2, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
  }

  /**
   * Retrieves a list of all departments.
   *
   * @return A ResponseEntity containing a list of DepartmentOutDto objects with
   *         HTTP status 200 (OK).
   */
  @CrossOrigin
  @GetMapping("getAll")
  ResponseEntity<List<DepartmentOutDto>> getAllDepartment() {
    return new ResponseEntity<>(
      this.departmentService.getAllDepartment(),
      HttpStatus.OK
    );
  }
}

