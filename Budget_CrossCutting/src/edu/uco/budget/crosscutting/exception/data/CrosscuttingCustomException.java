package edu.uco.budget.crosscutting.exception.data;

import edu.uco.budget.crosscutting.exception.BudgetCustomException;
import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

public class CrosscuttingCustomException extends BudgetCustomException {
    private static final long serialVersionUID = 2L;
    
    protected CrosscuttingCustomException(String userMessage, String technicalMessage,
            Throwable rootException) {
        super(userMessage,  LayerException.CROSSCUTTING, technicalMessage, rootException);
    }   
    
    public static final BudgetCustomException CreateUserException(final String userMessage) {
        return new CrosscuttingCustomException(userMessage, userMessage, new Exception());
        
    }
    
    public static final BudgetCustomException CreateTechnicalException(final String technicalMessage) {
        return new CrosscuttingCustomException(null, technicalMessage, new Exception());
        
    }
    public static final BudgetCustomException CreateTechnicalException(final String technicalMessage, final Exception rootException ) {
        return new CrosscuttingCustomException(null, technicalMessage,rootException);
        
    }
    public static final BudgetCustomException Create(final String userMessage, final String technicalMessage) {
        return new CrosscuttingCustomException(userMessage, technicalMessage, new Exception());
        
    }
    public static final BudgetCustomException Create(final String userMessage, final String technicalMessage, final Exception rootException ) {
        return new CrosscuttingCustomException(userMessage, technicalMessage, rootException);
    }
    
    
    
    


}
