package com.heroku.tpe5.service;

import com.heroku.tpe5.domain.dto.CareerCount;
import com.heroku.tpe5.domain.dto.CareerReportRow;
import com.heroku.tpe5.domain.entity.Career;
import com.heroku.tpe5.domain.repository.CareerRepository;
import com.heroku.tpe5.model.CareerAto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CareerService {

    @Autowired
    private CareerRepository careerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CareerAto createCareer(CareerAto careerAto) {
        Career career = modelMapper.map(careerAto, Career.class);
        career = careerRepository.save(career);
        return modelMapper.map(career, CareerAto.class);
    }

    public List<CareerAto> getCareers() {
        return careerRepository.findAll().stream()
                .map(career -> modelMapper.map(career, CareerAto.class))
                .collect(Collectors.toList());
    }

    /**
     * f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
     *
     * @return
     */
    public List<CareerCount> getCareersByInscriptions() {
        return careerRepository.getCareersByInscriptions();
    }

    /**
     * h) generar un reporte de las carreras, que para cada carrera incluya información de los
     * inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
     * presentar los años de manera cronológica.
     *
     * @return
     */
    public List<CareerReportRow> getCareersReport() {
        return careerRepository.getCareersReport();
    }
}
