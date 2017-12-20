package com.aaw.aula3.repository;

import com.aaw.aula3.domain.Atividade;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Atividade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

}
