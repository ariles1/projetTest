package org.formation.formationSpringBoot.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("S")
public class Stagiaire extends Personne {
	private String formation;
	@OneToOne // cote maitre
	@JoinColumn(name = "computer_id")
	private Ordinateur ordinateur;

	public Stagiaire() {

	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) {
		this.formation = formation;
	}

	public Ordinateur getOrdinateur() {
		return ordinateur;
	}

	public void setOrdinateur(Ordinateur ordinateur) {
		this.ordinateur = ordinateur;
	}

}
