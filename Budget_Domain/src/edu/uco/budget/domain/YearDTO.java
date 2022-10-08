package edu.uco.budget.domain;

import java.util.UUID;

import static edu.uco.budget.crosscutting.helper.NumberHelper.isLessThan;
import static edu.uco.budget.crosscutting.helper.NumberHelper.ZERO;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.budget.crosscutting.helper.UUIDHelper.getNewUUID;;

public class YearDTO {
	
	    private UUID id;
	    private short yearNumber;

	    public YearDTO() {
	    	setId(getNewUUID());
	    	setYearNumber(ZERO);
	    }

	    public YearDTO(final UUID id, final short yearNumber) {
	        setId(id);
	        setYearNumber(yearNumber);
	    }
	    
	    public static final YearDTO create(final UUID id, final short yearNumber) {
	    	return new YearDTO(id, yearNumber);
	    } 

	    public UUID getId() {
	        return id;
	    }

	    public short getYearNumber() {
	        return yearNumber;
	    }

	    public void setId(final UUID id) {
	        this.id = getDefaultUUID(id);
	    }

	    public void setYearNumber(final short yearNumber) {
	        this.yearNumber = isLessThan(yearNumber, ZERO)? ZERO : yearNumber;
	    }

}
