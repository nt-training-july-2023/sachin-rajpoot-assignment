package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.payloads.DepartmentDto;
import org.junit.jupiter.api.Test;

public class DepartmentDtoTest {

  private DepartmentDto departmentDto = new DepartmentDto();

  @Test
  public void testGetAndSetDepartmentId() {
    departmentDto.setDepartmentId(1);
    assertEquals(1, departmentDto.getDepartmentId());
  }

  @Test
  public void testGetAndSetName() {
    departmentDto.setDepartmentName("HR Department");
    assertEquals("HR Department", departmentDto.getDepartmentName());
  }



  @Test
  public void testHashCodeAndEquals() {
    DepartmentDto departmentDto1 = new DepartmentDto();
    departmentDto1.setDepartmentId(1);
    departmentDto1.setDepartmentName("HR Department");

    DepartmentDto departmentDto2 = new DepartmentDto();
    departmentDto2.setDepartmentId(1);
    departmentDto2.setDepartmentName("HR Department");

    DepartmentDto departmentDto3 = new DepartmentDto();
    departmentDto3.setDepartmentId(2);
    departmentDto3.setDepartmentName("IT Department");
    
    assertTrue(departmentDto1.equals(departmentDto2));

    assertEquals(departmentDto1.hashCode(), departmentDto2.hashCode());
    assertEquals(departmentDto1, departmentDto2);

    assertNotEquals(departmentDto1.hashCode(), departmentDto3.hashCode());
    assertNotEquals(departmentDto1, departmentDto3);
  }

  @Test
  public void testDefaultConstructor() {
    assertNotNull(new DepartmentDto());
  }
}
