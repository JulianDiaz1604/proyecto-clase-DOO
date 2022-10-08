package edu.uco.budget.crosscutting.helper;

import java.util.UUID;
import static edu.uco.budget.crosscutting.helper.ObjectHelper.getDefaultIfNull; //Si el metodo existe en la clase va a ignorar el importado

public final class UUIDHelper {
	
	private UUIDHelper() {
		super();
	}
	
	public static final UUID getDefaultUUID(final UUID value) {
		return getDefaultIfNull(value, getNewUUID()); 
	}
	
	public static final UUID getNewUUID() {
		return UUID.randomUUID();
	}

}
 