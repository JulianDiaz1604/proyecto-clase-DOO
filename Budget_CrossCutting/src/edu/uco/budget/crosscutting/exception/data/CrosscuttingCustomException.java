package edu.uco.budget.crosscutting.exception.data;

import edu.uco.budget.crosscutting.exception.BudgetCustomException;
import edu.uco.budget.crosscutting.exception.enumeration.LayerException;

public class CrosscuttingCustomException extends BudgetCustomException {
    private static final long serialVersionUID = 2L;
    
    protected CrosscuttingCustomException(String userMessage, LayerException layer, String technicalMessage,
            Throwable rootException) {
        super(userMessage,  LayerException.CROSSCUTTING, technicalMessage, rootException);
    }
    
    public static final BudgetCustomException CreateUserException(final String userMessage) {
        return new CrosscuttingCustomException(userMessage,  LayerException.CROSSCUTTING, userMessage, new Exception());
        
    }
    
    


}
