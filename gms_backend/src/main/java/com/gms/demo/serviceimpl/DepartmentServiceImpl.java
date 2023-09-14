package com.gms.demo.serviceimpl;

import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.DepartmentOutDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.DepartmentService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
   * Creates a new department.
   *
   * @param departmentDto The data transfer object containing department information.
   * @return The created department represented as a DepartmentDto.
   */
  @Override
  public DepartmentDto createDepartment(
    @RequestBody @Valid final DepartmentDto departmentDto
  ) {
    Department department =
      this.modelMapper.map(departmentDto, Department.class);
    return this.modelMapper.map(
        this.departmentRepo.save(department),
        DepartmentDto.class
      );
  }

  /**
   * Retrieves a list of all departments.
   *
   * @return A list of DepartmentOutDto objects representing all departments.
   */
  @Override
  public List<DepartmentOutDto> getAllDepartment() {
    List<Department> departments = this.departmentRepo.findAll();
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
   * @param departmentId The unique identifier of the department to retrieve.
   * @return The department represented as a DepartmentDto.
   * @throws ResourceNotFoundException If the department with the specified ID is not found.
   */
  @Override
  public DepartmentOutDto getDepartmentById(final Integer departmentId) {
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
   * Creates a new department if the provided credentials match those of an admin member.
   * This method requires administrator privileges.
   *
   * @param departmentDto The data transfer object containing department information.
   * @param email The email address of the administrator member.
   * @param password The password of the administrator member.
   * @return The created department represented as a DepartmentDto if the credentials match;
   *         otherwise, returns null.
   */
  @Override
  public DepartmentOutDto createDepartment2(
    final DepartmentDto departmentDto,
    final String email,
    final String password
  ) {
    System.out.println("inside service");
    Member member = this.memberRepo.findByEmail(email);
    if (member != null) {
      System.out.println("inside 1");
      System.out.println("Role " + member.getRole());
      System.out.println(member.getRole().equals(Role.ADMIN));
      if (
        member.getPassword().equals(password)
        && member.getRole() != null
        && member.getRole().equals(Role.ADMIN)
      ) {
        System.out.println("Hail Admin!!!!!!!!!");
        System.out.println("This is what you've created" + departmentDto);

        Department department =
          this.modelMapper.map(departmentDto, Department.class);
        return this.modelMapper.map(
            this.departmentRepo.save(department),
            DepartmentOutDto.class
          );
      }
    }
    return null;
  }
}