package com.gms.demo.filter;

import com.gms.demo.repo.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

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
  private MemberRepo memberRepo;

  /**
   * Constructor.
   *
   * @param memberRepox member repo
   *
   */
  public FilterConfiguration(final MemberRepo memberRepox) {
    super();
    this.memberRepo = memberRepox;
  }

  /**
   *filter method.
   *
   * @return CorsFilter     filter
   */

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.addAllowedOrigin("http://localhost:3000");
    config.addAllowedHeader("*");
    config.addAllowedMethod("GET");
    config.addAllowedMethod("POST");
    config.addAllowedMethod("PUT");
    config.addAllowedMethod("DELETE");
    org.springframework.web.cors.UrlBasedCorsConfigurationSource source =
            new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
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
