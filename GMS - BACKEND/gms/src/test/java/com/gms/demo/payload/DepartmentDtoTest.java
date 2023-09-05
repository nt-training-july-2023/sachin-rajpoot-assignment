package com.gms.demo.payload;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.gms.demo.payloads.DepartmentDto;
import com.gms.demo.payloads.MemberLoginDto;

public class DepartmentDtoTest {
	/** The DepartmentDto entity instance for testing. */
	private DepartmentDto departmentDto = new DepartmentDto();
	
	/**
	 * Test method for setters and getters of the Member Login Dto entity.
	 */
	@Test
	public void settersAndGetterTest() {

		departmentDto.setDepartmentId(1);
		assertEquals(1, departmentDto.getDepartmentId());
		
		departmentDto.setName("HR");
		assertEquals("HR", departmentDto.getName());
		
		

	}
}
