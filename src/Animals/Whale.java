package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;
import java.util.Objects;

/**
 * Represents a whale, a type of water animal, with the additional attribute 'foodType'.
 */
public class Whale extends WaterAnimal {
    private String foodType;

    /**
     * Creates a default Whale object with no specific details.
     */
    public Whale() {
        super();
    }

    /**
     * Creates a Whale object with the specified attributes.
     *
     * @param name       The name of the whale.
     * @param gender     The gender of the whale.
     * @param weight     The weight of the whale.
     * @param speed      The speed of the whale.
     * @param medals     An array of medals the whale has won (if applicable).
     * @param location   The current location of the whale.
     * @param diveDepth  The maximum dive depth of the whale.
     * @param foodType   The type of food the whale eats.
     */
    public Whale(String name, Enums.Gender gender, double weight, double speed, Medal[] medals,
                 Point location, double diveDepth, String foodType) {
        super(name, gender, weight, speed, medals, location, diveDepth);
        this.foodType = foodType; // No need to create a new String if you're not modifying the original
    }
    public Whale(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double diveDepth, String foodType , int size , int id , Location loc , Enums.Orientation orien , int maxEnergy , int energyPerMeter , CompetitionPanel pan , BufferedImage img1 , BufferedImage img2 , BufferedImage img3 , BufferedImage img4){
        super(name, gender, weight, speed, medals, location,diveDepth, size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        this.foodType = foodType;
    }

    public Whale(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double diveDepth, String foodType, int energy,int energyPerMeter, int maxEnergy) {
        super(name, gender, weight, speed, medals, location, diveDepth, energy, maxEnergy, energyPerMeter);
        this.foodType = foodType;
        animalpicname = "whale";
        loadImages(animalpicname);
        setCurrentImage(this.getImg1());
    this.setLocation(new Point(50 , 135));}

    /**
     * Gets the species of the animal.
     *
     * @return A string indicating the species ("Whale").
     */
    @Override
    public String getSpecies() {
        return "Whale";
    }

    /**
     * Gets the sound the whale makes.
     *
     * @return The sound "Splash" as a string.
     */
    @Override
    public String sound() {
        return "Splash";
    }

    /**
     * Determines if this Whale object is equal to another object.
     * Two Whale objects are considered equal if they have the same attributes
     * (including inherited attributes from Animal and WaterAnimal) and the same food type.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Whale) || !super.equals(obj)) {
            return false;
        }
        Whale other = (Whale) obj;
        return Objects.equals(foodType, other.foodType);
    }

    /**
     * Returns a string representation of the whale's information.
     * Includes name, gender, weight, speed, medals, location, dive depth, and food type.
     *
     * @return A formatted string with the whale's details.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nFood Type: " + foodType;
    }
    public String getCategory() {
        return "Water";
    }

}
