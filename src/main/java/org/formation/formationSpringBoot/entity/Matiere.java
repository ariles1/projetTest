package org.formation.formationSpringBoot.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "course")
@SequenceGenerator(name = "seqMatiere", sequenceName = "seq_Course", initialValue = 100)
public class Matiere {
	@Id
	@GeneratedValue(generator = "seqMatiere", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_course")
	private Integer id;
	@Column(name = "name", length = 100, nullable = false)
	private String nom;
//	@ManyToMany(mappedBy = "matieres")
//	private Set<Formateur> formateurs;
	@OneToMany(mappedBy = "key.matiere")
	private Set<Enseigner> enseignents;

	public Matiere() {
	}

	public Matiere(String nom) {
		super();
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

//	public Set<Formateur> getFormateurs() {
//		return formateurs;
//	}
//
//	public void setFormateurs(Set<Formateur> formateurs) {
//		this.formateurs = formateurs;
//	}

	public Set<Enseigner> getEnseignents() {
		return enseignents;
	}

	public void setEnseignents(Set<Enseigner> enseignents) {
		this.enseignents = enseignents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matiere other = (Matiere) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
