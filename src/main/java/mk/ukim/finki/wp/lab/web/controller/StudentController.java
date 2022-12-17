package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.implementation.GradeServiceImplementation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

    private final StudentService studentService;

    private final CourseService courseService;

    private final GradeServiceImplementation gradeServiceImplementation;

    public StudentController(StudentService studentService, CourseService courseService, GradeServiceImplementation gradeServiceImplementation) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeServiceImplementation = gradeServiceImplementation;
    }

    @GetMapping("/addStudent")
    public String ListStudents(Model model, HttpServletRequest request) {
        String courseId = (String) request.getSession().getAttribute("courseId");
        model.addAttribute("courseId", courseId);
        model.addAttribute("studentsList", this.studentService.listAll());
        model.addAttribute("bodyContent", "listStudents");

        return "master-template";
    }

    @GetMapping("/createStudent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String CreateStudentPage(Model model) {
        model.addAttribute("bodyContent", "createStudent");

        return "master-template";
    }

    @PostMapping("/createStudent")
    public String saveCourse(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String name,
                             @RequestParam String surname) {
        studentService.save(username, password, name, surname, true);
        return "redirect:/addStudent";
    }

    @GetMapping("/listNewStudents")
    public String ListNewStudents(Model model) {
        model.addAttribute("newStudentsList", this.studentService.listNewStudents());
        model.addAttribute("bodyContent", "listNewStudents");

        return "master-template";
    }

    @PostMapping("/addStudent")
    public String saveStudentInCourse(HttpServletRequest request, Model model) {
        String courseId = (String) request.getSession().getAttribute("courseId");
        String username = request.getParameter("student");

        Course c = courseService.addStudentInCourse(username, Long.parseLong(courseId));

        return "redirect:/StudentEnrollmentSummary";
    }

    @GetMapping("/StudentEnrollmentSummary")
    public String StudentEnrollment(HttpServletRequest request, Model model) {
        String courseId = (String) request.getSession().getAttribute("courseId");
        Course c = courseService.findById(Long.parseLong(courseId));
        model.addAttribute("courseName", c.getName());
        model.addAttribute("studentsInCourse", courseService.listStudentsByCourse(Long.parseLong(courseId)));
        model.addAttribute("gradesInCourse", courseService.listGradesByCourse(Long.parseLong(courseId)));
        model.addAttribute("bodyContent", "studentsInCourse");

        return "master-template";
    }
}
