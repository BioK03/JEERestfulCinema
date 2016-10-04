package metier;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the director database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Director.findAll", query="SELECT d FROM Director d"),
	@NamedQuery(name="Director.find", query="SELECT d FROM Director d WHERE d.noRea = :id"),
	@NamedQuery(name="Director.delete", query="DELETE FROM Director d WHERE d.noRea = :id"),
	@NamedQuery(name="Director.search", query="SELECT d FROM Director d WHERE d.firstnameRea LIKE :term OR d.lastnameRea LIKE :term")
})
public class Director implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int noRea;

	private String firstnameRea;

	private String lastnameRea;

	private String picture;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="director")
	private transient List<Movie> movies;

	public Director() {
	}

	public int getNoRea() {
		return this.noRea;
	}

	public void setNoRea(int noRea) {
		this.noRea = noRea;
	}

	public String getFirstnameRea() {
		return this.firstnameRea;
	}

	public void setFirstnameRea(String firstnameRea) {
		this.firstnameRea = firstnameRea;
	}

	public String getLastnameRea() {
		return this.lastnameRea;
	}

	public void setLastnameRea(String lastnameRea) {
		this.lastnameRea = lastnameRea;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Movie addMovy(Movie movy) {
		getMovies().add(movy);
		movy.setDirector(this);

		return movy;
	}

	public Movie removeMovy(Movie movy) {
		getMovies().remove(movy);
		movy.setDirector(null);

		return movy;
	}

}