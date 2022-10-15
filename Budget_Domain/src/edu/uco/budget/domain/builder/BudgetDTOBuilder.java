package edu.uco.budget.domain.builder;

import java.util.UUID;

import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;

public class BudgetDTOBuilder {
    
    private UUID id;
    private PersonDTO person;
    private YearDTO year;

    private BudgetDTOBuilder(){
        super();
    }

    public final BudgetDTOBuilder getBudgetDTOBuilder(){
        return new BudgetDTOBuilder();
    }

    public final BudgetDTOBuilder setId(UUID id){
        this.id = id;
        return this;
    }

    public final BudgetDTOBuilder setPerson(PersonDTO person){
        this.person = person;
        return this;
    }

    public final BudgetDTOBuilder setYear(YearDTO year){
        this.year = year;
        return this;
    }

    public final BudgetDTO build(){
        return BudgetDTO.create(id, year, person);
    }

}
