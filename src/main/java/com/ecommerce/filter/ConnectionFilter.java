package com.ecommerce.filter;

import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.util.ConnectionDB;

@WebFilter(urlPatterns = {"/api/*"})
public class ConnectionFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
    // Add CORS headers
    HttpServletResponse res = (HttpServletResponse) response;
    res.setHeader("Access-Control-Allow-Origin", "*");
    res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    res.setHeader("Access-Control-Allow-Headers", "Content-Type");
    res.setHeader("Access-Control-Max-Age", "3600");
    Connection conn = ConnectionDB.getConnection();
    if ( conn.getAutoCommit() ) {
      conn.setAutoCommit(false);
    }
    try {
      request.setAttribute("connection", conn);
      chain.doFilter(request, response);
      conn.commit();
    } catch (Exception e) {
      conn.rollback();
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
      e.printStackTrace();
    }
    
  }
}
