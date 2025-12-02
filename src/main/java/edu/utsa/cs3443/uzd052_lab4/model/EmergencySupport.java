package edu.utsa.cs3443.uzd052_lab4.model;

/**
 * Interface for emergency support units
 */
public interface EmergencySupport {
    /**
     * Deploys aid for an emergency
     */
    void deployAid();

    /**
     * Provides a concise readiness report
     *
     * @return readiness details
     */
    String getEmergencyReadinessReport();
}
