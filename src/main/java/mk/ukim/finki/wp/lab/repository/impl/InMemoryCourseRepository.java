package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.CourseDataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryCourseRepository {

    public List<Course> findAllCourses() {
        return CourseDataHolder.courseList.stream().
                sorted(Comparator.comparing(Course::getName))
                .collect(Collectors.toList());
    }

    public Course findById(Long courseId) {
        return CourseDataHolder.courseList.stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .collect(Collectors.toList()).get(0);
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        return CourseDataHolder.courseList.stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .findFirst().get().getStudents();
    }

    public Course addStudentToCourse(Student student, Course course) {
        course.getStudents().add(student);
        return course;
    }

    public Course save(String name, String description, Teacher teacher) {

        List<Course> coursesWithSameName = CourseDataHolder.courseList.stream().filter(course -> course.getName().equals(name)).collect(Collectors.toList());
        Course existingCourse = null;
        if (coursesWithSameName.size() > 0) {
            existingCourse = coursesWithSameName.get(coursesWithSameName.size() - 1);
            //Course existingCourse = coursesWithSameName.get(coursesWithSameName.size() - 1);
            CourseDataHolder.courseList.removeIf(course -> course.getName().equals(name));
            int count = existingCourse.getHowManyTimes();
            count++;
            Course c = new Course(name, description, teacher);
            c.setHowManyTimes(count);
            CourseDataHolder.courseList.add(c);
            return c;
        } else {
            Course c = new Course(name, description, teacher);
            CourseDataHolder.courseList.add(c);
            return c;
        }

    }


    public void deleteById(Long id) {
        CourseDataHolder.courseList.removeIf(course -> course.getCourseId().equals(id));
    }
}
