package endpoint;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.CategorieDao;
import modele.Categorie;

@Path("/categories")
@RequestScoped
public class CategorieResource {

	private static int DEFAULT_PAGE_SIZE = 10;

	@Inject
	private CategorieDao categorieDao;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categorie> getCategories(@QueryParam("from") Integer from,
			@QueryParam("limit") Integer limit) {
		if (from == null) {
			from = 0;
		}
		if (limit == null) {
			limit = DEFAULT_PAGE_SIZE;
		}
		List<Categorie> categories = categorieDao.findAll(from, limit);
		return categories;
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategorieById(@PathParam("id") Long id) {
		Categorie categorie = categorieDao.getCategorieById(id);
		if (categorie != null)
			return Response.ok(categorie).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCategorie(@PathParam("id") Long id, Categorie categorieAModifier) {
		categorieAModifier.setId(id);
		categorieDao.updateCategorie(categorieAModifier);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deleteCategorieById(@PathParam("id") Long id) {
		if (id == null){
			throw new IllegalArgumentException("L'id doit être renseigné");
		}
		categorieDao.deleteCategorieById(id);
	}
	
	/**
	 * @return the categorieDao
	 */
	protected CategorieDao getCategorieDao() {
		return categorieDao;
	}

	/**
	 * @param categorieDao the categorieDao to set
	 */
	protected void setCategorieDao(CategorieDao categorieDao) {   //car même nom de package dans java et test
		this.categorieDao = categorieDao;
	}
}
