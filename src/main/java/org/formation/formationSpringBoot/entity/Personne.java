package org.formation.formationSpringBoot.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.formation.formationSpringBoot.entity.view.JsonViews;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Formateur.class, name = "formateur"),
		@Type(value = Stagiaire.class, name = "stagiaire") })
@Entity
@Table(name = "person")
@SequenceGenerator(name = "seqPersonne", sequenceName = "seq_person", initialValue = 100, allocationSize = 1)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 1)
@NamedQueries({ @NamedQuery(name = "Personne.findAll", query = "select p from Personne p"),
		@NamedQuery(name = "Personne.findBySalle", query = "select p from Personne p where p.salle.nom=:nom") })
public abstract class Personne {
	@JsonView(JsonViews.Common.class)
	@Id
	@Column(name = "id_person")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPersonne")
	private Integer id;
	@JsonView(JsonViews.Common.class)
	@Column(name = "first_name", length = 150, nullable = false)
	private String prenom;
	@JsonView(JsonViews.Common.class)
	@Column(name = "last_name", length = 150, nullable = false)
	private String nom;
	@JsonView(JsonViews.Common.class)
	@Column(name = "civility")
	@Enumerated(EnumType.ORDINAL)
	private Titre titre;
	@JsonView(JsonViews.Common.class)
	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateNaissance;
	@Embedded
	@JsonView(JsonViews.Common.class)
	@AttributeOverrides({ @AttributeOverride(name = "rue", column = @Column(name = "street_person", length = 255)),
			@AttributeOverride(name = "codePostal", column = @Column(name = "zip_code_person", length = 6)),
			@AttributeOverride(name = "ville", column = @Column(name = "city_person", length = 150)) })
	private Adresse adresse;
	@JsonView(JsonViews.PersonneWithSalle.class)
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Salle salle;
	@Version
	private int version;

	public Personne() {
	}

	public Personne(String prenom, String nom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Titre getTitre() {
		return titre;
	}

	public void setTitre(Titre titre) {
		this.titre = titre;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
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
		Personne other = (Personne) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
