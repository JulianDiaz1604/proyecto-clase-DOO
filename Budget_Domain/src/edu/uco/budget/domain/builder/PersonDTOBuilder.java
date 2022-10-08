package edu.uco.budget.domain.builder;

import java.util.UUID;

import edu.uco.budget.domain.PersonDTO;

import static edu.uco.budget.domain.PersonDTO.create;

public class PersonDTOBuilder {
	
	private UUID id;
	private String idCard;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secodSurname;
	
	private PersonDTOBuilder() {
		super();
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public final PersonDTOBuilder setIdCard(String idCard) {
		this.idCard = idCard;
		return this;
	}

	public final PersonDTOBuilder setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public final PersonDTOBuilder setSecondName(String secondName) {
		this.secondName = secondName;
		return this;
	}

	public final PersonDTOBuilder setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
		return this;
	}

	public final PersonDTOBuilder setSecodSurname(String secodSurname) {
		this.secodSurname = secodSurname;
		return this;
	}
	
	public static final PersonDTOBuilder getPersonDTOBuilder() {
		return new PersonDTOBuilder();
	}
	
	public final PersonDTO build() {
		return create(id, idCard, firstName, secondName, firstSurname, secodSurname);
	}

}
