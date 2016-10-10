package metier;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c"),
	@NamedQuery(name="Category.find", query="SELECT c FROM Category c WHERE c.catCode = :id"),
	@NamedQuery(name="Category.delete", query="DELETE FROM Category c WHERE c.catCode = :id"),
	@NamedQuery(name="Category.search", query="SELECT c FROM Category c WHERE c.catCode LIKE :term OR c.wording LIKE :term")
})
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String catCode;

	private String picture;

	private String wording;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="category")
	private transient List<Movie> movies;

	public Category() {
	}

	public String getCatCode() {
		return this.catCode;
	}

	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getWording() {
		return this.wording;
	}

	public void setWording(String wording) {
		this.wording = wording;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Movie addMovy(Movie movy) {
		getMovies().add(movy);
		movy.setCategory(this);

		return movy;
	}

	public Movie removeMovy(Movie movy) {
		getMovies().remove(movy);
		movy.setCategory(null);

		return movy;
	}

}