package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import metier.Actor;
import metier.Category;
import metier.Director;
import metier.Movie;
import metier.Personage;

@Path("/cinema")
public class WService {
	private EntityManagerFactory emf;
	
	/* 
	 ■   ■■■ ■■■ ■■■ ■■■ ■■■
	 ■    ■  ■    ■  ■   ■  
	 ■    ■  ■■■  ■  ■■  ■■■
	 ■    ■    ■  ■  ■     ■
	 ■■■ ■■■ ■■■  ■  ■■■ ■■■ 
	 */
	@GET
	@Path("/actor/list")
	@Produces("application/json")
	public String actorList() {
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Actor> actors = em.createNamedQuery("Actor.findAll", Actor.class).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(actors);
		return json;
	}
	
	@GET
	@Path("/category/list")
	@Produces("application/json")
	public String categoryList() {
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
		
		em.getTransaction().commit();
		em.close();

		String json = gson.toJson(categories);
		return json;
	}
	
	@GET
	@Path("/director/list")
	@Produces("application/json")
	public String directorList() {
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Director> directors = em.createNamedQuery("Director.findAll", Director.class).getResultList();
		
		em.getTransaction().commit();
		em.close();

		String json = gson.toJson(directors);
		return json;
	}
	
	@GET
	@Path("/movie/list")
	@Produces("application/json")
	public String movieList() {
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Movie> movies = em.createNamedQuery("Movie.findAll", Movie.class).getResultList();
		
		em.getTransaction().commit();
		em.close();

		String json = gson.toJson(movies);
		return json;
	}
	
	@GET
	@Path("/personage/list")
	@Produces("application/json")
	public String personageList() {
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Personage> personages = em.createNamedQuery("Personage.findAll", Personage.class).getResultList();
		
		em.getTransaction().commit();
		em.close();

		String json = gson.toJson(personages);
		return json;
	}
	
	/*
	 ■■■ ■■■ ■■■
	 ■   ■    ■
	 ■■■ ■■   ■
	 ■ ■ ■    ■
	 ■■■ ■■■  ■
	 */
	
