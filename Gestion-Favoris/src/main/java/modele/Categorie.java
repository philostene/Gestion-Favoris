package modele;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CATEGORIES")
//@NamedQueries({
//		@NamedQuery(name = "Categorie.findAll", query = " SELECT m FROM Categorie m ORDER BY m.nom "),
//		@NamedQuery(name = "Categorie.findAllByVilleId", query = " SELECT m FROM Categorie m JOIN m.id v WHERE v.id = :id ORDER BY m.nom "),
//		@NamedQuery(name = "Categorie.deleteById", query = " DELETE FROM Categorie m WHERE m.identifiant = :id") 
//		})
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String nom;

	@JsonIgnore
	@ManyToMany
	@JoinColumn(name = "ID")
	private Set<Favori> favoris;
	
	
	public Categorie(String nom) {
		super();
		this.nom = nom;
	}

	public Categorie() {
	}


	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the favoris
	 */
	public Set<Favori> getFavoris() {
		return favoris;
	}

	/**
	 * @param favoris the favoris to set
	 */
	public void setFavoris(Set<Favori> favoris) {
		this.favoris = favoris;
	}

	@Override
	public String toString() {
		return "Categorie [identifiant=" + id + ", nom=" + nom
				+ "]";
	}
}
