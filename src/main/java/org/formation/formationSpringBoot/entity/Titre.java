package org.formation.formationSpringBoot.entity;

public enum Titre {
	M("monsieur"), MME("madame"), MLLE("mademoiselle");

	private String texte;

	private Titre(String texte) {
		this.texte = texte;
	}

	public String getTexte() {
		return texte;
	}
}
