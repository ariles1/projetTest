package org.formation.formationSpringBoot.entity;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("T")
@NamedQueries({
		@NamedQuery(name = "Formateur.findAllWithEnseingementsAndSalle", query = "select distinct f from Formateur f left join fetch f.matieresEnseignees me left join fetch me.key.matiere left join fetch f.salle ") })
public class Formateur extends Personne {
	private Integer experience;
//	@ManyToMany
//	@JoinTable(name = "teach", joinColumns = { @JoinColumn(name = "trainer_id") }, inverseJoinColumns = {
//			@JoinColumn(name = "course_id") })
//	private Set<Matiere> matieres;

	@OneToMany(mappedBy = "key.formateur")
	private Set<Enseigner> matieresEnseignees;

	public Formateur() {

	}

	public Formateur(String prenom, String nom) {
		super(prenom, nom);
		// TODO Auto-generated constructor stub
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Set<Enseigner> getMatieresEnseignees() {
		return matieresEnseignees;
	}

	public void setMatieresEnseignees(Set<Enseigner> matieresEnseignees) {
		this.matieresEnseignees = matieresEnseignees;
	}

//	public Set<Matiere> getMatieres() {
//		return matieres;
//	}
//
//	public void setMatieres(Set<Matiere> matieres) {
//		this.matieres = matieres;
//	}

}
