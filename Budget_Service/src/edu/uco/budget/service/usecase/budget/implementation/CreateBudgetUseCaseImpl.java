package edu.uco.budget.service.usecase.budget.implementation;

import java.time.Year;
import java.util.List;
import java.util.UUID;

import edu.uco.budget.crosscutting.exception.data.UseCaseCustomException;
import edu.uco.budget.crosscutting.helper.UUIDHelper;
import edu.uco.budget.data.daofactory.DAOFactory;
import edu.uco.budget.data.enumeration.DAOFactoryType;
import edu.uco.budget.domain.BudgetDTO;
import edu.uco.budget.domain.PersonDTO;
import edu.uco.budget.domain.YearDTO;
import edu.uco.budget.service.usecase.budget.CreateBudgetUseCase;
import edu.uco.budget.service.usecase.person.FindPersonById;
import edu.uco.budget.service.usecase.person.implementation.FindPersonByIdImpl;
import edu.uco.budget.service.usecase.year.FindNextYearUseCase;
import edu.uco.budget.service.usecase.year.implementation.FindNextYearUseCaseImpl;

public final class CreateBudgetUseCaseImpl implements CreateBudgetUseCase{

    private final DAOFactory factory;
    private final FindNextYearUseCase findNextYearUseCase;
    private final FindPersonById findPersonById;

    public CreateBudgetUseCaseImpl(DAOFactory factory){
        this.factory = factory;
        findNextYearUseCase = new FindNextYearUseCaseImpl(factory);
        findPersonById = new FindPersonByIdImpl(factory);
    }

    @Override
    public final void execute(BudgetDTO budget) {

        // 1. Que el año de presupuesto exista.
        final YearDTO year = findNextYearUseCase.execute();
        PersonDTO person = findPerson(budget.getPerson().getId());
        // 3. Que no existe 
        budget.setYear(year);
        budget.setPerson(person);
        validateIfBudgetExist(budget);

        budget.setId(UUIDHelper.getNewUUID());

        // 4. Crear el presupuesto 

        factory.getBudgetDAO().create(budget);
    }

    private final PersonDTO findPerson(final UUID id){

        // 2. Que la ersona exista
        final PersonDTO person = findPersonById.execute(id);

        if (person.notExist()){
            //throw UseCaseCustomException.createUserException(Messages.CreateBudgetUseCaseImpl.BUSSINES_PERSON_DOES_NOTEXIST);
        }

        return person;

    }

    private final void validateIfBudgetExist(BudgetDTO budget){
        //TODO
    }
    
}
