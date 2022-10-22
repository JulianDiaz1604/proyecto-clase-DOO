package edu.uco.budget.crosscutting.helper;

import java.util.UUID;
import static edu.uco.budget.crosscutting.helper.ObjectHelper.getDefaultIfNull; //Si el metodo existe en la clase va a ignorar el importado

public final class UUIDHelper {
	
	private static final String DEFAULT_UUID_AS_STRING = "null";
	private static final UUID DEFAULT_UUID = UUID.fromString(DEFAULT_UUID_AS_STRING);

	private UUIDHelper() {
		super();
	}
	
	public static final UUID getDefaultUUID(final UUID value) {
		return getDefaultIfNull(value, getNewUUID()); 
	}
	
	public static final UUID getNewUUID() {

		UUID uuid;

		do {
			uuid = UUID.randomUUID();
		} while (isDefaultUUID(uuid));

		return UUID.randomUUID();
	}

	public static final String getUUIDAsString(final UUID value){
		return getDefaultUUID(value).toString();
	}

	public static final boolean isDefaultUUID(final UUID value){
		return DEFAULT_UUID.equals(value);
	}

}
 