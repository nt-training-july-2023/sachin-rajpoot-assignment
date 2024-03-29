package com.gms.demo.serviceimpl;

import com.gms.demo.controller.DepartmentController;
import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.DepartmentOutDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.DepartmentService;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *Service Implementation for department.
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

  /**
   * The Department repository for database operations related to departments.
   */
  @Autowired
  private DepartmentRepo departmentRepo;

  /**
   * The ModelMapper for object mapping and transformation.
   */
  @Autowired
  private ModelMapper modelMapper;

  /**
   * The Member repository for database operations related to members.
   */
  @Autowired
  private MemberRepo memberRepo;

  /**
   * Number of items sent.
   */
  private final Integer numberOfItemToSend = 10;

  /**
   * Logger for logging.
   */
  private static final Logger DISPLAY = LoggerFactory
      .getLogger(DepartmentController.class);

  /*
   * Retrieves a list of all departments.
   *
   * @return A list of DepartmentOutDto objects
   *     representing all departments.
   */
  @Override
  public final List<DepartmentOutDto>
        getAllDepartment(final Integer pageNumber) {
    DISPLAY.info("Inside Department Service");
    Integer numberOfPages = numberOfItemToSend;
    Page<Department> departments =
        this.departmentRepo.findAll(
          PageRequest.of(pageNumber, numberOfPages, Sort.by("departmentName"))
        );
    List<DepartmentOutDto> departmentDtos = new ArrayList<>();
    departments.forEach(department ->
        departmentDtos.add(
        this.modelMapper.map(department, DepartmentOutDto.class)
      )
    );
    return departmentDtos;
  }

  /**
   * Retrieves a department by its unique identifier.
   *
   * @param departmentId The unique identifier of the
   *     department to retrieve.
   * @return The department represented as a DepartmentDto.
   * @throws ResourceNotFoundException If the
   *     department with the specified ID is not found.
   */
  @Override
  public final DepartmentOutDto
      getDepartmentById(final Integer departmentId) {
    DISPLAY.info("Inside Department Service");
    Department department =
        this.departmentRepo.findById(departmentId)
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Department",
            "departmentId",
            departmentId
          )
        );
    return this.modelMapper.map(department, DepartmentOutDto.class);
  }

  /**
   * Creates a new department if the provided credentials
   *     match those of an admin
   * member. This method requires administrator privileges.
   *
   * @param departmentDto The data transfer object containing department
   *                      information.
   * @param email         The email address of the administrator member.
   * @param password      The password of the administrator member.
   * @return The created department represented as a DepartmentDto if the
   *         credentials match; otherwise, returns null.
   */
  @Override
  public final DepartmentOutDto createDepartment2(
      final DepartmentDto departmentDto,
      final String email,
      final String password
  ) {
    DISPLAY.info("Inside Department Service");
    Member member = this.memberRepo.findByEmail(email);
    if (departmentDto.getDepartmentName().trim().equals("")) {
        return null;
    }
    if (member != null
        && member.getPassword().equals(password)
        && member.getRole() != null
        && member.getRole().equals(Role.ADMIN)) {
      departmentDto.setDepartmentName(departmentDto
          .getDepartmentName().trim());
      Department department =
            this.modelMapper.map(departmentDto, Department.class);
      return this.modelMapper.map(
            this.departmentRepo.save(department),
            DepartmentOutDto.class
          );
    }
    return null;
  }

  @Override
  public final ApiResponse deleteDepartment(final Integer departmentId) {
    DISPLAY.info("Inside Department Service");
    this.departmentRepo.findById(departmentId)
        .orElseThrow(() ->
          new ResourceNotFoundException(
            "Department",
            "departmentId",
            departmentId
          )
        );
    this.departmentRepo.deleteById(departmentId);
    return new ApiResponse(
      "Department with departmentId : "
       +
      departmentId
       +
      " is deleted successfully",
      true
    );
  }

  @Override
  public final List<DepartmentOutDto> getAllDepartmentNoPage() {
    DISPLAY.info("Inside Department Service");
    List<Department> departments = this.departmentRepo.findAll();
    List<DepartmentOutDto> departmentDtos = new ArrayList<>();
    departments.forEach(department ->
        departmentDtos.add(
        this.modelMapper.map(department, DepartmentOutDto.class)
      )
    );
    return departmentDtos;
  }
}
