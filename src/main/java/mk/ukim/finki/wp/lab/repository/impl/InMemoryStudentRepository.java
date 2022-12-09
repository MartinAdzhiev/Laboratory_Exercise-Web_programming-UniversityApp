package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.StudentDataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryStudentRepository {

    public Student save(Student s) {
        if (s == null || s.getUsername() == null || s.getUsername().isEmpty()) {
            return null;
        }
        StudentDataHolder.studentList.add(s);
        return s;
    }

    public List<Student> findAllStudents() {
        return StudentDataHolder.studentList;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return StudentDataHolder.studentList.stream()
                .filter(student -> student.getUsername().contains(text))
                .collect(Collectors.toList());
    }
}
