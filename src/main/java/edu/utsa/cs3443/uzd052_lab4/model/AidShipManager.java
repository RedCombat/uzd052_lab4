package edu.utsa.cs3443.uzd052_lab4.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages a list of aid ships and handles loading and saving from CSV
 */
public class AidShipManager {
    private ArrayList<AidShip> aidShips;
    private String dataFilePath;

    /**
     * Creates a manager with default data path
     */
    public AidShipManager() {
        this.aidShips = new ArrayList<>();
        this.dataFilePath = "data/aid_ships.csv";
    }

    /**
     * Adds an aid ship to the internal list
     *
     * @param aidShip the ship to add
     */
    public void addAidShip(AidShip aidShip) {
        aidShips.add(aidShip);
    }

    /**
     * Loads ships from the CSV file
     *
     * @throws IOException when the file cannot be read
     */
    public void loadAidShips() throws IOException {
        File file = new File(dataFilePath);

        if (!file.exists()) {
            throw new FileNotFoundException("Data file not found: " + dataFilePath);
        }

        aidShips.clear();

        Scanner scanner = new Scanner(file);

        // skip header if it exists
        if (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine().trim();
            if (!firstLine.isEmpty() && !firstLine.startsWith("name,")) {
                AidShip ship = convertLineToAidShip(firstLine);
                if (ship != null) {
                    addAidShip(ship);
                }
            }
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                AidShip ship = convertLineToAidShip(line);
                if (ship != null) {
                    addAidShip(ship);
                }
            }
        }
        scanner.close();
    }

    /**
     * Finds a ship by registration number
     *
     * @param registrationNumber registration to search
     * @return the ship if found or null
     */
    public AidShip findAidShip(String registrationNumber) {
        for (int i = 0; i < aidShips.size(); i++) {
            if (aidShips.get(i).getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                return aidShips.get(i);
            }
        }
        return null;
    }

    /**
     * Deletes a ship and saves the updated list
     *
     * @param aidShip the ship to delete
     * @return true if the ship was removed
     * @throws IOException if saving fails
     */
    public boolean deleteAidShip(AidShip aidShip) throws IOException {
        boolean removed = aidShips.remove(aidShip);
        if (removed) {
            saveDataToFile();
        }
        return removed;
    }

    /**
     * Writes the list of ships to the CSV file
     */
    private void saveDataToFile() throws IOException {
        File file = new File(dataFilePath);

        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.println("name,registrationNumber,tonnage,crewSize,port,aidType,aidCapacity,hasHelipad");

        for (AidShip ship : aidShips) {
            writer.println(convertAidShipToLine(ship));
        }
        writer.close();
    }

    /**
     * Parses a CSV line into an AidShip
     *
     * @param line a CSV line
     * @return an AidShip or null
     */
    private AidShip convertLineToAidShip(String line) {
        try {
            String[] parts = line.split(",");

            if (parts.length != 8) {
                System.err.println("Invalid line format: " + line);
                return null;
            }

            String name = parts[0].trim();
            String registrationNumber = parts[1].trim();
            double tonnage = Double.parseDouble(parts[2].trim());
            int crewSize = Integer.parseInt(parts[3].trim());
            String currentPort = parts[4].trim();
            String aidType = parts[5].trim();
            int suppliesOnBoard = Integer.parseInt(parts[6].trim());
            boolean hasHelipad = Boolean.parseBoolean(parts[7].trim());

            return new AidShip(name, registrationNumber, tonnage, crewSize,
                    currentPort, aidType, suppliesOnBoard, hasHelipad);
        }
        catch (Exception e)
        {
            System.err.println("Error parsing line: " + line + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Converts an AidShip to a CSV line
     *
     * @param ship the ship to convert
     * @return CSV text for the ship
     */
    private String convertAidShipToLine(AidShip ship) {
        return ship.getName() + "," +
                ship.getRegistrationNumber() + "," +
                ship.getTonnage() + "," +
                ship.getCrewSize() + "," +
                ship.getCurrentPort() + "," +
                ship.getAidType() + "," +
                ship.getSuppliesOnBoard() + "," +
                ship.hasHelipad();
    }


    /**
     * Returns a readable list of managed ships
     */
    @Override
    public String toString() {
        String result = "Aid Ship Manager:\n";
        result += "Total Ships: " + aidShips.size() + "\n";
        result += "----------------------------------------\n";
        for (AidShip ship : aidShips) {
            result += ship.toString() + "\n";
        }
        return result;
    }

    /**
     * Gets the list of aid ships
     *
     * @return the list used by the UI
     */
    public ArrayList<AidShip> getAidShipList() {
        return aidShips;
    }
}