//package mk.ukim.finki.wp.lab.web.servlet;
//
//import mk.ukim.finki.wp.lab.model.Student;
//import mk.ukim.finki.wp.lab.service.StudentService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "create-student-servlet", urlPatterns = "/createStudent")
//public class CreateStudentServlet extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final StudentService studentService;
//
//    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.studentService = studentService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//
//        context.setVariable("bodyContent", "createStudent");
//
//        this.springTemplateEngine.process("master-template.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String name = req.getParameter("name");
//        String surname = req.getParameter("surname");
//
//        Student s = null;
//        try {
//            studentService.save(username, password, name, surname, true);
//        } catch(IllegalArgumentException ex) {
//            springTemplateEngine.process("createStudent.html", context, resp.getWriter());
//
//        }
//        resp.sendRedirect("/addStudent");
//    }
//}
