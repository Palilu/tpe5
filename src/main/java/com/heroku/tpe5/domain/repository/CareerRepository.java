package com.heroku.tpe5.domain.repository;

import com.heroku.tpe5.domain.dto.CareerCount;
import com.heroku.tpe5.domain.dto.CareerReportRow;
import com.heroku.tpe5.domain.entity.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 *  <ul>
 *    <li>Guillermina Lauge</li>
 *    <li>Pablo Mendoza</li>
 *    <li>Ricardo Gentil</li>
 *  </ul>
 */
@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {


    /**
     * f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
     */
    @Query(" SELECT new com.heroku.tpe5.domain.dto.CareerCount(c.id, c.name, count(i)) " +
           " FROM Career c JOIN c.inscriptions i " +
           " GROUP BY c.id, c.name ORDER BY count(i) DESC")
    List<CareerCount> getCareersByInscriptions();

    /**
     * 3) Generar un reporte de las carreras, que para cada carrera incluya información de los
     *  inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
     *  los años de manera cronológica.
     */
    @Query(" SELECT new com.heroku.tpe5.domain.dto.CareerReportRow(" +
           "     c.id, " +
           "     c.name, " +
           "     YEAR(i.since), " +
           "     SUM(CASE WHEN(i.status = 'INSCRIPTED') THEN 1 ELSE 0 END), " +
           "     SUM(CASE WHEN(i.status = 'FINISHED') THEN 1 ELSE 0 END)" +
           " )" +
           " FROM Career c JOIN c.inscriptions i " +
           " GROUP BY c.id, c.name, YEAR(i.since) ORDER BY c.name, YEAR(i.since)")
    List<CareerReportRow> getCareersReport();
}

