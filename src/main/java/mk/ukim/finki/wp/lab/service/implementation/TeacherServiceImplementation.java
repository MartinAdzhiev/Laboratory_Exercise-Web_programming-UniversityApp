package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.converter.TeacherFullname;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.model.exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryTeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImplementation implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImplementation(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Teacher save(TeacherFullname teacherFullname) {
        Teacher t = new Teacher(teacherFullname);
        return teacherRepository.save(t);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
