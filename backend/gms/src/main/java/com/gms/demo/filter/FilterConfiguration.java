package com.gms.demo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.gms.demo.repo.MemberRepo;
//import com.gms.repo.EmployeeRepo;
/**
 * Security Filter Configuration.
 *
 * @author Sachin
 */
@Configuration
public class FilterConfiguration {
  /**
   * Autowired EmployeeRepo.
   */
  @Autowired
  MemberRepo memberRepo;
  /**
   * Constructor.
   *
   * @param employeeRepo EmployeeRepo
   */
  public FilterConfiguration(MemberRepo memberRepo) {
    super();
    this.memberRepo = memberRepo;
  }
  @Bean
  public CorsFilter corsFilter() {
      org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.addAllowedOrigin("http://localhost:3000"); // Replace with your frontend URL
      config.addAllowedHeader("*"); // Allow all headers for demonstration purposes; restrict as needed
      config.addAllowedMethod("GET");
      config.addAllowedMethod("POST");
      config.addAllowedMethod("PUT");
      config.addAllowedMethod("DELETE");
      source.registerCorsConfiguration("/**", config);
      return new CorsFilter(source);
  }
  /**
   * Security Filter Configuration.
   *
   * @return FilterRegistrationBean
   */
  @Bean
  @DependsOn("corsFilter")
  FilterRegistrationBean<SecurityFilter> registrationBeanAdmin() {
    FilterRegistrationBean<SecurityFilter> registrationBean =
        new FilterRegistrationBean<SecurityFilter>();
    registrationBean.setFilter(new SecurityFilter(memberRepo));
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }
}