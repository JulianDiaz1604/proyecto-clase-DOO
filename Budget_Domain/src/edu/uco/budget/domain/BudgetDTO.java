package edu.uco.budget.domain;

import java.util.UUID;

import static edu.uco.budget.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getUUIDFromString;;

public class BudgetDTO {

    private UUID id;
    private YearDTO year;
    private PersonDTO person;

    public BudgetDTO() {
        setId(getNewUUID());
    }

    public BudgetDTO(final UUID id, final YearDTO year, final PersonDTO person) {
        setId(id);
        setYear(year);
        setPerson(person);
    }

    public static final BudgetDTO create(final UUID id, final YearDTO year, final PersonDTO person){
        return new BudgetDTO(id, year, person);
    }

    public static final BudgetDTO create(final String id, final YearDTO year, final PersonDTO person){
        return new BudgetDTO(getUUIDFromString(id), year, person);
    }

    public UUID getId() {
        return id;
    }

    public YearDTO getYear() {
        return year;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public void setYear(YearDTO year) {
        this.year = year;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }
    
}