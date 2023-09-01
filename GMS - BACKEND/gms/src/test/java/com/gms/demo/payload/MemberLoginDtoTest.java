package com.gms.demo.payload;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import com.gms.demo.payloads.MemberLoginDto;

/**
 * Represents a test class for the Member Login Dto entity, covering Member
 * Login-related
 * operations.
 * Contains methods for testing getters, setters, and the toString() method.
 *
 * @author Sachin Singh Rajpoot
 * @version 1.0
 * @since Beginning of time
 */
public class MemberLoginDtoTest {
    /** The MemberLoginDto entity instance for testing. */
    private MemberLoginDto memberLoginDto = new MemberLoginDto();

    /**
     * Test method for setters and getters of the Member Login Dto entity.
     */
    @Test
    public void settersAndGetterTest() {

        memberLoginDto.setEmail("supersaiyan@nucleusteq.com");
        assertEquals("supersaiyan@nucleusteq.com", memberLoginDto.getEmail());

        memberLoginDto.setPassword("123456788");
        assertEquals("123456788", memberLoginDto.getPassword());

    }

    /**
     * Test method for generating the String representation of the MemberLoginDto entity.
     */
    @Test
    public void toStringTest() {
        String expected = "email=" + memberLoginDto.getEmail() + ", password=" + memberLoginDto.getPassword() + "]";

        assertEquals(expected, memberLoginDto.toString());
    }

}
