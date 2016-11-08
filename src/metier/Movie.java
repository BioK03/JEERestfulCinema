package metier;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the movie database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Movie.findAll", query="SELECT m FROM Movie m"),
	@NamedQuery(name="Movie.find", query="SELECT m FROM Movie m WHERE m.noMovie = :id"),
	@NamedQuery(name="Movie.findByCategory", query="SELECT m FROM Movie m WHERE m.category.catCode = :categoryid"),
	@NamedQuery(name="Movie.findByDirector", query="SELECT m FROM Movie m WHERE m.director.noRea = :directorid"),
	@NamedQuery(name="Movie.delete", query="DELETE FROM Movie m WHERE m.noMovie = :id"),
	@NamedQuery(name="Movie.deleteByCategory", query="DELETE FROM Movie m WHERE m.category.catCode = :categoryid"),
	@NamedQuery(name="Movie.search", query="SELECT m FROM Movie m WHERE m.title LIKE :term")
})

public class Movie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int noMovie;

	private String allocineLink;

	private int budget;

	private int duration;

	private int incomings;

	private String picture;

	@Temporal(TemporalType.DATE)
	private Date releaseDate;

	private String title;

	//bi-directional many-to-one association to Director
	@ManyToOne
	@JoinColumn(name="NoRea")
	private Director director;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="CatCode")
	private Category category;

	//bi-directional many-to-one association to Personage
	@OneToMany(mappedBy="movie")
	private transient List<Personage> personages;

	public Movie() {
	}

	public int getNoMovie() {
		return this.noMovie;
	}

	public void setNoMovie(int noMovie) {
		this.noMovie = noMovie;
	}

	public String getAllocineLink() {
		return this.allocineLink;
	}

	public void setAllocineLink(String allocineLink) {
		this.allocineLink = allocineLink;
	}

	public int getBudget() {
		return this.budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getIncomings() {
		return this.incomings;
	}

	public void setIncomings(int incomings) {
		this.incomings = incomings;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Director getDirector() {
		return this.director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Personage> getPersonages() {
		return this.personages;
	}

	public void setPersonages(List<Personage> personages) {
		this.personages = personages;
	}

	public Personage addPersonage(Personage personage) {
		getPersonages().add(personage);
		personage.setMovie(this);

		return personage;
	}

	public Personage removePersonage(Personage personage) {
		getPersonages().remove(personage);
		personage.setMovie(null);

		return personage;
	}

}