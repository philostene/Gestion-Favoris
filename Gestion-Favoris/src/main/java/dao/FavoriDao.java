package dao;

import java.util.List;

import modele.Favori;

public interface FavoriDao {
	List<Favori> findAll(int first, int size);
	
	Favori getFavoriById(Long id);

	void deleteFavoriById(Long id);

	Favori createFavori(Favori favori);
	
	Favori updateFavori(Favori favori);
}
