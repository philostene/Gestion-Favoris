package dao;

import java.util.List;

import modele.Categorie;

public interface CategorieDao {
	List<Categorie> findAll(int first, int size);
	
	List<Categorie> findAllForFavoriId(Long idFavori, int first, int size);
	
	Categorie getCategorieById(Long id);

	void deleteCategorieById(Long id);
	
	Categorie updateCategorie(Categorie categorie);
	
	Categorie createCategorie(Categorie categorie);
}
