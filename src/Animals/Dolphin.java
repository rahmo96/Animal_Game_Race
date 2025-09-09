package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Represents a dolphin, a type of water animal, with the additional attribute 'WaterType'
 * to indicate whether it lives in sea water or sweet water.
 */
public class Dolphin extends WaterAnimal {
    private Enums.WaterType waterType;

    /**
     * Creates a default Dolphin object with no specific details.
     */
    public Dolphin() {}

    /**
     * Creates a Dolphin object with the specified attributes.
     *
     * @param name       The name of the dolphin.
     * @param gender     The gender of the dolphin.
     * @param weight     The weight of the dolphin.
     * @param speed      The speed of the dolphin.
     * @param medals     An array of medals the dolphin has won (if applicable).
     * @param location   The current location of the dolphin.
     * @param diveDepth  The maximum dive depth of the dolphin.
     * @param waterType  The type of water the dolphin lives in (sea or sweet).
     */


    public Dolphin(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double diveDepth, Enums.WaterType waterType, int energy, int energyPerMeter, int maxEnergy) {
        super(name, gender, weight, speed, medals, location, diveDepth, energy, maxEnergy, energyPerMeter);
        this.waterType = waterType;
        animalpicname = "dolphin3";
        loadImages("dolphin3");
        setCurrentImage(this.getImg1());
        this.setLocation(new Point(50 , 210));
    }

    /**
     * Gets the species of the animal.
     *
     * @return A string indicating the species ("Dolphin").
     */
    @Override
    public String getSpecies() {
        return "Dolphin";
    }

    /**
     * Gets the sound the dolphin makes.
     *
     * @return The sound "Click-Click" as a string.
     */
    @Override
    public String sound() {
        return "Click-Click";
    }

    /**
     * Determines if this Dolphin object is equal to another object.
     * Two Dolphin objects are considered equal if they have the same attributes
     * (including inherited attributes from Animal and WaterAnimal) and the same water type.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dolphin) || !super.equals(obj)) { // Check for type and superclass equality
            return false;
        }
        Dolphin other = (Dolphin) obj;
        return waterType == other.waterType;
    }


    /**
     * Returns a string representation of the dolphin's information.
     * Includes name, gender, weight, speed, medals, location, dive depth, and water type.
     *
     * @return A formatted string with the dolphin's details.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nWater Type: " + waterType;
    }

    public String getCategory() {
        return "Water";
    }

}
