package edu.uco.budget.crosscutting.exception.data;

import edu.uco.budget.crosscutting.exception.BudgetCustomException;
import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

public class UseCaseCustomException extends BudgetCustomException{

    protected UseCaseCustomException(String userMessage, LayerException layer, String technicalMessage,
            Throwable rootException) {
        super(userMessage, layer, technicalMessage, rootException);
        //TODO Auto-generated constructor stub
    }
    
}
