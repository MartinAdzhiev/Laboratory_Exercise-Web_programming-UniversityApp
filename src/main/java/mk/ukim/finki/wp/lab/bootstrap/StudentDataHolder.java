package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDataHolder {

    public static List<Student> studentList = new ArrayList<>();

    @PostConstruct
    public void init() {
        studentList.add(new Student("martin.adzhiev", "12345", "Martin", "Adzhiev", false));
        studentList.add(new Student("jack.smith", "student_jack", "Jack", "Smith", false));
        studentList.add(new Student("thomas.brown", "student_thomas", "Thomas", "Brown", false));
        studentList.add(new Student("emily.wilson", "student_emily", "Emily", "Wilson", false));
        studentList.add(new Student("lily.roberts", "student_lily", "Lily", "Roberts", false));
    }
}
