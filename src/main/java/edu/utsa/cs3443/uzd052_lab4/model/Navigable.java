package edu.utsa.cs3443.uzd052_lab4.model;

/**
 * Interface for objects that can navigate
 */
public interface Navigable {
    /**
     * Navigates to a target location
     *
     * @param location destination name or coordinates
     */
    void navigateTo(String location);

    /**
     * Sets the current speed
     *
     * @param speed speed value
     */
    void setCurrentSpeed(double speed);

    /**
     * Gets the current speed
     *
     * @return current speed
     */
    double getCurrentSpeed();
}
