package com.gms.demo.filter;

import com.gms.demo.controller.DepartmentController;
import com.gms.demo.entity.Role;
import com.gms.demo.repo.MemberRepo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Security Filter class.
 */
@Component
public class SecurityFilter implements Filter {

  /**
   * Employee Repo Autowired.
   */
  @Autowired
  private MemberRepo memberRepo;

  /**
   * Logger for logging.
   */
  private static final Logger DISPLAY = LoggerFactory
      .getLogger(DepartmentController.class);

  /**
   * List to store Admin urls.
   */
  private static List<String> adminUrls = new ArrayList<String>();

  static {
    //    MEMBER URLS
    adminUrls.add("/api/create/");
    adminUrls.add("/api/member/getAll/auth");
    adminUrls.add("/api/member/delete/memberId/{memberId}");

    //    DEPARTMENT URLS
    adminUrls.add("/api/department/create/auth");
    adminUrls.add("/api/department/delete/departmentId/{departmentId}");
    adminUrls.add("/api/department/getAll");
  }

  /**
   *Constructor.
   *
   *@param memberRepox member repo.
   */
  public SecurityFilter(final MemberRepo memberRepox) {
    this.memberRepo = memberRepox;
  }

  /**
   * Checkk if url is in the admin urls or not.
   *
   * @param currentUrl String
   * @return Boolean
   */
  public final Boolean checkAdminUrl(final String currentUrl) {
    if (adminUrls.contains(currentUrl)) {
      return true;
    }
    return false;
  }

  /**
   * Filter for urls.
   */
  @Override
  public final void doFilter(
      final ServletRequest request,
      final ServletResponse response,
      final FilterChain chain
  ) throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    DISPLAY.info(
        "HEADERS RECEIVED -> EMAIL : "
         +
        httpServletRequest.getHeader("email")
         +
        " AND PASSWORD : "
         +
        httpServletRequest.getHeader("password")
    );
    String email = httpServletRequest.getHeader("email");
    String password = httpServletRequest.getHeader("password");

    String currentUrl = httpServletRequest.getRequestURI();
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    DISPLAY.info(currentUrl + email + password);
    if (currentUrl.equals("/api/login")) {
      chain.doFilter(request, response);
    } else if (httpServletRequest.getMethod().equals("OPTIONS")) {
      httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
      httpServletResponse.setHeader(
          "Access-Control-Allow-Methods",
          "GET, POST, PUT, DELETE"
      );
      httpServletResponse.setHeader(
          "Access-Control-Allow-Headers",
          "Authorization, Content-Type, email, password"
      );
      httpServletResponse.setContentType("application/json");
      httpServletResponse.setStatus(HttpServletResponse.SC_OK);
    } else {
      if (email == null || password == null) {
        ((HttpServletResponse) response).sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "Invalid User"
        );
      } else if (
          memberRepo.existsByEmailAndPasswordAndRole(
          email,
          password,
          Role.ADMIN
        )
          &&
          checkAdminUrl(currentUrl)
      ) {
        chain.doFilter(request, response);
      } else if (
          memberRepo.existsByEmailAndPassword(email, password)
          &&
          !(checkAdminUrl(currentUrl))
      ) {
        chain.doFilter(request, response);
      } else {
        DISPLAY.info("Unauthorized");
        ((HttpServletResponse) response).sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "Unauthorized User"
        );
      }
    }
  }
}
