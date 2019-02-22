
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Procession extends DomainEntity {

	// Properties

	private String	ticker;
	private String	title;
	private String	description;
	private Date	momentOrganised;
	private boolean	ffinal;


	@NotNull
	@NotBlank
	@Pattern(regexp = "^\\d{6}-[A-Z]{6}$")
	public String getTicker() {
		return this.ticker;
	}
	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotNull
	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}

	@NotNull
	@NotBlank
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	public Date getMomentOrganised() {
		return this.momentOrganised;
	}
	public void setMomentOrganised(final Date momentOrganised) {
		this.momentOrganised = momentOrganised;
	}

	public boolean isFfinal() {
		return this.ffinal;
	}
	public void setFfinal(final boolean ffinal) {
		this.ffinal = ffinal;
	}


	//Relationships

	private List<DFloat>	dFloats;
	private Finder			finder;


	@Valid
	@NotNull
	@ManyToMany
	public List<DFloat> getdFloats() {
		return this.dFloats;
	}

	public void setdFloats(final List<DFloat> dFloats) {
		this.dFloats = dFloats;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Finder getFinder() {
		return this.finder;
	}

	public void setFinder(final Finder finder) {
		this.finder = finder;
	}

}
