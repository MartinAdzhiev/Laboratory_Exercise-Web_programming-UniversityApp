package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryStudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }


    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findStudentByUsernameContainingIgnoreCase(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname, boolean isNew) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()
                || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Student s = new Student(username, password, name, surname, true);
        studentRepository.save(s);
        return s;
    }

    @Override
    public List<Student> listNewStudents() {
        return studentRepository.findAll().stream()
                .filter(student -> student.isNew())
                .collect(Collectors.toList());
    }
}
