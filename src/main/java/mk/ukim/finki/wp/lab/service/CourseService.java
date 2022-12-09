package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> listAll();

    List<Student> listStudentsByCourse(Long courseId);

    List<Grade> listGradesByCourse(Long courseId);

    Course addStudentInCourse(String username, Long courseId);

    Course save(String name, String description, Long teacherId);

    void deleteById(Long id);

    Course findById(Long id);
}
