package ru.spb.herzen.jviewer.model.impl;

/**
 * Common model implementation for all models.
 * @author Evgeny Mironenko
 */
public final class CommonModel {

    private CommonModel(){
    }

    /**
     * Equals realisation for string objects. Is necessary to prevent NPE.
     * @param str1 first string.
     * @param str2 second string.
     * @return equality result.
     */
    public static boolean equalsString(String str1, String str2) {
        return str1 == null && str2 == null || str2.equals(str1);
    }
}
