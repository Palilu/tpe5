package com.heroku.tpe5.controller;

import com.heroku.tpe5.domain.entity.Gender;
import com.heroku.tpe5.model.StudentAto;
import com.heroku.tpe5.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * a) dar de alta un estudiante
     *
     * @param studentAto
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentAto createStudent(@RequestBody @Validated StudentAto studentAto) {
        return studentService.createStudent(studentAto);
    }

    /**
     * c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
     * d) recuperar un estudiante, en base a su número de libreta universitaria.
     * e) recuperar todos los estudiantes, en base a su género.
     * g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
     *
     * @param sortBy
     * @param direction
     * @param studentId
     * @param gender
     * @param careerId
     * @param city
     * @return
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentAto> getStudents(@RequestParam(required = false) String sortBy,
                                        @RequestParam(required = false) Sort.Direction direction,
                                        @RequestParam(required = false) Long studentId,
                                        @RequestParam(required = false) Gender gender,
                                        @RequestParam(required = false) Long careerId,
                                        @RequestParam(required = false) String city
                                        ) {
        if (sortBy != null && direction != null) {
            return studentService.getStudents(sortBy, direction);
        } else if (studentId != null) {
            return List.of(studentService.findByStudentId(studentId));
        } else if (gender != null) {
            return studentService.findByGender(gender);
        } else if (careerId != null && city != null) {
            return studentService.findByCareerAndCity(careerId, city);
        } else {
            return studentService.getStudents();
        }
    }
}
