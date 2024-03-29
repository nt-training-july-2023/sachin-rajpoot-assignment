package com.gms.demo.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.gms.demo.entity.Department;
import com.gms.demo.entity.Member;
import com.gms.demo.entity.Role;
import com.gms.demo.exception.ResourceNotFoundException;
import com.gms.demo.payloads.ApiResponse;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.DepartmentOutDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.serviceimpl.DepartmentServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

	@Mock
	private DepartmentRepo departmentRepo;

	@Mock
	private MemberRepo memberRepo;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private DepartmentServiceImpl departmentService;

	List<Department> departments = new ArrayList<>();
	Department department = new Department();
	List<DepartmentOutDto> departmentDtos = new ArrayList<>();
	DepartmentOutDto departmentOutDto = new DepartmentOutDto();

	@Test
	public void testGetAllDepartmentNoPage() {
		List<Department> departments = new ArrayList<>();
		Department department = new Department();
		department.setDepartmentId(1);
		department.setDepartmentName("test");
		department.setMembers(null);
		department.setTickets(null);
		departments.add(department);
		when(departmentRepo.findAll()).thenReturn(departments);
		when(modelMapper.map(any(), eq(DepartmentOutDto.class))).thenReturn(new DepartmentOutDto());
		List<DepartmentOutDto> departmentOutDtos = departmentService.getAllDepartmentNoPage();
		assertNotNull(departmentOutDtos);
	}

	@Test
	public void testGetAllDepartmentWithPage() {
		Integer pageNumber = 0;
		Integer numberOfPages = 10;
		Pageable pageable = PageRequest.of(0, 8);
		List<Department> departments = new ArrayList<>();
		Department department1 = new Department();
		Department department2 = new Department();
		departments.add(department1);
		departments.add(department2);
		Page<Department> departmentPage = new PageImpl<>(departments, pageable, departments.size());
		when(this.departmentRepo.findAll(PageRequest.of(pageNumber, numberOfPages, Sort.by("departmentName"))))
				.thenReturn(departmentPage);
		List<DepartmentOutDto> departmentOutDtos = departmentService.getAllDepartment(0);
		assertNotNull(departmentOutDtos);
	}

	@Test
	public void testGetDepartmentByIdValidId() {
		Integer departmentId = 1;
		Department department = new Department();
		when(departmentRepo.findById(departmentId)).thenReturn(Optional.of(department));
		when(modelMapper.map(department, DepartmentOutDto.class)).thenReturn(new DepartmentOutDto());
		DepartmentOutDto departmentOutDto = departmentService.getDepartmentById(departmentId);
		assertNotNull(departmentOutDto);
	}

	@Test
	public void testGetDepartmentByIdInvalidId() {
		Integer departmentId = 1;
		when(departmentRepo.findById(departmentId)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> departmentService.getDepartmentById(departmentId));
	}

	@Test
	public void testCreateDepartment2ValidAdminCredentials() {
		String email = "admin@nucleusteq.com";
		String password = "adminPassword";
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentId(1);
		departmentDto.setDepartmentName("test");
		Department department = new Department();
		department.setDepartmentId(1);
		department.setDepartmentName("test");
		department.setMembers(null);
		department.setTickets(null);
		DepartmentOutDto departmentOutDto = new DepartmentOutDto();
		departmentOutDto.setDepartmentName("test");
		departmentOutDto.setMembers(null);
		departmentOutDto.setTickets(null);
		Member adminMember = new Member();
		adminMember.setRole(Role.ADMIN);
		adminMember.setEmail(email);
		adminMember.setPassword(password);
		when(memberRepo.findByEmail(email)).thenReturn(adminMember);
		when(modelMapper.map(departmentDto, Department.class)).thenReturn(department);
		when(departmentRepo.save(department)).thenReturn(department);
		when(modelMapper.map(department, DepartmentOutDto.class)).thenReturn(departmentOutDto);
		DepartmentOutDto createdDepartment = departmentService.createDepartment2(departmentDto, email, password);
		assertNotNull(createdDepartment);
	}

	@Test
	public void testCreateDepartment2InvalidAdminCredentials() {
		String email = "user@example.com";
		String password = "userPassword";
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("HR");
		when(memberRepo.findByEmail(email)).thenReturn(null);
		DepartmentOutDto createdDepartment = departmentService.createDepartment2(departmentDto, email, password);
		assertNull(createdDepartment);
	}

	@Test
	public void testDeleteDepartment() {
		Integer departmentId = 1;
		department.setDepartmentId(departmentId);
		when(departmentRepo.findById(departmentId)).thenReturn(Optional.of(department));
		ApiResponse response = departmentService.deleteDepartment(departmentId);
		verify(departmentRepo, times(1)).deleteById(departmentId);
		assertNotNull(response);
		assertTrue(response.isSuccess());
		assertEquals("Department with departmentId : " + departmentId + " is deleted successfully",
				response.getMessage());
	}

}
