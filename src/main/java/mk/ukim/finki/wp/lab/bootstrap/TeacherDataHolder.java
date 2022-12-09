package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDataHolder {

    public static List<Teacher> teacherList = new ArrayList<>();

    @PostConstruct
    public void init() {
        teacherList.add(new Teacher((long) 1, "Teacher1", "TeascherSurname1"));
        teacherList.add(new Teacher((long) 2, "Teacher2", "TeascherSurname2"));
        teacherList.add(new Teacher((long) 3, "Teacher3", "TeascherSurname3"));
        teacherList.add(new Teacher((long) 4, "Teacher4", "TeascherSurname4"));
        teacherList.add(new Teacher((long) 5, "Teacher5", "TeascherSurname5"));
    }
}
