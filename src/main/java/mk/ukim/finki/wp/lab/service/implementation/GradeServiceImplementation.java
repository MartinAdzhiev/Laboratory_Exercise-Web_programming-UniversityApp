package mk.ukim.finki.wp.lab.service.implementation;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImplementation {

    private final GradeRepository gradeRepository;

    public GradeServiceImplementation(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> listAll() {
        return gradeRepository.findAll();
    }




}
