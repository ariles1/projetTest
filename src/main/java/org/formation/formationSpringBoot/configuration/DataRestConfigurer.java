package org.formation.formationSpringBoot.configuration;

import org.formation.formationSpringBoot.entity.Formateur;
import org.formation.formationSpringBoot.entity.Matiere;
import org.formation.formationSpringBoot.entity.Personne;
import org.formation.formationSpringBoot.entity.Salle;
import org.formation.formationSpringBoot.entity.Stagiaire;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class DataRestConfigurer implements RepositoryRestConfigurer {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Formateur.class, Stagiaire.class, Matiere.class, Salle.class);
	}
}
