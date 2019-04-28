package org.formation.formationSpringBoot.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "teach")
public class Enseigner {
	@EmbeddedId
	private EnseignerPk key;
	@Enumerated(EnumType.STRING)
	@Column(name = "level", length = 10)
	private Niveau niveau;

	public Enseigner() {

	}

	public EnseignerPk getKey() {
		return key;
	}

	public void setKey(EnseignerPk key) {
		this.key = key;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		Enseigner other = (Enseigner) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
