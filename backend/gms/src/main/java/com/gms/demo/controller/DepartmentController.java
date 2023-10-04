package com.gms.demo.controller;

import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.DepartmentOutDto;
import com.gms.demo.service.DepartmentService;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
   * Logger for logging.
   */
  private static final Logger DISPLAY = LoggerFactory
      .getLogger(DepartmentController.class);

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
  @PostMapping("create/auth")
  final ResponseEntity<?> createDepartment2(
      @RequestBody @Valid final DepartmentDto departmentDto,
      @RequestHeader final String email,
      @RequestHeader final String password
  ) {
    DISPLAY.info("Inside Create Department method");
    DepartmentOutDto departmentDto2 =
        this.departmentService.createDepartment2(departmentDto,
          email, password);
    if (departmentDto2 != null) {
      DISPLAY.info("Department created success");
      return new ResponseEntity<>(new
              ApiResponse("Created Successfully", true), HttpStatus.CREATED);
    }
    return new ResponseEntity<>(new ApiResponse("Failed, Check Your"
            + "Inputs And Try Again.",
            false), HttpStatus.UNAUTHORIZED);
  }

  /**
   * Retrieves a list of all departments.
   *
   *@param pageNumber Page number to send.
   * @return A ResponseEntity containing a list of DepartmentOutDto objects with
   *         HTTP status 200 (OK).
   */
  @CrossOrigin
  @GetMapping("getAll/pageNumber/{pageNumber}")
  final ResponseEntity<List<DepartmentOutDto>> getAllDepartment(
      @PathVariable final Integer pageNumber
  ) {
    DISPLAY.info("Inside Get All Page Department method");
    return new ResponseEntity<>(
      this.departmentService.getAllDepartment(pageNumber),
      HttpStatus.OK
    );
  }

  /**
   * Retrieves all departments without pagination.
   *
   * @return A ResponseEntity containing a list of DepartmentOutDto
   *     and an HTTP status code.
   */
  @CrossOrigin
  @GetMapping("getAll/noPage")
  final ResponseEntity<List<DepartmentOutDto>> getAllDepartment() {
    DISPLAY.info("Inside Get All No Page Department method");
    return new ResponseEntity<>(
      this.departmentService.getAllDepartmentNoPage(),
      HttpStatus.OK
    );
  }

  /**
   * Deletes a department by its ID.
   *
   * @param departmentId The ID of the department to be deleted.
   * @return A ResponseEntity containing an ApiResponse and an HTTP status code.
   */
  @CrossOrigin
  @DeleteMapping("/delete/departmentId/{departmentId}")
  final ResponseEntity<ApiResponse> deleteDepartment(
      @PathVariable @Valid final Integer departmentId
  ) {
    DISPLAY.info("Inside Delete Department method");
    ApiResponse apiResponse =
        this.departmentService.deleteDepartment(departmentId);
    if (apiResponse != null) {
      DISPLAY.info("Department Deleted Success");
      return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
    }
    DISPLAY.info("Department Delete Fail");
    return new ResponseEntity<ApiResponse>(HttpStatus.BAD_REQUEST);
  }
}
