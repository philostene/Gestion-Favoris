package endpoint;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dao.CategorieDao;
import dao.FavoriDao;


import javax.ws.rs.core.UriInfo;

import modele.Categorie;
import modele.Favori;


@Path("/favoris")
@RequestScoped
public class FavoriResource {

	private static int DEFAULT_PAGE_SIZE = 10;

	@Inject
	private FavoriDao favoriDao;

//	@Inject
//	private CategorieDao categorieDao;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Favori> getFavoris(@QueryParam("from") Integer from,
			@QueryParam("limit") Integer limit) {
		if (from == null) {
			from = 0;
		}
		if (limit == null) {
			limit = DEFAULT_PAGE_SIZE;
		}
		List<Favori> favoris = favoriDao.findAll(from, limit);
		return favoris;
	}
	

//	@GET
//	@Path("{id}/categories")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Categorie> getCategoriesByFavori(@PathParam("id") Long id,
//			@QueryParam("from") Integer from, @QueryParam("limit") Integer limit) {
//		if (from == null) {
//			from = 0;
//		}
//		if (limit == null) {
//			limit = DEFAULT_PAGE_SIZE;
//		}
//		List<Categorie> categories = categorieDao.findAllForFavoriId(id, from,
//				limit);
//		return categories;
//	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFavoriById(@PathParam("id") Long id) {
		Favori favori = favoriDao.getFavoriById(id);
		if (favori != null)
			return Response.ok(favori).build();
		return Response.status(Status.NOT_FOUND).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createFavori(Favori favoriACreer, @Context UriInfo uriInfo) {
		if (!isFavoriValid(favoriACreer)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Favori favori = favoriDao.createFavori(favoriACreer);
		if (favori != null) {
			URL url;
			URI uri;
			try {
				url = new URL(uriInfo.getAbsolutePath().toURL()
						.toExternalForm()
						+ "/" + favori.getId());
				uri = url.toURI();
			} catch (MalformedURLException e) {
				return Response.status(Status.BAD_REQUEST).build();
			} catch (URISyntaxException e) {
				return Response.status(Status.BAD_REQUEST).build();
			}
			return Response.created(uri).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

//	@POST
//	@Path("{id}/categories")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response createCategorie(@PathParam("id") Long id,
//			Categorie categorieACreer, @Context UriInfo uriInfo) {
//		Categorie categorie = categorieDao.createCategorie(categorieACreer);
//		if (categorie != null) {
//			URL url;
//			URI uri;
//			try {
//				url = new URL(uriInfo.getAbsolutePath().toURL()
//						.toExternalForm().replaceFirst("/favoris/[0-9]+/", "/")
//						+ "/" + categorie.getId());
//				uri = url.toURI();
//			} catch (MalformedURLException e) {
//				return Response.status(Status.BAD_REQUEST).build();
//			} catch (URISyntaxException e) {
//				return Response.status(Status.BAD_REQUEST).build();
//			}
//			return Response.created(uri).build();
//		}
//		return Response.status(Status.BAD_REQUEST).build();
//	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateFavori(@PathParam("id") Long id, Favori favoriAModifier) {
		if (!isFavoriValid(favoriAModifier)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		favoriAModifier.setId(id);
		favoriDao.updateFavori(favoriAModifier);
		return Response.ok().build();
	}

	@DELETE
	@Path("{id}")
	public void deleteFavoriById(@PathParam("id") Long id) {
		favoriDao.deleteFavoriById(id);
	}
	
	private boolean isFavoriValid(Favori favori) {
		boolean valid = true;
		valid &= (favori.getNom() != null);
		valid &= (favori.getUrl() != null);
		valid &= (favori.getDate_creation() != null);
		return valid;
	}

}
