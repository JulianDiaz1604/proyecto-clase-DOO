package edu.uco.budget.crosscutting.exception.data;

import edu.uco.budget.crosscutting.exception.BudgetCustomException;
import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

public class DataCustomException extends BudgetCustomException  {
    private static final long serialVersionUID = 3L;
    

    protected DataCustomException(String userMessage, LayerException layer, String technicalMessage,
            Throwable rootException) {
        super(userMessage,LayerException.DATA , technicalMessage, rootException);
        
    }
    public static final BudgetCustomException CreateUserException(final String userMessage) {
        return new CrosscuttingCustomException(userMessage,  LayerException.DATA, userMessage, new Exception());
    }
    
    public static final BudgetCustomException CreateTechnicalException(final String technicalMessage) {
        return new CrosscuttingCustomException(null, LayerException.DATA, technicalMessage, new Exception());
        
    }
    public static final BudgetCustomException CreateTechnicalException(final String technicalMessage, final Exception rootException ) {
        return new CrosscuttingCustomException(null,  LayerException.DATA, technicalMessage,rootException);
        
    }
    public static final BudgetCustomException Create(final String userMessage, final String technicalMessage) {
        return new CrosscuttingCustomException(userMessage,  LayerException.DATA, technicalMessage, new Exception());
        
    }
    public static final BudgetCustomException Create(final String userMessage, final String technicalMessage, final Exception rootException ) {
        return new CrosscuttingCustomException(userMessage,  LayerException.DATA, technicalMessage, rootException);
    }
    

}
