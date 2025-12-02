package edu.utsa.cs3443.uzd052_lab4.model;

/**
 * Represents a person with basic information
 * Base class for User
 * 
 * @author uzd052
 */
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    /**
     * Default constructor
     */
    public Person() {
    }

    /**
     * Constructs a Person with specified parameters
     * 
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email address
     * @param phone the phone number
     */
    public Person(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Gets the first name
     * 
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name
     * 
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name
     * 
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name
     * 
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email
     * 
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email
     * 
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the phone number
     * 
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number
     * 
     * @param phone the phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s, %s", firstName, lastName, email, phone);
    }
}
