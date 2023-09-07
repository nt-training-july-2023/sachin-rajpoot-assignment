package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.*;

import com.gms.demo.payloads.ApiResponse;
import org.junit.jupiter.api.Test;

public class ApiResponseTest {

  private ApiResponse apiResponse = new ApiResponse("Success", true);

  @Test
  public void testGetAndSetMessage() {
    apiResponse.setMessage("New Message");
    assertEquals("New Message", apiResponse.getMessage());
  }

  @Test
  public void testGetAndSetSuccess() {
    apiResponse.setSuccess(false);
    assertFalse(apiResponse.isSuccess());
  }

  @Test
  public void testHashCode() {
    ApiResponse otherApiResponse = new ApiResponse("Success", true);
    assertEquals(apiResponse.hashCode(), otherApiResponse.hashCode());
  }

  @Test
  public void testEqualsWithEqualObjects() {
    ApiResponse otherApiResponse = new ApiResponse("Success", true);
    assertTrue(apiResponse.equals(otherApiResponse));
  }

  @Test
  public void testEqualsWithDifferentObjects() {
    ApiResponse otherApiResponse = new ApiResponse("Error", false);
    assertFalse(apiResponse.equals(otherApiResponse));
  }

  @Test
  public void testEqualsWithNull() {
    assertFalse(apiResponse.equals(null));
  }

  @Test
  public void testEqualsWithDifferentClass() {
    Object otherObject = new Object();
    assertFalse(apiResponse.equals(otherObject));
  }

  @Test
  public void testConstructor() {
    assertNotNull(apiResponse);
  }
}
