package dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import modele.Categorie;

@Named
public class CategorieJpaDao implements CategorieDao {

	@Inject
	private EntityManager entityManager;

	public CategorieJpaDao() {
		super();
	}
	
	@Override
	public List<Categorie> findAll(int first, int size) {
		return entityManager
				.createNamedQuery("Categorie.findAll", Categorie.class)
				.setFirstResult(first).setMaxResults(size).getResultList();
	}

	@Override
	public List<Categorie> findAllForFavoriId(Long idFavori, int first, int size) {
		return entityManager
				.createNamedQuery("Categorie.findAllByFavoriId", Categorie.class)
				.setParameter("id", idFavori).setFirstResult(first)
				.setMaxResults(size).getResultList();
	}

	@Override
	public Categorie getCategorieById(Long id) {
		return entityManager.find(Categorie.class, id);
	}

	@Override
	public void deleteCategorieById(Long id) {
		entityManager.getTransaction().begin();
		entityManager.createNamedQuery("Categorie.deleteById")
				.setParameter("id", id).executeUpdate();
		entityManager.getTransaction().commit();
	}

	@Override
	public Categorie updateCategorie(Categorie categorie) {
		entityManager.getTransaction().begin();
		categorie = entityManager.merge(categorie);
		entityManager.getTransaction().commit();
		return categorie;
	}

	@Override
	public Categorie createCategorie(Categorie categorie) {
		entityManager.getTransaction().begin();
		entityManager.persist(categorie);
		entityManager.getTransaction().commit();
		return categorie;
	}


}
