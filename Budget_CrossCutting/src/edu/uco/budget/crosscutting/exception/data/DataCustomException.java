package edu.uco.budget.crosscutting.exception.data;

import edu.uco.budget.crosscutting.exception.BudgetCustomException;
import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

public class DataCustomException extends BudgetCustomException  {
    private static final long serialVersionUID = 3L;
    

    protected DataCustomException(String userMessage, String technicalMessage,
            Throwable rootException) {
        super(userMessage,LayerException.DATA , technicalMessage, rootException);
        
    }
    public static final BudgetCustomException CreateUserException(final String userMessage) {
        return new DataCustomException(userMessage, userMessage, new Exception());
    }
    
    public static final BudgetCustomException CreateTechnicalException(final String technicalMessage) {
        return new DataCustomException(null, technicalMessage, new Exception());
        
    }
    public static final BudgetCustomException CreateTechnicalException(final String technicalMessage, final Exception rootException ) {
        return new DataCustomException(null, technicalMessage,rootException);
        
    }
    public static final BudgetCustomException Create(final String userMessage, final String technicalMessage) {
        return new DataCustomException(userMessage,technicalMessage, new Exception());
        
    }
    public static final BudgetCustomException Create(final String userMessage, final String technicalMessage, final Exception rootException ) {
        return new DataCustomException(userMessage, technicalMessage, rootException);
    }
    

}
