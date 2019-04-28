package org.formation.formationSpringBoot.repository;

import java.util.List;

import org.formation.formationSpringBoot.entity.Formateur;
import org.formation.formationSpringBoot.entity.Personne;
import org.formation.formationSpringBoot.entity.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
	public List<Personne> findByNom(String nom);

	@Query("select p from Personne p where p.salle.nom=:nom")
	public List<Personne> findBySalle(@Param("nom") String nom);

	@Query("select s from Stagiaire s")
	public List<Stagiaire> findAllStagiaire();

	@Query("select f from Formateur f")
	public List<Formateur> findAllFormateur();
}
