package edu.utsa.cs3443.uzd052_lab4.model;

/**
 * Base ship class that provides common navigation behavior
 */
public abstract class Ship implements Navigable {

    protected String name;
    protected String registrationNumber;
    protected double tonnage;
    protected int crewSize;
    protected double currentSpeed;
    protected String currentPort;

    /**
     * Creates a ship with core attributes
     *
     * @param name ship name
     * @param registrationNumber official registration number
     * @param tonnage displacement in tons
     * @param crewSize number of crew members
     * @param currentPort current port location
     */
    public Ship(String name, String registrationNumber, double tonnage, int crewSize, String currentPort){
        this.name=name;
        this.registrationNumber=registrationNumber;
        this.tonnage=tonnage;
        this.crewSize=crewSize;
        this.currentPort=currentPort;
        this.currentSpeed=0.0;
    }

    /**
     * Docks the ship at a port and stops movement
     *
     * @param port name of the port
     */
    public void dock(String port){
        this.currentPort = port;
        this.currentSpeed = 0.0;
    }


    /**
     * Navigates the ship to a location and sets a default cruising speed
     *
     * @param location destination name
     */
    @Override
    public void navigateTo(String location) {
        System.out.println(name + " is navigating to " + location + ".");
        setCurrentSpeed(20.0);
    }


    /**
     * Updates the current speed
     *
     * @param speed new speed value
     */
    @Override
    public void setCurrentSpeed(double speed){
        this.currentSpeed= speed;
    }


    /**
     * Returns the current speed
     *
     * @return speed in knots or units
     */
    @Override
    public double getCurrentSpeed(){
        return currentSpeed;
    }


    /**
     * Returns a readable description of the ship
     */
    @Override
    public String toString(){
        return getClass().getSimpleName() + " [name=" + name + ", registrationNumber=" + registrationNumber +
                ", tonnage=" + tonnage + ", crewSize=" + crewSize + ",currentSpeed=" +
                currentSpeed + ", currentPort=" + currentPort + "]";
    }


    /**
     * Gets the name
     *
     * @return ship name
     */
    public String getName(){
        return name;
    }
    /**
     * Sets the name
     *
     * @param name ship name
     */
    public void setName(String name) {
        this.name =name;
    }
    /**
     * Gets the registration number
     *
     * @return registration number
     */
    public String getRegistrationNumber(){
        return registrationNumber;
    }
    /**
     * Sets the registration number
     *
     * @param registrationNumber identifier assigned to the ship
     */
    public void setRegistrationNumber(String registrationNumber){
        this.registrationNumber = registrationNumber;
    }
    /**
     * Gets the tonnage
     *
     * @return displacement in tons
     */
    public double getTonnage(){
        return tonnage;
    }
    /**
     * Sets the tonnage
     *
     * @param tonnage displacement in tons
     */
    public void setTonnage(double tonnage){
        this.tonnage = tonnage;
    }
    /**
     * Gets the crew size
     *
     * @return number of crew
     */
    public int getCrewSize() {
        return crewSize;
    }
    /**
     * Sets the crew size
     *
     * @param crewSize number of crew
     */
    public void setCrewSize(int crewSize){
        this.crewSize = crewSize;
    }
    /**
     * Gets the current port
     *
     * @return port name
     */
    public String getCurrentPort(){
        return currentPort;
    }
    /**
     * Sets the current port
     *
     * @param currentPort port name
     */
    public void setCurrentPort(String currentPort){
        this.currentPort = currentPort;
    }
}
