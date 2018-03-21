package com.senla.autoservice.servlet.filter;


import com.senla.autoservice.bean.User;
import com.senla.autoservice.facade.Autoservice;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "AuditFilter")
public class AuditFilter implements  Filter {
    private FilterConfig config;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain filterChain) throws  ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final Object user = request.getSession().getAttribute("user");
        final String url = request.getRequestURI();

        Autoservice.getInstance().auditRequest((User) user, url);
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
