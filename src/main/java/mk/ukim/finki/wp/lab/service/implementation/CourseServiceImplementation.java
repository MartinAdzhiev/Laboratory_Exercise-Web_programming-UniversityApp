package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryCourseRepository;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryTeacherRepository;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImplementation implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;
    private final TeacherRepository teacherRepository;

    public CourseServiceImplementation(CourseRepository courseRepository, StudentService studentService, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Optional<Course> c = courseRepository.findById(courseId);
        return c.get().getStudents();
        //return courseRepository.findAllStudentsByCourse(courseId);
    }

    public List<Grade> listGradesByCourse(Long courseId) {
        Optional<Course> c = courseRepository.findById(courseId);
        return c.get().getGrades();
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    @Transactional
    public Course addStudentInCourse(String username, Long courseId) {
        Course c = courseRepository.findById(courseId).orElseThrow(IllegalArgumentException::new);
        c.getStudents().removeIf(student -> student.getUsername().equals(username));
        c.getStudents().add(studentService.searchByNameOrSurname(username).get(0));
        return c;
        //return courseRepository.addStudentToCourse(studentService.searchByNameOrSurname(username).get(0), c);
    }

    @Override
    @Transactional
    public Course save(String name, String description, Long teacherId) {
        Teacher t = teacherRepository.findById(teacherId).orElseThrow(IllegalArgumentException::new);
        //courseRepository.findAll().removeIf(course -> course.getName().equals(name));
        if (t == null) {
            throw new TeacherNotFoundException(teacherId);
        }
        Course c = new Course(name, description, t);
        courseRepository.save(c);
        return c;
        //return courseRepository.save(name, description, t);
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteById(Long id) {
        this.courseRepository.deleteById(id);
    }

}
