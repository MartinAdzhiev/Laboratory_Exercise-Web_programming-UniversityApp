package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "courses-list-servlet", urlPatterns = "/listCourses")
public class CoursesListSevlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public CoursesListSevlet(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        webContext.setVariable("coursesList", this.courseService.listAll());

        this.springTemplateEngine.process("listCourses.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String courseId = req.getParameter("courseId");

        if (courseId != null) {

            req.getSession().setAttribute("courseId", courseId);
            resp.sendRedirect("/addStudent");
        } else {
            resp.sendRedirect("/listCourses");
        }

    }
}
