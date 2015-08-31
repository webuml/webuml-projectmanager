package com.webuml.projectmanager.controller.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS">
 *      https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS
 *      </a>
 */
@Component
public class CORSRequestFilter extends OncePerRequestFilter {

  @Value("${webuml-ui.baseUri}")
  private String webumlUiBaseUri;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    response.addHeader("Access-Control-Allow-Origin", webumlUiBaseUri);
    response.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
    response.addHeader("Access-Control-Allow-Headers", "Content-Type");
    chain.doFilter(request, response);
  }
}
