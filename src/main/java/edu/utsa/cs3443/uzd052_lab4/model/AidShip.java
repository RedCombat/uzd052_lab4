package edu.utsa.cs3443.uzd052_lab4.model;

/**
 * Represents an aid ship that can deploy supplies and support emergencies
 */
public class AidShip extends Ship implements EmergencySupport {
    private String aidType;
    private int suppliesOnBoard;
    private boolean hasHelipad;

    /**
     * Creates a new aid ship with basic characteristics
     *
     * @param name ship name
     * @param registrationNumber official registration number
     * @param tonnage displacement in tons
     * @param crewSize number of crew members
     * @param currentPort current port location
     * @param aidType type of aid carried
     * @param suppliesOnBoard number of aid units onboard
     * @param hasHelipad whether the ship has a helipad
     */
    public AidShip(String name, String registrationNumber, double tonnage, int crewSize, String currentPort, String aidType,
                   int suppliesOnBoard, boolean hasHelipad) {
        super(name, registrationNumber, tonnage, crewSize, currentPort);
        this.aidType = aidType;
        this.suppliesOnBoard = suppliesOnBoard;
        this.hasHelipad = hasHelipad;
    }

    @Override
    public void dock(String port){
        super.dock(port);
        unloadSupplies();
    }

    /**
     * Unloads all supplies after docking
     */
    private void unloadSupplies() {
        this.suppliesOnBoard = 0;
    }


    /**
     * Deploys available aid and resets onboard count
     */
    @Override
    public void deployAid() {
        System.out.println("Aid Ship " + name + " is deploying " + suppliesOnBoard + " units of " + aidType + " aid.");
        unloadSupplies();
    }


    /**
     * Provides a short readiness report for emergency use
     *
     * @return a multi line report about supplies and helipad
     */
    @Override
    public String getEmergencyReadinessReport(){
        return "Aid Ship Emergency Report:\n" +
                "Name: " + name +"\n" +
                "Supplies on Board: " + suppliesOnBoard +"\n" +
                "Helipad: " + (hasHelipad ? "Available" : "Not Available") + "\n" +
                "===============================";
    }


    /**
     * Returns a readable description of the ship
     */
    @Override
    public String toString(){
        return super.toString() + ", aidType= " + aidType + ", suppliesOnBoard= " + suppliesOnBoard +
                ", hasHelipad= " + hasHelipad + "]";
    }
    /**
     * Gets the aid type carried
     *
     * @return the aid type
     */
    public String getAidType() {
        return aidType;
    }

    /**
     * Gets the number of supplies onboard
     *
     * @return supplies count
     */
    public int getSuppliesOnBoard() {
        return suppliesOnBoard;
    }

    /**
     * Indicates if the ship has a helipad
     *
     * @return true if a helipad is available
     */
    public boolean hasHelipad() {
        return hasHelipad;
    }
}
