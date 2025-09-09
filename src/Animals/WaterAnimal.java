package Animals;

import Mobility.Point;
import Olympics.Medal;
import Util.Enums;
import Graphics.CompetitionFrame;
import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.image.BufferedImage;
import java.util.Objects;
import Graphics.CompetitionPanel;

/**
 * Represents a water-dwelling animal, extending the Animal class, with the ability to dive to a certain depth.
 */
public class WaterAnimal extends Animal {
    private static final int MAX_DIVE = -800; // Maximum dive depth allowed
    private double diveDepth;
    protected String animalpicname;

    /**
     * Creates a default WaterAnimal object with no specific details.
     */
    public WaterAnimal() {
        super();
        diveDepth = 0; // Default dive depth if not specified
    }

    /**
     * Creates a WaterAnimal object with the specified attributes.
     *
     * @param name      The name of the animal.
     * @param gender    The gender of the animal.
     * @param weight    The weight of the animal.
     * @param speed     The speed of the animal.
     * @param medals    An array of medals the animal has won.
     * @param location  The current location of the animal.
     * @param diveDepth The maximum dive depth of the animal (must be <= MAX_DIVE).
     * @throws IllegalArgumentException If the diveDepth exceeds the maximum limit.
     */
    public WaterAnimal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals,
                       Point location, double diveDepth) {
        super(name, gender, weight, speed, medals, location);
        if (diveDepth >= MAX_DIVE) { // Check if diveDepth is valid
            this.diveDepth = diveDepth;
        } else {
            JOptionPane.showMessageDialog(null, "The maximum dive depth is " + MAX_DIVE + "", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public WaterAnimal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double diveDepth , int size , int id , Location loc , Enums.Orientation orien , int maxEnergy , int energyPerMeter , CompetitionPanel pan , BufferedImage img1 , BufferedImage img2 , BufferedImage img3 , BufferedImage img4){
        super(name, gender, weight, speed, medals, location, size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        if (diveDepth <= MAX_DIVE) { // Check if diveDepth is valid
            this.diveDepth = diveDepth;
        } else {
            throw new IllegalArgumentException("Exceeded maximum dive depth!");
        }
    }

    public WaterAnimal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double diveDepth, int energy, int maxEnergy, int energyPerMeter) {
        super(name, gender, weight, speed, medals, location, energy, maxEnergy, energyPerMeter);
        if (diveDepth >= MAX_DIVE) { // Check if diveDepth is valid
            this.diveDepth = diveDepth;
        } else {
            throw new IllegalArgumentException("Exceeded maximum dive depth!");
        }
    }

    /**
     * Gets the maximum dive depth allowed for water animals.
     * @return The maximum dive depth.
     */
    public static int getMaxDive() {
        return MAX_DIVE;
    }

    /**
     * Gets the current dive depth of the animal.
     * @return The current dive depth.
     */
    public double getDiveDepth() {
        return diveDepth;
    }

    // Abstract methods from Animal (to be implemented in subclasses)

    @Override
    public String getSpecies(){ return "";};
    public String sound(){ return "";};

    /**
     * Attempts to make the animal dive to the specified depth.
     * @param diveDepth The depth to dive to (must be <= MAX_DIVE).
     * @return true if the dive is successful, false if the depth is invalid.
     */
    public boolean dive(double diveDepth) { // Renamed to dive (lowercase d) for convention
        if (diveDepth <= MAX_DIVE) {
            this.diveDepth = diveDepth;
            return true;
        } else {
            System.out.println("Cannot dive to this depth!");
            return false;
        }
    }

    /**
     * Determines if this WaterAnimal object is equal to another object.
     * Two WaterAnimal objects are considered equal if they have the same attributes
     * (including inherited attributes from Animal) and the same dive depth.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WaterAnimal) || !super.equals(obj)) { // Check for type and superclass equality
            return false;
        }
        WaterAnimal other = (WaterAnimal) obj;
        return Double.compare(diveDepth, other.diveDepth) == 0;
    }

    /**
     * Returns a hash code value for the WaterAnimal object.
     * The hash code is based on the animal's attributes (including inherited attributes
     * from Animal) and dive depth.
     *
     * @return A hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), diveDepth);
    }

    /**
     * Returns a string representation of the water animal's information.
     * Includes name, gender, weight, speed, medals, location, and dive depth.
     *
     * @return A formatted string with the water animal's details.
     */
    @Override
    public String toString() {
        return super.toString() + // Use superclass's toString for common fields
                "\nDive depth: " + getDiveDepth();
    }

    @Override
    public String getAnimaleName() {
        return "";
    }

    @Override
    public String getCategory() {
        return "";
    }

    @Override
    public double moveInDirection() {
        double flag=0;
        Point temp=getLocation();
        int distance=(int) (CompetitionFrame.MOVEPIXSELS*getSpeed());
        if(temp.getX()+distance>630){
            flag=move(new Point(630,temp.getY()));
        }
        else flag=move(new Point(temp.getX()+distance,temp.getY()));
        return flag;
    }


}
