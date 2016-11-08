package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;

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
	public void addActor(
			@FormParam("lastnameAct") String lastnameAct,
			@FormParam("firstnameAct") String firstnameAct,
			@FormParam("birthdate") String birthdate,
			@FormParam("deathdate") String deathdate,			
			@FormParam("picture") String picture) throws Exception {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Actor a = new Actor();
		
		DateFormat format = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);
		
		a.setLastnameAct(lastnameAct);
		a.setFirstnameAct(firstnameAct);
		a.setBirthdate(format.parse(birthdate));
		a.setDeathdate(format.parse(deathdate));		
		a.setPicture(picture);
		
		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("category/add/")
	public void addCategory(
			@FormParam("catCode") String catCode,
			@FormParam("wording") String wording,
			@FormParam("picture") String picture) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Category c = new Category();
		c.setCatCode(catCode);
		c.setWording(wording);
		c.setPicture(picture);
		
		em.persist(c);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("director/add/")
	public void addDirector(
			@FormParam("lastnameRea") String lastnameRea,
			@FormParam("firstnameRea") String firstnameRea,
			@FormParam("picture") String picture){
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Director d = new Director();
		d.setLastnameRea(lastnameRea);
		d.setFirstnameRea(firstnameRea);
		d.setPicture(picture);
		
		em.persist(d);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("movie/add/")
	public void addMovie(
			@FormParam("title") String title,
			@FormParam("duration") String duration,
			@FormParam("releaseDate") String releaseDate,
			@FormParam("budget") String budget,
			@FormParam("incomings") String incomings,
			@FormParam("director") int directorId,
			@FormParam("category") int categoryId,
			@FormParam("picture") String picture,
			@FormParam("allocineLink") String allocineLink) throws ParseException {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		DateFormat format = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);
		
		List<Director> directors = em.createNamedQuery("Director.find", Director.class).setParameter("id", directorId).getResultList();
		List<Category> categories = em.createNamedQuery("Category.find", Category.class).setParameter("id", categoryId).getResultList();
		
		Movie m = new Movie();
		m.setTitle(title);
		m.setDuration(Integer.parseInt(duration));
		m.setReleaseDate(format.parse(releaseDate));
		m.setBudget(Integer.parseInt(budget));
		m.setIncomings(Integer.parseInt(incomings));
		m.setDirector(directors.get(0));
		m.setCategory(categories.get(0));
		m.setPicture(picture);
		m.setAllocineLink(allocineLink);
		
		em.persist(m);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("personage/add/")
	public void addPersonage(
			@FormParam("actor") int actorId,
			@FormParam("movie") int movieId,
			@FormParam("persName") String persName) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Personage p = new Personage();
		p.setPersName(persName);
		List<Movie> movies = em.createNamedQuery("Movie.find", Movie.class).setParameter("id", movieId).getResultList();
		List<Actor> actors = em.createNamedQuery("Actor.find", Actor.class).setParameter("id", actorId).getResultList();
		p.setActor(actors.get(0));
		p.setMovie(movies.get(0));
		
		em.persist(p);
		
		em.getTransaction().commit();
		em.close();
	}
	
	/*
	 ■■■ ■■  ■■■ ■■■
	 ■   ■ ■  ■   ■
	 ■■  ■ ■  ■   ■
	 ■   ■ ■  ■   ■
	 ■■■ ■■  ■■■  ■ 
	 */
	
	@POST
	@Path("/actor/edit/")
	public void editActor(
			@FormParam("noAct") String actorId,
			@FormParam("birthdate") String birthdate,
			@FormParam("deathdate") String deathdate,
			@FormParam("firstnameAct") String firstnameAct,
			@FormParam("lastnameAct") String lastnameAct,
			@FormParam("picture") String picture) throws ParseException {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Actor a = em.createNamedQuery("Actor.find", Actor.class).setParameter("id", actorId).getResultList().get(0);
		
		DateFormat format = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);
		
		a.setLastnameAct(lastnameAct);
		a.setFirstnameAct(firstnameAct);
		a.setBirthdate(format.parse(birthdate));
		a.setDeathdate(format.parse(deathdate));
		a.setPicture(picture);
		
		em.flush();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("/category/edit/")
	public void editCategory(
			@FormParam("catCode") String categoryId, 
			@FormParam("wording") String wording,
			@FormParam("picture") String picture) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Category c = em.createNamedQuery("Category.find", Category.class).setParameter("id", categoryId).getResultList().get(0);
		c.setWording(wording);
		c.setPicture(picture);
		
		em.flush();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("/director/edit/")
	public void editDirector(
			@FormParam("noRea") String directorId,
			@FormParam("lastnameRea") String lastnameRea,
			@FormParam("firstnameRea") String firstnameRea,
			@FormParam("picture") String picture) {
			
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Director d = em.createNamedQuery("Director.find", Director.class).setParameter("id", directorId).getResultList().get(0);
		d.setLastnameRea(lastnameRea);
		d.setFirstnameRea(firstnameRea);
		d.setPicture(picture);
		
		em.flush();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("/movie/edit/")
	public void editMovie(
			@FormParam("noMovie") String movieId,
			@FormParam("title") String title,
			@FormParam("duration") String duration,
			@FormParam("releaseDate") String releaseDate,
			@FormParam("budget") String budget,
			@FormParam("incomings") String incomings,
			@FormParam("director") String directorId,
			@FormParam("category") String categoryId,
			@FormParam("picture") String picture,
			@FormParam("allocineLink") String allocineLink) throws ParseException {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		DateFormat format = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH);
		
		List<Director> directors = em.createNamedQuery("Director.find", Director.class).setParameter("id", directorId).getResultList();
		List<Category> categories = em.createNamedQuery("Category.find", Category.class).setParameter("id", categoryId).getResultList();
		
		Movie m = em.createNamedQuery("Movie.find", Movie.class).setParameter("id", movieId).getResultList().get(0);
		m.setTitle(title);
		m.setDuration(Integer.parseInt(duration));
		m.setReleaseDate(format.parse(releaseDate));
		m.setBudget(Integer.parseInt(budget));
		m.setIncomings(Integer.parseInt(incomings));
		m.setDirector(directors.get(0));
		m.setCategory(categories.get(0));
		m.setPicture(picture);
		m.setAllocineLink(allocineLink);
		
		em.flush();
		
		em.getTransaction().commit();
		em.close();
	}
	
	@POST
	@Path("/personage/edit/")
	public void editPersonage(
			@FormParam("noMovie") String movieId,
			@FormParam("noAct") String actorId,
			@FormParam("persName") String persName) {
		emf = Persistence.createEntityManagerFactory("cinema");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Personage p = em.createNamedQuery("Personage.find", Personage.class).setParameter("movieid", movieId).setParameter("actorid", actorId)
				.getResultList().get(0);
		p.setPersName(persName);
		
		em.flush();
		
		em.getTransaction().commit();
		em.close();
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
