package edu.uco.budget.crosscutting.helper;
import edu.uco.budget.crosscutting.helper.ObjectHelper;

public class StringHelper {

    public static final String EMPTY = "";

    private StringHelper() {
        super();
    }

    public static final String getDefaultString(String value, String defaultValue) {
        return ObjectHelper.getDefaultIfNull(value, defaultValue);
    }

    public static final String getDefaultString(String value) {
        return getDefaultString(value, EMPTY);
    }

    public static final String applyTrim(String value) {
        return getDefaultString(value).trim();
    }

}


