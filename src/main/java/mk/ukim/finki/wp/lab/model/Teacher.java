package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.converter.TeacherFullname;
import mk.ukim.finki.wp.lab.converter.TeacherFullnameConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = TeacherFullnameConverter.class)
    private TeacherFullname teacherFullname;
//    private String name;
//    private String surname;

    private LocalDate dateOfEmployment;

    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private List<Course> courses;

    public Teacher() {
    }

    public Teacher(Long id, String name, String surname) {
        this.id = id;
//        this.name = name;
//        this.surname = surname;
    }

    public Teacher(TeacherFullname teacherFullname) {
        this.teacherFullname = teacherFullname;
    }
}
