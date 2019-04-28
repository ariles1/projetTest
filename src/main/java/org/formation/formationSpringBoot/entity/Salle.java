package org.formation.formationSpringBoot.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.formation.formationSpringBoot.entity.view.JsonViews;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "room")
@SequenceGenerator(name = "seqSalle", sequenceName = "seq_room", initialValue = 100, allocationSize = 1)
@NamedQueries({
		@NamedQuery(name = "Salle.findAllWithPersonnes", query = "select distinct s from Salle s left join fetch s.personnes p left join fetch p.ordinateur "),
		@NamedQuery(name = "Salle.findByKeyWithPersonnes", query = "select distinct s from Salle s left join fetch s.personnes p left join fetch p.ordinateur where s.id=:key") })
public class Salle {
	@JsonView(JsonViews.Common.class)
	@Id
	@GeneratedValue(generator = "seqSalle", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_room")
	private Integer id;
	@Column(name = "room", length = 100, nullable = false)
	@JsonView(JsonViews.Common.class)
	private String nom;
	@Column(name = "floor")
	@JsonView(JsonViews.Common.class)
	private Integer etage;
	@Column(name = "capacity")
	@JsonView(JsonViews.Common.class)
	private Integer capacite;
	@OneToMany(mappedBy = "salle", fetch = FetchType.LAZY)
	@JsonView(JsonViews.SalleWithPersonne.class)
	private Set<Personne> personnes;
	@Version
	private int version;

	public Salle() {
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

	public Integer getEtage() {
		return etage;
	}

	public void setEtage(Integer etage) {
		this.etage = etage;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public Set<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
		Salle other = (Salle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
