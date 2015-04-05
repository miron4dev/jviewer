package tk.jviewer.utils;

/**
 * Common model implementation for all models.
 * @author Evgeny Mironenko
 */
public final class ObjectUtils {

    private ObjectUtils() {
    }

    /**
     * Equals implementation for string objects. Is necessary to prevent NPE.
     * @param str1 first string.
     * @param str2 second string.
     * @return equality result.
     */
    public static boolean equalsString(String str1, String str2) {
        return str1 == null && str2 == null || str2.equals(str1);
    }

    public static int hashCodeIncreasing(int oldHashCode, String someObjectField) {
        if (someObjectField != null) {
            return someObjectField.length() + oldHashCode;
        }

        return oldHashCode;
    }
}
