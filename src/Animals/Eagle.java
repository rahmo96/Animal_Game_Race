package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;

/**
 * Represents an eagle, a type of air animal, with specific attributes like altitude of flight and a maximum altitude limit.
 */
public class Eagle extends AirAnimal {
    private double altitudeOfFlight;
    static final int MAX_ALTITUDE = 1000;

    public Eagle(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location,double wingspan, double altitude, int energy, int energyPerMeter,  int maxEnergy) {
        super(name, gender, weight, speed, medals, location, wingspan, energy, maxEnergy, energyPerMeter);
        this.altitudeOfFlight = altitude;
        animalpicname = "eagle1";
        loadImages(animalpicname);
        setCurrentImage(this.getImg1());
        this.setLocation(new Point(0, 55));
    }


    /**
     * Gets the current altitude of flight of the eagle.
     * @return The current altitude of flight.
     */
    public double getAltitudeOfFlight() {
        return altitudeOfFlight;
    }
    /**
     * Returns the maximum altitude that an eagle can reach.
     *
     * @return the maximum altitude in meters
     */
    public int getMaxAltitude() {
        return MAX_ALTITUDE;
    }

    /**
     * Gets the species of the animal.
     * @return A string indicating the species ("Eagle").
     */
    @Override
    public String getSpecies() {
        return "Eagle";
    }

    /**
     * Gets the sound the eagle makes.
     * @return The sound "Clack-wack-chack" as a string.
     */
    @Override
    public String sound() {
        return "Clack-wack-chack";
    }

    /**
     * Determines if this Eagle object is equal to another object.
     * Two Eagle objects are considered equal if they have the same attributes
     * (including inherited attributes from Animal and AirAnimal) and the same
     * altitude of flight.
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Eagle) || !super.equals(obj)) { // Check for type and superclass equality
            return false;
        }
        Eagle other = (Eagle) obj;
        return Double.compare(altitudeOfFlight, other.altitudeOfFlight) == 0;
    }


    /**
     * Returns a string representation of the eagle's information.
     * Includes name, gender, weight, speed, medals, location, wingspan, and altitude of flight.
     * @return A formatted string with the eagle's details.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nAltitude of Flight: " + getAltitudeOfFlight();
    }
    public String getCategory() {
        return "Air";
    }

}
