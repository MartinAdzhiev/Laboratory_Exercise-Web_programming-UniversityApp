package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class CourseDataHolder {

    public static List<Course> courseList = new ArrayList<>();

    @PostConstruct
    public void init() {
        Teacher teacher = new Teacher((long) 1, "Teacher1", "TeascherSurname1");
        courseList.add(new Course("Discrete mathematics", "1st year course", teacher));
        courseList.add(new Course("Operating systems", "2nd year course", teacher));
        courseList.add(new Course("Algorithms and data structures", "2nd year course", teacher));
        courseList.add(new Course("Intro to Data Science", "3rd year course", teacher));
        courseList.add(new Course("Cloud computing", "4th year course", teacher));
    }

}