	@GET
	@Path("/actor/{id}")
	@Produces("application/json")
	public String fetchActor(@PathParam("id")  int actorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Actor> actors = em.createNamedQuery("Actor.find", Actor.class).setParameter("id", actorId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(actors);
		return json;
	}
	
	@GET
	@Path("/actor/movie/{movieid}")
	@Produces("application/json")
	public String fetchActorByMovie(@PathParam("movieid")  int movieId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Personage> personages = em.createNamedQuery("Personage.findByMovie", Personage.class).setParameter("movieid", movieId).getResultList();
		
		List<Actor> actorList = new ArrayList<Actor>();
		for(Personage p : personages)
		{
			actorList.add(p.getActor());
		}
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(actorList);
		return json;
	}
	
	@GET
	@Path("/category/{id}")
	@Produces("application/json")
	public String fetchCategory(@PathParam("id")  String categoryId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Category> categories = em.createNamedQuery("Category.find", Category.class).setParameter("id", categoryId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(categories);
		return json;
	}
	
	@GET
	@Path("/director/{id}")
	@Produces("application/json")
	public String fetchDirector(@PathParam("id")  int directorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Director> directors = em.createNamedQuery("Director.find", Director.class).setParameter("id", directorId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(directors);
		return json;
	}
	
	@GET
	@Path("/movie/{id}")
	@Produces("application/json")
	public String fetchMovie(@PathParam("id")  int movieId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Movie> movies = em.createNamedQuery("Movie.find", Movie.class).setParameter("id", movieId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(movies);
		return json;
	}
	
	@GET
	@Path("/movie/actor/{actorid}")
	@Produces("application/json")
	public String fetchMovieByActor(@PathParam("actorid")  int actorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Personage> personages = em.createNamedQuery("Personage.findByActor", Personage.class).setParameter("actorid", actorId).getResultList();
		
		List<Movie> movies = new ArrayList<Movie>();
		for(Personage p : personages)
		{
			movies.add(p.getMovie());
		}
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(movies);
		return json;
	}
	
	@GET
	@Path("/movie/category/{categoryid}")
	@Produces("application/json")
	public String fetchMovieByCategory(@PathParam("categoryid")  String categoryId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Movie> movies = em.createNamedQuery("Movie.findByCategory", Movie.class).setParameter("categoryid", categoryId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(movies);
		return json;
	}
	
	@GET
	@Path("/movie/director/{directorid}")
	@Produces("application/json")
	public String fetchMovieByDirector(@PathParam("directorid")  int directorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Movie> movies = em.createNamedQuery("Movie.findByDirector", Movie.class).setParameter("directorid", directorId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(movies);
		return json;
	}
	
	@GET
	@Path("/personage/{movieid}/{actorid}")
	@Produces("application/json")
	public String fetchPersonage(@PathParam("movieid")  int movieId, @PathParam("actorid") int actorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Personage> personages = em.createNamedQuery("Personage.find", Personage.class).setParameter("movieid", movieId).setParameter("actorid", actorId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(personages);
		return json;
	}
	
	@GET
	@Path("/personage/movie/{movieid}")
	@Produces("application/json")
	public String fetchPersonageByMovie(@PathParam("movieid")  int movieId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Personage> personages = em.createNamedQuery("Personage.findByMovie", Personage.class).setParameter("movieid", movieId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(personages);
		return json;
	}
	
	@GET
	@Path("/personage/actor/{actorid}")
	@Produces("application/json")
	public String fetchPersonageByActor(@PathParam("actorid")  int actorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Personage> personages = em.createNamedQuery("Personage.findByActor", Personage.class).setParameter("actorid", actorId).getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		String json = gson.toJson(personages);
		return json;
	}
	
	/*
	 ■■■ ■■  ■■ 
	 ■ ■ ■ ■ ■ ■
	 ■■■ ■ ■ ■ ■
	 ■ ■ ■ ■ ■ ■
	 ■ ■ ■■  ■■
	 */
	
	@POST
	@Path("actor/add/")
	public void addActor(@FormParam("actor") String actorString) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Actor actor = gson.fromJson(actorString, Actor.class);
		
		em.persist(actor);
	}
	
	@POST
	@Path("category/add/")
	public void addCategory(@FormParam("catCode") String categoryCatCode, @FormParam("wording") String categoryWording) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Category c = new Category();
		c.setCatCode(categoryCatCode);
		c.setWording(categoryWording);
		c.setPicture(null);
		
		em.persist(c);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	@POST
	@Path("director/add/{director}")
	@Consumes("application/json")
	public void addDirector(@PathParam("director") String directorString) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Director director = gson.fromJson(directorString, Director.class);
		
		em.persist(director);
	}
	
	@POST
	@Path("movie/add/{movie}")
	@Consumes("application/json")
	public void addMovie(@PathParam("movie") String movieString) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Movie movie = gson.fromJson(movieString, Movie.class);
		
		em.persist(movie);
	}
	
	@POST
	@Path("personage/add/{personage}")
	@Consumes("application/json")
	public void addPersonage(@PathParam("personage") String personageString) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Personage personage = gson.fromJson(personageString, Personage.class);
		
		em.persist(personage);
	}
	
	/*
	 ■■■ ■■  ■■■ ■■■
	 ■   ■ ■  ■   ■
	 ■■  ■ ■  ■   ■
	 ■   ■ ■  ■   ■
	 ■■■ ■■  ■■■  ■ 
	 */
	
	@POST
	@Path("/actor/edit/{actor}")
	@Consumes("application/json")
	public void editActor(@PathParam("actor") String actorString) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Actor actor = gson.fromJson(actorString, Actor.class);
		
		Actor actorToBeUpdated = em.createNamedQuery("Actor.find", Actor.class).setParameter("id", actor.getNoAct()).getResultList().get(0);
		
		em.getTransaction().begin();
		
		actorToBeUpdated.setBirthdate(actor.getBirthdate());
		actorToBeUpdated.setDeathdate(actor.getDeathdate());
		actorToBeUpdated.setFirstnameAct(actor.getFirstnameAct());
		actorToBeUpdated.setLastnameAct(actor.getLastnameAct());
		actorToBeUpdated.setPersonages(actor.getPersonages());
		actor.setPicture(actor.getPicture());
		
		em.getTransaction().commit();
	}
	
	@POST
	@Path("/category/edit/")
	public void editCategory(@FormParam("catCode") String categoryCatCode, @FormParam("wording") String categoryWording) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Category c = em.createNamedQuery("Category.find", Category.class).setParameter("id", categoryCatCode).getResultList().get(0);
		
		c.setWording(categoryWording);
		
		em.flush();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("/director/edit/{director}")
	@Consumes("application/json")
	public void editDirector(@PathParam("director") String directorString) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Director director = gson.fromJson(directorString, Director.class);
		
		Director directorToBeUpdated = em.createNamedQuery("Director.find", Director.class).setParameter("id", director.getNoRea()).getResultList().get(0);
		
		em.getTransaction().begin();
		
		directorToBeUpdated.setFirstnameRea(director.getFirstnameRea());
		directorToBeUpdated.setLastnameRea(director.getLastnameRea());
		directorToBeUpdated.setMovies(director.getMovies());
		directorToBeUpdated.setPicture(director.getPicture());
		
		em.getTransaction().commit();
	}
	
	@POST
	@Path("/movie/edit/{movie}")
	@Consumes("application/json")
	public void editMovie(@PathParam("movie") String movieString) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Movie movie = gson.fromJson(movieString, Movie.class);
		
		Movie movieToBeUpdated = em.createNamedQuery("Movie.find", Movie.class).setParameter("id", movie.getNoMovie()).getResultList().get(0);
		
		em.getTransaction().begin();
		
		movieToBeUpdated.setAllocineLink(movie.getAllocineLink());
		movieToBeUpdated.setBudget(movie.getBudget());
		movieToBeUpdated.setCategory(movie.getCategory());
		movieToBeUpdated.setDirector(movie.getDirector());
		movieToBeUpdated.setDuration(movie.getDuration());
		movieToBeUpdated.setIncomings(movie.getIncomings());
		movieToBeUpdated.setPersonages(movie.getPersonages());
		movieToBeUpdated.setPicture(movie.getPicture());
		movieToBeUpdated.setReleaseDate(movie.getReleaseDate());
		movieToBeUpdated.setTitle(movie.getTitle());
		
		em.getTransaction().commit();
	}
	
	@POST
	@Path("/personage/edit/{personage}")
	@Consumes("application/json")
	public void editPersonage(@PathParam("personage") String personageString) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Personage personage = gson.fromJson(personageString, Personage.class);
		
		Personage personageToBeUpdated = em.createNamedQuery("Personage.find", Personage.class)
				.setParameter("movieid", personage.getMovie().getNoMovie()).setParameter("actorid", personage.getActor().getNoAct()).getResultList().get(0);
		
		em.getTransaction().begin();
		
		personageToBeUpdated.setPersName(personage.getPersName());
		
		em.getTransaction().commit();
	}
	
