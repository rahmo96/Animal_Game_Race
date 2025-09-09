package Animals;

import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

import javax.xml.stream.Location;
import java.awt.*;
import Graphics.CompetitionPanel;
import java.awt.image.BufferedImage;
import Graphics.CompetitionFrame;

/**
 * Represents an air-breathing animal with a wingspan, extending the Animal class.
 */
public abstract class AirAnimal extends Animal implements Sound {
    private double wingspan;
    protected String animalpicname;

    /**
     * Creates a default AirAnimal object with no specific details.
     */
    public AirAnimal() {
        super();
        this.wingspan = 0;
    }

    /**
     * Creates an AirAnimal object with the specified attributes.
     * @param name The name of the animal.
     * @param gender The gender of the animal.
     * @param weight The weight of the animal.
     * @param speed The speed of the animal.
     * @param medals An array of medals the animal has won.
     * @param location The current location of the animal.
     * @param wingspan The wingspan of the animal.
     */
    public AirAnimal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double wingspan) {
        super(name, gender, weight, speed, medals, location);
        this.wingspan = wingspan;
    }

    public AirAnimal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double wingspan, int size, int id, Location loc, Enums.Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4) {
        super(name, gender, weight, speed, medals, location, size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        this.wingspan = wingspan;
    }

    public AirAnimal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, double wingspan, int energy, int maxEnergy, int energyPerMeter) {
        super(name, gender, weight, speed, medals, location, energy, maxEnergy, energyPerMeter);
        this.wingspan = wingspan;
    }

    /**
     * Gets the sound the animal makes (to be implemented in concrete subclasses).
     * @return The sound the animal makes as a String.
     */
    public String sound() {
        return "";
    }

    @Override
    public String getSpecies() {
        return "";
    }

    /**
     * Gets the wingspan of the animal.
     * @return The wingspan of the animal.
     */
    public double getWingspan() {
        return wingspan;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof AirAnimal)) {
            return false;
        }

        AirAnimal other = (AirAnimal) obj;

        // Compare all fields for equality, including those inherited from Animal
        return super.equals(obj) && // Call the equals() method of the parent class (Animal)
                Double.compare(wingspan, other.wingspan) == 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\nWingspan: " + getWingspan();
    }


    @Override
    public String getAnimaleName() {
        return "Air Animal";
    }

    @Override
    public String getCategory() {
        return "";
    }

    @Override
    public double moveInDirection() {
        double flag=0;
        int distance= (int) (CompetitionFrame.MOVEPIXSELS*getSpeed());
        Point temp = getLocation();
        if(temp.getX()+distance>695){
            flag =move(new Point(695,temp.getY()));
        }
        else flag=move(new Point(temp.getX()+distance,temp.getY()));
        return flag;
    }


}
