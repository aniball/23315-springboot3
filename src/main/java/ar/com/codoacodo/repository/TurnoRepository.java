package ar.com.codoacodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codoacodo.domain.Turno;


@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {

}


