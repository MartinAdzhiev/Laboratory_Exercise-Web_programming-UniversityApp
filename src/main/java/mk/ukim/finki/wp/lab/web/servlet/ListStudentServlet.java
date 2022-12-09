package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "list-student-servlet", urlPatterns = "/addStudent")
public class ListStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;


    public ListStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());

        webContext.setVariable("studentsList", this.studentService.listAll());
        String courseId = (String) req.getSession().getAttribute("courseId");
        webContext.setVariable("courseId", courseId);


        this.springTemplateEngine.process("listStudents.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentUsername = req.getParameter("student");
        if (studentUsername == null) {
            resp.sendRedirect("/addStudent");
        } else {
            req.getSession().setAttribute("newStudentToCourse", studentUsername);

            resp.sendRedirect("/StudentEnrollmentSummary");
        }
    }
}