	/*
	 ■■  ■■■ ■   ■■■ ■■■ ■■■
	 ■ ■ ■   ■   ■    ■  ■
	 ■ ■ ■■  ■   ■■   ■  ■■
	 ■ ■ ■   ■   ■    ■  ■
	 ■■  ■■■ ■■■ ■■■  ■  ■■■
	 */
	
	@GET
	@Path("/actor/delete/{id}")
	public void deleteActor(@PathParam("id")  int actorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Personage.deleteByActor", Actor.class).setParameter("actorid", actorId).executeUpdate();
		em.createNamedQuery("Actor.delete", Actor.class).setParameter("id", actorId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/category/delete/{id}")
	public void deleteCategory(@PathParam("id")  String categoryId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Personage.deleteByMovieCategory", Personage.class).setParameter("categoryid", categoryId).executeUpdate();
		em.createNamedQuery("Movie.deleteByCategory", Movie.class).setParameter("categoryid", categoryId).executeUpdate();
		em.createNamedQuery("Category.delete", Category.class).setParameter("id", categoryId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/director/delete/{id}")
	public void deleteDirector(@PathParam("id")  int directorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Director.delete", Director.class).setParameter("id", directorId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/movie/delete/{id}")
	public void deleteMovie(@PathParam("id")  int movieId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Personage.deleteByMovie", Personage.class).setParameter("movieid", movieId).executeUpdate();
		em.createNamedQuery("Movie.delete", Movie.class).setParameter("id", movieId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/movie/delete/category/{categoryid}")
	public void deleteMovieByCategory(@PathParam("categoryid")  String categoryId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Personage.deleteByMovieCategory", Personage.class).setParameter("categoryid", categoryId).executeUpdate();
		em.createNamedQuery("Movie.deleteByCategory", Movie.class).setParameter("categoryid", categoryId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/personage/delete/{movieid}/{actorid}")
	public void deletePersonage(@PathParam("movieid")  int movieId, @PathParam("actorid") int actorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Personage.delete", Personage.class).setParameter("movieid", movieId).setParameter("actorid", actorId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/personage/delete/actor/{actorid}")
	public void deletePersonageByActor(@PathParam("actorid") int actorId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Personage.deleteByActor", Personage.class).setParameter("actorid", actorId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/personage/delete/movie/{movieid}")
	public void deletePersonageByMovie(@PathParam("movieid") int movieId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Personage.deleteByMovie", Personage.class).setParameter("movieid", movieId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@GET
	@Path("/personage/delete/category/{categoryid}")
	public void deletePersonageByCategory(@PathParam("categoryid") int categoryId) throws Exception
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.createNamedQuery("Personage.deleteByMovieCategory", Personage.class).setParameter("categoryid", categoryId).executeUpdate();
		
		em.getTransaction().commit();
		em.close();
	}
	
	/*
	 ■■■ ■■■ ■■■ ■■■ ■■■ ■ ■
	 ■   ■   ■ ■ ■ ■ ■   ■ ■
	 ■■■ ■■  ■■■ ■■■ ■   ■■■
	   ■ ■   ■ ■ ■■  ■   ■ ■
	 ■■■ ■■■ ■ ■ ■ ■ ■■■ ■ ■
	 */
	
	@SuppressWarnings("rawtypes")
	@GET
	@Path("/search/{term}")
	@Produces("application/json")
	public String search(@PathParam("term")  String term) throws Exception
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		Map<String, Set> mapResult = new HashMap<String, Set>();
		
		Set<Actor> actorSet = actorSetSearch(term);
		mapResult.put("actors", actorSet);
		
		Set<Category> categorySet = categorySetSearch(term);
		mapResult.put("categories", categorySet);
		
		Set<Director> directorSet = directorSetSearch(term);
		mapResult.put("directors", directorSet);
		
		Set<Movie> movieSet = movieSetSearch(term);
		mapResult.put("movies", movieSet);
		
		Set<Personage> personageSet = personageSetSearch(term);
		mapResult.put("personages", personageSet);
		
		String json = gson.toJson(mapResult);
		return json;
	}
	
	@GET
	@Path("/actor/search/{term}")
	@Produces("application/json")
	public String actorSearch(@PathParam("term")  String term) throws Exception
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String json = gson.toJson(actorSetSearch(term));
		return json;
	}
	
	private Set<Actor> actorSetSearch(String term)
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Actor> actors = em.createNamedQuery("Actor.search", Actor.class).setParameter("term", term).getResultList();
		
		Set<Actor> actorSet = new LinkedHashSet<>(actors);
		
		em.getTransaction().commit();
		em.close();
		
		return actorSet;
	}
	
	@GET
	@Path("/category/search/{term}")
	@Produces("application/json")
	public String categorySearch(@PathParam("term")  String term) throws Exception
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String json = gson.toJson(categorySetSearch(term));
		return json;
	}
	
	private Set<Category> categorySetSearch(String term)
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Category> categories = em.createNamedQuery("Category.search", Category.class).setParameter("term", term).getResultList();
		
		Set<Category> categorySet = new LinkedHashSet<>(categories);
		
		em.getTransaction().commit();
		em.close();
		
		return categorySet;
	}
	
	@GET
	@Path("/director/search/{term}")
	@Produces("application/json")
	public String directorSearch(@PathParam("term")  String term) throws Exception
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String json = gson.toJson(directorSetSearch(term));
		return json;
	}
	
	private Set<Director> directorSetSearch(String term)
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Director> directors = em.createNamedQuery("Director.search", Director.class).setParameter("term", term).getResultList();
		
		Set<Director> directorSet = new LinkedHashSet<>(directors);
		
		em.getTransaction().commit();
		em.close();
		
		return directorSet;
	}

	@GET
	@Path("/movie/search/{term}")
	@Produces("application/json")
	public String movieSearch(@PathParam("term")  String term) throws Exception
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String json = gson.toJson(movieSetSearch(term));
		return json;
	}
	
	private Set<Movie> movieSetSearch(String term)
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Movie> movies = em.createNamedQuery("Movie.search", Movie.class).setParameter("term", term).getResultList();
		
		Set<Movie> movieSet = new LinkedHashSet<>(movies);
		
		em.getTransaction().commit();
		em.close();
		
		return movieSet;
	}
	
	@GET
	@Path("/personage/search/{term}")
	@Produces("application/json")
	public String personageSearch(@PathParam("term")  String term) throws Exception
	{
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		
		String json = gson.toJson(personageSetSearch(term));
		return json;
	}
	
	private Set<Personage> personageSetSearch(String term)
	{
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		List<Personage> personages = em.createNamedQuery("Personage.search", Personage.class).setParameter("term", term).getResultList();
		
		Set<Personage> personageSet = new LinkedHashSet<>(personages);
		
		em.getTransaction().commit();
		em.close();
		
		return personageSet;
	}
}
