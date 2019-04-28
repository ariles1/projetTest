package org.formation.formationSpringBoot.repository;

import java.util.List;

import org.formation.formationSpringBoot.entity.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SalleRepository extends JpaRepository<Salle, Integer> {
	@Query("select s from Salle s left join fetch s.personnes")
	List<Salle> findAllSalleWithPersonne();
}
