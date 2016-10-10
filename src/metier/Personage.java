package metier;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the personage database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Personage.findAll", query="SELECT p FROM Personage p"),
	@NamedQuery(name="Personage.find", query="SELECT p FROM Personage p WHERE p.movie.noMovie = :movieid AND p.actor.noAct = :actorid"),
	@NamedQuery(name="Personage.findByMovie", query="SELECT p FROM Personage p WHERE p.movie.noMovie = :movieid"),
	@NamedQuery(name="Personage.findByActor", query="SELECT p FROM Personage p WHERE p.actor.noAct = :actorid"),
	@NamedQuery(name="Personage.delete", query="DELETE FROM Personage p WHERE p.movie.noMovie = :movieid AND p.actor.noAct = :actorid"),
	@NamedQuery(name="Personage.deleteByActor", query="DELETE FROM Personage p WHERE p.actor.noAct = :actorid"),
	@NamedQuery(name="Personage.deleteByMovie", query="DELETE FROM Personage p WHERE p.movie.noMovie = :movieid"),
	@NamedQuery(name="Personage.deleteByMovieCategory", query="DELETE FROM Personage p WHERE p.movie.category.catCode = :categoryid"),
	@NamedQuery(name="Personage.search", query="SELECT p FROM Personage p WHERE p.persName LIKE :term")
})

public class Personage implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PersonagePK id;

	private String persName;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="NoMovie")
	private Movie movie;

	//bi-directional many-to-one association to Actor
	@ManyToOne
	@JoinColumn(name="NoAct")
	private Actor actor;

	public Personage() {
	}

	public PersonagePK getId() {
		return this.id;
	}

	public void setId(PersonagePK id) {
		this.id = id;
	}

	public String getPersName() {
		return this.persName;
	}

	public void setPersName(String persName) {
		this.persName = persName;
	}

	public Movie getMovie() {
		return this.movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Actor getActor() {
		return this.actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}