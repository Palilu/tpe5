package com.heroku.tpe5.domain.repository;

import com.heroku.tpe5.domain.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author
 *  <ul>
 *    <li>Guillermina Lauge</li>
 *    <li>Pablo Mendoza</li>
 *    <li>Ricardo Gentil</li>
 *  </ul>
 */
@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
}
