package modele;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "FAVORIS")
@NamedQueries({
		@NamedQuery(name = "Categorie.findAll", query = " SELECT v FROM Categorie v ORDER BY v.nom "),
		@NamedQuery(name = "Categorie.deleteById", query = " DELETE FROM Categorie v WHERE v.id = :id") })
public class Favori {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String nom;
	
	@Column(name = "URL")
	private String url;
	
	@Column(name = "DATE_CREATION")
	private Date date_creation;
	
	@Column(name = "CATEGORIE_ID")
	private Long categorieID;

	@JsonIgnore
	@ManyToMany
	@JoinColumn(name = "ID")
	private Set<Categorie> categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the date_creation
	 */
	public Date getDate_creation() {
		return date_creation;
	}

	/**
	 * @param date_creation the date_creation to set
	 */
	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	/**
	 * @return the categorieID
	 */
	public Long getCategorieID() {
		return categorieID;
	}

	/**
	 * @param categorieID the categorieID to set
	 */
	public void setCategorieID(Long categorieID) {
		this.categorieID = categorieID;
	}

	/**
	 * @return the categories
	 */
	public Set<Categorie> getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(Set<Categorie> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + "]";
	}

}
