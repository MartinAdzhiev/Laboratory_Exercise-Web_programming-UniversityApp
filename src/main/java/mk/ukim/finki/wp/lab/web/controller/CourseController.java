package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("coursesList", this.courseService.listAll());
        model.addAttribute("bodyContent", "listCourses");
        return "master-template";
    }

    @PostMapping
    public String chooseCourse(HttpServletRequest request, Model model) {
        String courseId = request.getParameter("courseId");

        if (courseId != null) {
            request.getSession().setAttribute("courseId", courseId);
            return "redirect:/addStudent";
        } else {
            return "redirect:/courses";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/add-course")
    public String AddCoursePage(Model model) {
        List<Teacher> teacherList = this.teacherService.findAll();
        model.addAttribute("teachers", teacherList);
        model.addAttribute("bodyContent", "add-course");
        return "master-template";
    }

    @GetMapping("/edit/{id}")
    public String EditCourse(@PathVariable Long id, Model model) {
        if (this.courseService.findById(id) != null) {
            Course course = this.courseService.findById(id);
            List<Teacher> teacherList = this.teacherService.findAll();
            model.addAttribute("course", course);
            model.addAttribute("teachers", teacherList);
            model.addAttribute("bodyContent", "add-course");
            return "master-template";
        }
        return "redirect:/courses";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long teacher) {
        String namee = name;
        System.out.println(namee);
        this.courseService.save(name, description, teacher);
        return "redirect:/courses";
    }
}
