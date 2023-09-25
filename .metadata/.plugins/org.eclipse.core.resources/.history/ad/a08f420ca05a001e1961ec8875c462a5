package com.gms.demo.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.repo.DepartmentRepo;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.service.DepartmentService;
import com.gms.demo.service.MemberService;

import com.gms.demo.controller.DepartmentController;
import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.DepartmentOutDto;
import com.gms.demo.service.DepartmentService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ValidationException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;
		

    @InjectMocks
    private DepartmentController departmentController;

    @Test
    public void testCreateDepartment_ValidInput() {

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentId(1);
        departmentDto.setDepartmentName("HR");
        when(departmentService.createDepartment(departmentDto)).thenReturn(departmentDto);


        ResponseEntity<DepartmentDto> response = departmentController.createDepartment(departmentDto);

    
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(departmentDto, response.getBody());
    }

    @Test
    public void testCreateDepartment2_ValidCredentials() {
    
        DepartmentDto departmentDto = new DepartmentDto();
        String email = "test@example.com";
        String password = "password";
        DepartmentOutDto departmentOutDto = new DepartmentOutDto();
        when(departmentService.createDepartment2(departmentDto, email, password)).thenReturn(departmentOutDto);

  
        ResponseEntity<DepartmentOutDto> response = departmentController.createDepartment2(departmentDto, email, password);

    
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(departmentOutDto, response.getBody());
    }

    @Test
    public void testCreateDepartment2_InvalidCredentials() {
 
        DepartmentDto departmentDto = new DepartmentDto();
        String email = "invalid@example.com";
        String password = "invalidPassword";
        when(departmentService.createDepartment2(departmentDto, email, password)).thenReturn(null);

    
        ResponseEntity<DepartmentOutDto> response = departmentController.createDepartment2(departmentDto, email, password);

 
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testGetAllDepartment() {
  
        List<DepartmentOutDto> departmentOutDtoList = Arrays.asList(new DepartmentOutDto(), new DepartmentOutDto());
        when(departmentService.getAllDepartment()).thenReturn(departmentOutDtoList);


        ResponseEntity<List<DepartmentOutDto>> response = departmentController.getAllDepartment();

   
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departmentOutDtoList, response.getBody());
    }
}
