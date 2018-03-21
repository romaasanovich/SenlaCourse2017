package com.senla.autoservice.servlet.filter;

import com.senla.autoservice.bean.User;
import com.senla.autoservice.facade.Autoservice;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/master", "/work", "/order", "/place"}, filterName = "AuthentificationFilter")
public class AuthentificationFilter implements Filter {

    private FilterConfig config;

    public void init(final FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain filterChain) throws  ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final Object user = request.getSession().getAttribute("user");

        if (user instanceof User) {
            try {
                final String token = ((User) user).getToken();

                if (Autoservice.getInstance().isValidToken(token)){
                    filterChain.doFilter(req, resp);
                }
            } catch (final Exception e) {
                resp.getWriter().println("Invalid token. Please login.");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
