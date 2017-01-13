package com.lostfound.mvc.security;

import com.LostFound.dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Peter
 *
 * Filter protects system from accessing administration pages by unprivileged users/
 */
@WebFilter(urlPatterns = {"/category/*", "/item/*", "/user/list"})
public class ProtectAdminFilter implements Filter {


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;
        HttpSession session = request.getSession(false);
        UserDTO authenticated = (UserDTO) session.getAttribute("authenticatedUser");
        boolean loggedIn = session != null && authenticated != null && authenticated.isAdmin();
        if (!loggedIn) {
            response.sendRedirect(request.getContextPath() + "/post/list");
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
