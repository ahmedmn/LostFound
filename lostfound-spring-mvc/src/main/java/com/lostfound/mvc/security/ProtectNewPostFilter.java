package com.lostfound.mvc.security;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Peter
 *
 * Filter protects system from creating new posts by guests
 */
@WebFilter(urlPatterns = {"/post/new"})
public class ProtectNewPostFilter implements Filter {


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        HttpSession session = request.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("authenticatedUser") != null;
        if (!loggedIn) {
            response.sendRedirect(request.getContextPath() + "/post/all/0");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
