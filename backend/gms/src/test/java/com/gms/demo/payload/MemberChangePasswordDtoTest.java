package com.gms.demo.payload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.gms.demo.payloads.MemberChangePasswordDto;

public class MemberChangePasswordDtoTest {

    private MemberChangePasswordDto memberChangePasswordDto1 =new MemberChangePasswordDto("oldPassword1", "newPassword1");
    private MemberChangePasswordDto memberChangePasswordDto2= new MemberChangePasswordDto("oldPassword2", "newPassword2");
    MemberChangePasswordDto memberChangePasswordDto3 = new MemberChangePasswordDto();
    @Test
    public void testGettersAndSetters() {
        assertEquals("oldPassword1", memberChangePasswordDto1.getOldpassword());
        memberChangePasswordDto1.setOldpassword("newOldPassword");
        assertEquals("newOldPassword", memberChangePasswordDto1.getOldpassword());

        assertEquals("newPassword1", memberChangePasswordDto1.getNewPassword());
        memberChangePasswordDto1.setNewPassword("newNewPassword");
        assertEquals("newNewPassword", memberChangePasswordDto1.getNewPassword());
    }

    @Test
    public void testHashCode() {
        assertEquals(memberChangePasswordDto1.hashCode(), memberChangePasswordDto1.hashCode());
        assertNotEquals(memberChangePasswordDto1.hashCode(), memberChangePasswordDto2.hashCode());
    }

    @Test
    public void testEquals() {
        assertTrue(memberChangePasswordDto1.equals(memberChangePasswordDto1));
        assertFalse(memberChangePasswordDto1.equals(null));
        assertFalse(memberChangePasswordDto1.equals("Not a MembersChangePasswordDto"));

        assertFalse(memberChangePasswordDto1.equals(memberChangePasswordDto2));
        assertFalse(memberChangePasswordDto2.equals(memberChangePasswordDto1));

        MemberChangePasswordDto memberChangePasswordDto3 = new MemberChangePasswordDto("oldPassword1", "newPassword1");
        assertTrue(memberChangePasswordDto1.equals(memberChangePasswordDto3));
        assertTrue(memberChangePasswordDto3.equals(memberChangePasswordDto1));
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("oldPassword1", memberChangePasswordDto1.getOldpassword());
        assertEquals("newPassword1", memberChangePasswordDto1.getNewPassword());
    }

}
