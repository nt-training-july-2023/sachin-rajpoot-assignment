//package com.gms.demo.payload;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//
//import com.gms.demo.entity.Department;
//import com.gms.demo.entity.Role;
//import com.gms.demo.entity.Status;
//import com.gms.demo.entity.Ticket;
//import com.gms.demo.entity.TicketType;
//import com.gms.demo.payloads.MemberDto;
//
///**
// * Represents a test class for the memberDtoTest entity, covering member
// * Dto-related
// * operations.
// * Contains methods for testing getters, setters, and the toString() method.
// *
// * @author Sachin Singh Rajpoot
// * @version 1.0
// * @since Beginning of time
// */
//public class MemberDtoTest {
//    /** The memberDto entity instance for testing. */
//    private MemberDto memberDto = new MemberDto();
//
//    /**
//     * Test method for setters and getters of the memberDto entity.
//     */
//    @Test
//    public void settersAndGetterTest() {
//        memberDto.setName("Goku");
//        assertEquals("Goku", memberDto.getName());
//
//        memberDto.setMemberId(1);
//        assertTrue(1 == memberDto.getMemberId());
//
//        memberDto.setEmail("supersaiyan@nucleusteq.com");
//        assertEquals("supersaiyan@nucleusteq.com", memberDto.getEmail());
//
//        memberDto.setPassword("123456788");
//        assertEquals("123456788", memberDto.getPassword());
//
//        memberDto.setDepartment(new Department(1, "HR", null, null));
//        assertEquals(new Department(1, "HR", null, null), memberDto.getDepartment());
//
//        memberDto.setRole(Role.ADMIN); // You should set an actual Role value here.
//        assertEquals(Role.ADMIN, memberDto.getRole());
//
//        List<Ticket> tickets = new ArrayList<>();
//        tickets.add(
//                new Ticket(
//                        1,
//                        "title",
//                        "desc desc desc",
//                        null,
//                        null,
//                        Status.OPEN, // You should set an actual Status value here.
//                        TicketType.GREIVANCE, // You should set an actual TicketType value here.
//                        null,
//                        null,
//                        null));
//        memberDto.setTickets(tickets);
//        assertEquals(tickets, memberDto.getTickets());
//    }
//
//    /**
//     * Test method for generating the String representation of the Member entity.
//     */
//    @Test
//    public void toStringTest() {
//        String expected = "Member [memberId="
//                +
//                memberDto.getMemberId()
//                +
//                ", name="
//                +
//                memberDto.getName()
//                +
//                ", email="
//                +
//                memberDto.getEmail()
//                +
//                ", password="
//                +
//                memberDto.getPassword()
//                +
//                ", role="
//                +
//                memberDto.getPassword()
//                +
//                ", department="
//                +
//                memberDto.getDepartment()
//                +
//                ", tickets="
//                +
//                memberDto.getTickets()
//                +
//                "]";
//
//        assertEquals(expected, memberDto.toString());
//    }
//
//}
