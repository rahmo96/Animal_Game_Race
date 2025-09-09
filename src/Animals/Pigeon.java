package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Represents a pigeon, a type of air animal, with the additional attribute 'family'.
 */
public class Pigeon extends AirAnimal {
    private String family;

    /**
     * Creates a default Pigeon object with no specific details.
     */
    public Pigeon(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, java.awt.Point location, double wingspan, String family, int size, int id, Location loc, Enums.Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1) {
        super();
    }

    /**
     * Creates a Pigeon object with the specified attributes.
     *
     * @param name      The name of the pigeon.
     * @param gender    The gender of the pigeon.
     * @param weight    The weight of the pigeon.
     * @param speed     The speed of the pigeon.
     * @param medals    An array of medals the pigeon has won (if applicable).
     * @param location  The current location of the pigeon.
     * @param wingspan  The wingspan of the pigeon.
     * @param family    The family to which the pigeon belongs.
     */
    public Pigeon(String name, Enums.Gender gender, double weight, double speed, Medal[] medals,
                  Point location, double wingspan, String family) {
        super(name, gender, weight, speed, medals, location, wingspan);
        this.family = family;
    }

    public Pigeon(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location , double wingspan, String family,
                  int size , int id , Location loc , Enums.Orientation orien , int maxEnergy , int energyPerMeter , CompetitionPanel pan , BufferedImage img1 , BufferedImage img2 , BufferedImage img3 , BufferedImage img4)
    {
        super(name, gender, weight, speed, medals, location, wingspan, size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        this.family = family;
    }

    public Pigeon(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double wingspan, String family, int energy, int energyPerMeter, int maxEnergy) {
        super(name, gender, weight, speed, medals, location, wingspan, energy, maxEnergy, energyPerMeter);
        this.family = family;
        animalpicname = "pigeon";
        loadImages(animalpicname);
        setCurrentImage(this.getImg1());
        this.setLocation(new Point(0, 0));
    }

    /**
     * Gets the sound the pigeon makes.
     *
     * @return The sound "Clack-wack-chack" as a string.
     */
    @Override
    public String sound() {
        return "Clack-wack-chack";
    }

    /**
     * Gets the family to which the pigeon belongs.
     *
     * @return The family name as a string.
     */
    public String getFamily() {
        return family;
    }

    /**
     * Gets the species of the animal.
     *
     * @return A string indicating the species ("Pigeon").
     */
    @Override
    public String getSpecies() {
        return "Pigeon";
    }

    /**
     * Determines if this Pigeon object is equal to another object.
     * Two Pigeon objects are considered equal if they have the same attributes
     * (including inherited attributes from Animal and AirAnimal) and the same family.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pigeon) || !super.equals(obj)) {
            return false;
        }
        Pigeon other = (Pigeon) obj;
        return Objects.equals(family, other.family); // Compare family attributes
    }
    /**
     * Returns a string representation of the pigeon's information.
     * Includes inherited attributes from Animal and AirAnimal, and the pigeon's family.
     *
     * @return A formatted string with the pigeon's details.
     */
    @Override
    public String toString() {
        return super.toString() + "\nFamily: " + getFamily();
    }

    public String getCategory() {
        return "Air";
    }

}
