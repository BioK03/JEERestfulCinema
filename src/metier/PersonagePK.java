package metier;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the personage database table.
 * 
 */
@Embeddable
public class PersonagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int noMovie;

	@Column(insertable=false, updatable=false)
	private int noAct;

	public PersonagePK() {
	}
	public int getNoMovie() {
		return this.noMovie;
	}
	public void setNoMovie(int noMovie) {
		this.noMovie = noMovie;
	}
	public int getNoAct() {
		return this.noAct;
	}
	public void setNoAct(int noAct) {
		this.noAct = noAct;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PersonagePK)) {
			return false;
		}
		PersonagePK castOther = (PersonagePK)other;
		return 
			(this.noMovie == castOther.noMovie)
			&& (this.noAct == castOther.noAct);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.noMovie;
		hash = hash * prime + this.noAct;
		
		return hash;
	}
}