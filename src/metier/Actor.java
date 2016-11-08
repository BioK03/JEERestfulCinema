package metier;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the actor database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Actor.findAll", query="SELECT a FROM Actor a"),
	@NamedQuery(name="Actor.find", query="SELECT a FROM Actor a WHERE a.noAct = :id"),
	@NamedQuery(name="Actor.delete", query="DELETE FROM Actor a WHERE a.noAct = :id"),
	@NamedQuery(name="Actor.search", query="SELECT a FROM Actor a WHERE a.firstnameAct LIKE :term OR a.lastnameAct LIKE :term")
}) 

public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int noAct;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	@Temporal(TemporalType.DATE)
	private Date deathdate;

	private String firstnameAct;

	private String lastnameAct;

	private String picture;

	//bi-directional many-to-one association to Personage
	@OneToMany(mappedBy="actor")
	private transient List<Personage> personages;

	public Actor() {
	}

	public int getNoAct() {
		return this.noAct;
	}

	public void setNoAct(int noAct) {
		this.noAct = noAct;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getDeathdate() {
		return this.deathdate;
	}

	public void setDeathdate(Date deathdate) {
		this.deathdate = deathdate;
	}

	public String getFirstnameAct() {
		return this.firstnameAct;
	}

	public void setFirstnameAct(String firstnameAct) {
		this.firstnameAct = firstnameAct;
	}

	public String getLastnameAct() {
		return this.lastnameAct;
	}

	public void setLastnameAct(String lastnameAct) {
		this.lastnameAct = lastnameAct;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Personage> getPersonages() {
		return this.personages;
	}

	public void setPersonages(List<Personage> personages) {
		this.personages = personages;
	}

	public Personage addPersonage(Personage personage) {
		getPersonages().add(personage);
		personage.setActor(this);

		return personage;
	}

	public Personage removePersonage(Personage personage) {
		getPersonages().remove(personage);
		personage.setActor(null);

		return personage;
	}

}