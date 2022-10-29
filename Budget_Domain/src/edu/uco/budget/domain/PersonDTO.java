package edu.uco.budget.domain;

import java.util.UUID;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDFromString;;

public class PersonDTO {
	
	private UUID id;
	private String idCard;
	private String firstName;
	private String secondName;
	private String firstSurname;
	private String secodSurname;
	
	public PersonDTO() {
		super();
	}
	
	public PersonDTO(final UUID id, final String idCard, final String firstName, final String secondName, final String firstSurname, final String secondSurname) {
		setId(id);
		setIdCard(idCard);
		setFirstName(firstName);
		setSecundName(secondName);
		setFirstSurname(firstSurname);
		setSecondSurname(secondSurname);
	}
	
	public static final PersonDTO create(final UUID id, final String idCard, final String firstName, final String secondName, final String firstSurname, final String secondSurname) {
		return new PersonDTO(id, idCard, firstName, secondName, firstName, secondSurname);
	}
	
	public static final PersonDTO create(final String id, final String idCard, final String firstName, final String secondName, final String firstSurname, final String secondSurname) {
		return new PersonDTO(getUUIDFromString(id), idCard, firstName, secondName, firstName, secondSurname);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = getDefaultUUID(id);
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecundName() {
		return secondName;
	}

	public void setSecundName(String secundName) {
		this.secondName = secundName;
	}

	public String getFirstSurname() {
		return firstSurname;
	}

	public void setFirstSurname(String firstSurname) {
		this.firstSurname = firstSurname;
	}

	public String getSecondSurname() {
		return secodSurname;
	}

	public void setSecondSurname(String seconSurname) {
		this.secodSurname = seconSurname;
	}

    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }

}
