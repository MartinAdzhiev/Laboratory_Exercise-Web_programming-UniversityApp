package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Grade> grades;


    private boolean isNew;

    public Student() {
    }

    public Student(String username) {
        this.username = username;
    }

    public Student(String username, String password, String name, String surname, boolean isNew) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.isNew = isNew;
    }
}
