package ru.spb.herzen.jviewer.controller;

/**
 * Registration management interface.
 * @author Evgeny Mironenko
 */
public interface RegistrationController {

    /**
     * Creates new profile.
     * @return redirect URL.
     */
    String regProfile();
}
