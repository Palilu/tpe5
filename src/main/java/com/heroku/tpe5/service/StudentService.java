package com.heroku.tpe5.service;

import com.heroku.tpe5.domain.entity.Career;
import com.heroku.tpe5.domain.entity.Gender;
import com.heroku.tpe5.domain.entity.Student;
import com.heroku.tpe5.domain.repository.CareerRepository;
import com.heroku.tpe5.domain.repository.StudentRepository;
import com.heroku.tpe5.model.StudentAto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private static final List<String> STUDENT_COLUMNS = List.of("id",
            "givenNames", "lastName", "dateOfBirth", "gender", "dni", "city", "studentId");
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * a) dar de alta un estudiante
     *
     * @param studentAto
     */
    public StudentAto createStudent(StudentAto studentAto) {
        Student student = modelMapper.map(studentAto, Student.class);
        student = studentRepository.save(student);
        return modelMapper.map(student, StudentAto.class);
    }

    public List<StudentAto> getStudents() {
        return studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentAto.class))
                .collect(Collectors.toList());
    }

    /**
     * c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
     *
     * @param sortBy
     * @param direction
     * @return
     */
    public List<StudentAto> getStudents(String sortBy, Sort.Direction direction) {
        List<Student> students = STUDENT_COLUMNS.contains(sortBy) ?
            studentRepository.findAll(Sort.by(direction, sortBy)) : studentRepository.findAll();

        return students.stream()
                .map(student -> modelMapper.map(student, StudentAto.class))
                .collect(Collectors.toList());
    }

    /**
     * d) recuperar un estudiante, en base a su número de libreta universitaria.
     *
     * @param studentId
     * @return
     */
    public StudentAto findByStudentId(Long studentId) {
        Student student = studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Student not found for studentId=" + studentId));
        return modelMapper.map(student, StudentAto.class);
    }

    /**
     * e) recuperar todos los estudiantes, en base a su género.
     *
     * @param gender
     * @return
     */
    public List<StudentAto> findByGender(Gender gender) {
        return studentRepository.findByGender(gender).stream()
                .map(student -> modelMapper.map(student, StudentAto.class))
                .collect(Collectors.toList());
    }

    /**
     * g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
     *
     * @param careerId
     * @param city
     * @return
     */
    public List<StudentAto> findByCareerAndCity(Long careerId, String city) {
        Career career = careerRepository.findById(careerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Career not found for careerId=" + careerId));
        return studentRepository.findByCityAndCareer(career, city).stream()
                .map(student -> modelMapper.map(student, StudentAto.class))
                .collect(Collectors.toList());
    }
}
