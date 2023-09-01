package com.gms.demo;

import com.gms.demo.repo.MemberRepo;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@SpringBootTest
class GmsApplicationTests {
	@MockBean
	private MemberRepo memberRepo;

	@Test
	void contextLoads() {
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
