package mk.ukim.finki.wp.lab.web.servlet;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.implementation.GradeServiceImplementation;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "student-enrollment-summary", urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;
    private final CourseService courseService;

    private final GradeServiceImplementation gradeServiceImplementation;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService, GradeServiceImplementation gradeServiceImplementation) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeServiceImplementation = gradeServiceImplementation;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String courseId = (String) req.getSession().getAttribute("courseId");
        String studentUsername = (String) req.getSession().getAttribute("newStudentToCourse");
        Course c = courseService.addStudentInCourse(studentUsername, Long.parseLong(courseId));
        context.setVariable("courseName", c.getName());
        context.setVariable("studentsInCourse", courseService.listStudentsByCourse(Long.parseLong(courseId)));
        context.setVariable("gradesInCourse", courseService.listGradesByCourse(Long.parseLong(courseId)));


        springTemplateEngine.process("studentsInCourse", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
