package com.aaw.aula3.repository;

import com.aaw.aula3.domain.Aula;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Aula entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

}
