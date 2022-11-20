package com.heroku.tpe5.domain.repository;

import com.heroku.tpe5.domain.entity.Career;
import com.heroku.tpe5.domain.entity.Gender;
import com.heroku.tpe5.domain.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author
 *  <ul>
 *    <li>Guillermina Lauge</li>
 *    <li>Pablo Mendoza</li>
 *    <li>Ricardo Gentil</li>
 *  </ul>
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Retorna los estudiantes que pertenezcan al género pasado cómo parámetro.
     */
    List<Student> findByGender(Gender gender);

    /**
     * Retorna el estudiante que tenga el número de libreta universitaria pasado como parámetro.
     */
    Optional<Student> findByStudentId(Long studentId);

    /**
     * Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
     *
     * @return
     */
    @Query(" SELECT s " +
           " FROM Student s JOIN s.inscriptions i " +
           " WHERE i.career = :career AND s.city = :city")
    List<Student> findByCityAndCareer(@Param(value= "career") Career career,
                                      @Param(value= "city") String city);
}
