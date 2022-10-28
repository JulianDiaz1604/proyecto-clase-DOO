package edu.uco.budget.crosscutting.exception;

import edu.uco.budget.crosscutting.exception.enumeration.LayerException;
import static edu.uco.budget.crosscutting.helper.ObjectHelper. *;

public class BudgetCustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String userMessage;
    private  LayerException layer;
    
            
    protected BudgetCustomException(final String userMessage, final LayerException layer, final String technicalMessage, final Throwable rootException) {
        super(technicalMessage, getDefaultIfNull(rootException, new Exception()));
        setUserMessage(userMessage);
        setLayer(layer);
    }


    public String getUserMessage() {
        return userMessage;
    }


    public void setUserMessage(final String userMessage) {
        this.userMessage = (userMessage);
    }


    public LayerException getLayer() {
        return layer;
    }


    public void setLayer(LayerException layer) {
        this.layer = getDefaultIfNull(layer, LayerException.APLICATION);
    }
    
    public final boolean isTechinalException() {
        return isNull(getUserMessage());
    }
   
    

}
