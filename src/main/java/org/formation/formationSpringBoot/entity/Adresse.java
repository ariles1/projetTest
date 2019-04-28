package org.formation.formationSpringBoot.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.formation.formationSpringBoot.entity.view.JsonViews;
import org.formation.formationSpringBoot.entity.view.JsonViews.Common;

import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class Adresse {
	@JsonView(JsonViews.Common.class)
	@Column(name = "street")
	private String rue;
	@JsonView(JsonViews.Common.class)
	@Column(name = "zip_code", length = 6)
	private String codePostal;
	@JsonView(JsonViews.Common.class)
	@Column(name = "city", length = 150)
	private String ville;

	public Adresse() {
	}

	public Adresse(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
