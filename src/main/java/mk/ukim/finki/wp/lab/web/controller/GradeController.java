package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
public class GradeController {


    private final StudentService studentService;
    private final GradeRepository gradeRepository;

    private final CourseRepository courseRepository;

    public GradeController(StudentService studentService, GradeRepository gradeRepository, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/addGradeForm/{username}")
    public String ShowForm(@PathVariable String username, Model model) {
        Student s = this.studentService.searchByNameOrSurname(username).get(0);
        model.addAttribute("student", s);
        model.addAttribute("bodyContent", "add-grade");

        return "master-template";
    }

    @PostMapping("/addGrade")
    public String saveGrade(@RequestParam("date")
                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                            @RequestParam String username,
                            @RequestParam Character grade,
                            HttpServletRequest request) {

        String courseId = (String) request.getSession().getAttribute("courseId");
        Course c = courseRepository.findById(Long.parseLong(courseId)).get();
        Student s = this.studentService.searchByNameOrSurname(username).get(0);


        Grade g = new Grade(grade, s, c, date);
        this.gradeRepository.save(g);
        return "redirect:/StudentEnrollmentSummary";

    }
}
