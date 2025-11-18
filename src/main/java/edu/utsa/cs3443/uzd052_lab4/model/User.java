package edu.utsa.cs3443.uzd052_lab4.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a user in the aid ship management system.
 * Extends Person with authentication credentials.
 * 
 * @author uzd052
 */
public class User extends Person {
    private String username;
    private String password;

    /**
     * Default constructor.
     */
    public User() {
        super();
    }

    /**
     * Constructs a User with specified parameters.
     * 
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email address
     * @param phone the phone number
     * @param username the username
     * @param password the password
     */
    public User(String firstName, String lastName, String email, String phone, 
                String username, String password) {
        super(firstName, lastName, email, phone);
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * 
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password.
     * 
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * 
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Authenticates a user with the provided credentials.
     * Loads user data from the CSV file and searches for a matching user.
     * 
     * @param username the username to authenticate
     * @param password the password to verify
     * @return the User object if authentication is successful, null otherwise
     * @throws IOException if there's an error reading the users file
     */
    public static User authenticate(String username, String password) throws IOException {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            return null;
        }

        // Resolve the users.csv file robustly and provide a clear error if missing
        Path usersPath = Paths.get("data", "users.csv").toAbsolutePath();
        if (!Files.exists(usersPath)) {
            throw new FileNotFoundException("Users file not found at: " + usersPath);
        }

        try (BufferedReader br = Files.newBufferedReader(usersPath, StandardCharsets.UTF_8)) {
            String line = br.readLine();

            // If the first line looks like a header, skip it; otherwise we'll process it
            boolean firstLineIsHeader = false;
            if (line != null) {
                String headerProbe = line.toLowerCase();
                if (headerProbe.contains("firstname") || headerProbe.contains("username")) {
                    firstLineIsHeader = true;
                }
            }

            if (!firstLineIsHeader) {
                // Process the first line as data if it wasn't a header
                if (line != null && !line.trim().isEmpty()) {
                    User u = tryParseUser(line);
                    if (u != null && u.getUsername().equals(username) && u.getPassword().equals(password)) {
                        return u;
                    }
                }
            }

            // Read remaining lines
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                User u = tryParseUser(line);
                if (u != null && u.getUsername().equals(username) && u.getPassword().equals(password)) {
                    return u;
                }
            }
        }

        return null;
    }

    // Attempts to parse a CSV line into a User; returns null on any issue
    private static User tryParseUser(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length < 6) {
                System.err.println("[User.authenticate] Skipping malformed row (expected 6+ columns): " + line);
                return null;
            }
            String firstName = parts[0].trim();
            String lastName = parts[1].trim();
            String email = parts[2].trim();
            String phone = parts[3].trim();
            String fileUsername = parts[4].trim();
            String filePassword = parts[5].trim();
            return new User(firstName, lastName, email, phone, fileUsername, filePassword);
        } catch (Exception e) {
            System.err.println("[User.authenticate] Error parsing line: " + line + " -> " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s (%s)", getFirstName(), getLastName(), username);
    }
}
