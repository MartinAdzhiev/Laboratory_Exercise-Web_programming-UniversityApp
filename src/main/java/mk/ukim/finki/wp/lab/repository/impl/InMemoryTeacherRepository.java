package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.TeacherDataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryTeacherRepository {

    public List<Teacher> findAll() {
        return TeacherDataHolder.teacherList;
    }

    public Teacher findById(Long teacherId) {
        return TeacherDataHolder.teacherList.stream()
                .filter(teacher -> teacher.getId().equals(teacherId))
                .collect(Collectors.toList()).get(0);
    }
}
