package mk.ukim.finki.wp.lab.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
public class ChoosenCourseFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String courseId = (String) request.getSession().getAttribute("courseId");
       // System.out.println(courseId);

        String path = request.getServletPath();
//
//        if (!"/courses".equals(path) && !path.equals("/h2")) {
//            response.sendRedirect("/courses");
//        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        //}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
