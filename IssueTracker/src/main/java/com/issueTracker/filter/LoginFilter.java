package com.IssueTracker.filter;

import com.IssueTracker.constants.Constants;
import com.IssueTracker.entities.enums.Role;
import com.IssueTracker.models.bindingModels.IssueEditBindingModel;
import com.IssueTracker.models.bindingModels.LoggedUserModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/issues/add/*", "/issues/edit/*", "/issues/delete/*"})
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        LoggedUserModel loggedUserModel = (LoggedUserModel) session.getAttribute("user");
        if(loggedUserModel == null){
            ((HttpServletResponse)servletResponse).sendRedirect("/login");
            return;
        }

        if (loggedUserModel.getRole() != Role.ADMIN){
            long issueId = Long.parseLong(((HttpServletRequest)servletRequest).getRequestURI().split("/")[3]);
            IssueEditBindingModel issueEditBindingModel = loggedUserModel.getIssues()
                                                        .stream()
                                                        .filter(i -> i.getId() == issueId)
                                                        .findFirst()
                                                        .orElse(null);

            if(issueEditBindingModel == null){
                ((HttpServletResponse)servletResponse).sendRedirect("/login");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
