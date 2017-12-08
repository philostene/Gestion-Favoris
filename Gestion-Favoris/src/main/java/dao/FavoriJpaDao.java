package dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import modele.Favori;

@Named
public class FavoriJpaDao implements FavoriDao {

	@Inject
	private EntityManager entityManager;

	public FavoriJpaDao() {
		super();
	}

	@Override
	public Favori getFavoriById(Long id) {
		return entityManager.find(Favori.class, id);
	}

	@Override
	public void deleteFavoriById(Long id) {
		entityManager.getTransaction().begin();
		entityManager.createNamedQuery("Favori.deleteById")
				.setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
	public Favori createFavori(Favori favori) {
		entityManager.getTransaction().begin();
		entityManager.persist(favori);
		entityManager.getTransaction().commit();
		return favori;
	}

	@Override
	public Favori updateFavori(Favori favori) {
		entityManager.getTransaction().begin();
		favori = entityManager.merge(favori);
		entityManager.getTransaction().commit();
		return favori;
	}

	@Override
	public List<Favori> findAll(int first, int size) {
		return entityManager.createNamedQuery("Favori.findAll", Favori.class)
				.setFirstResult(first).setMaxResults(size).getResultList();
	}

}
