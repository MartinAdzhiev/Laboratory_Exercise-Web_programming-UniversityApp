package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.model.enumerations.Type;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String descrpition;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;

    @Enumerated(EnumType.STRING)
    private Type type;

    private int howManyTimes = 0;
    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Grade> grades;

    public Course() {
    }

    public Course(String name, String descrpition, Teacher teacher) {
        this.courseId = (long) (Math.random() * 1000);
        this.name = name;
        this.descrpition = descrpition;
        this.students = new ArrayList<>();
        this.teacher = teacher;
    }
}
