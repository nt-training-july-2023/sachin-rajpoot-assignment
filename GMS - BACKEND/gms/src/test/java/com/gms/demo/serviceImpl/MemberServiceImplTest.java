package com.gms.demo.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.gms.demo.entity.Member;
import com.gms.demo.payloads.MemberDto;
import com.gms.demo.payloads.MemberLoginDto;
import com.gms.demo.repo.MemberRepo;
import com.gms.demo.serviceimpl.MemberServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {
  /** The MemberRepo instance for testing. */
  @Mock
  private MemberRepo memberRepo;

  /** The MemberServiceImpl instance for testing. */
  @InjectMocks
  private MemberServiceImpl memberServiceImpl;

  /** The ModelMapper instance for testing. */
  @Mock
  private ModelMapper modelMapper;

  /**
   * Set up method to initialize mocks and the mockMvc instance.
   */
  @Before
  public void setUp() {
    // THIS WILL SETUP/INITAILIZE MOCKITO INSIDE OUR CLASS
    MockitoAnnotations.initMocks(this);
    // INITIALIZE MOCKMVC -> SETUP ONLY FOR MEMBERCONTROLLER CLASS AND BUILD ON IT
    // this.mockMvc = MockMvcBuilders.standaloneSetup(memberServiceImpl).build();
    // modelMapper = new ModelMapper();
  }

  /** The MemberLoginDto instance for testing. */
  private MemberLoginDto memberLoginDto = new MemberLoginDto(
      "supersaiyan@nucleusteq.com",
      "132642677");
  /** The Member instance for testing. */
  private Member member = new Member(1, "Naruto", "supersaiyan3@nucleusteq.com", "132642677");

  /**
   * Test for successful member login.
   * @throws Exception
   */
  @Test
  public void loginTest() throws Exception {
    Mockito
        .when(this.memberRepo.findByEmail(memberLoginDto.getEmail()))
        .thenReturn(member);

    Boolean flag = this.memberServiceImpl.login(memberLoginDto);

    assertEquals(true, flag);
  }

  /**
   * Test for successful member creation.
   */
  @Test
  public void createMemberTest() {
    MemberDto memberDto2 = new MemberDto(1, "Naruto", "supersaiyan@nucleusteq.com", "132642677");
    Member member2 = new Member(1, "Naruto", "supersaiyan@nucleusteq.com", "132642677");
    // Member member2 = this.modelMapper.map(memberDto2, Member.class);
    Mockito
        .when(this.memberRepo.save(member2))
        .thenReturn(member2);
    Mockito
        .when(this.modelMapper.map(memberDto2, Member.class))
        .thenReturn(member2);

    Mockito
        .when(this.modelMapper.map(member2, MemberDto.class))
        .thenReturn(memberDto2);

    MemberDto memberDto3 = this.memberServiceImpl.createMember(memberDto2);

    assertEquals(memberDto2, memberDto3);
  }
}
