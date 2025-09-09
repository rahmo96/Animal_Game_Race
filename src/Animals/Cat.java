package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;

/**
 * Represents a cat, a type of terrestrial animal, with an additional attribute indicating
 * whether or not the cat has been castrated.
 */
public class Cat extends TerrestrialAnimals {
    private boolean castrated;


    public Cat(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, int numLegs, boolean castrated, int energy, int energyPerMeter,  int maxEnergy) {
        super(name, gender, weight, speed, medals, location, numLegs, energy, maxEnergy, energyPerMeter);
        this.castrated = castrated;
        animalpicname = "cat2";
        location = new Point(0, 0);
        loadImages(animalpicname);
        this.setInitialPosition(location);
        this.setCurrentImage(this.getImg1());

    }

    /**
     * Gets the species of the animal.
     *
     * @return A string indicating the species ("Cat").
     */
    @Override
    public String getSpecies() {
        return "Cat";
    }

    /**
     * Gets the sound the cat makes.
     *
     * @return The sound "Meow" as a string.
     */
    @Override
    public String sound() {
        return "Meow";
    }

    /**
     * Determines if this Cat object is equal to another object.
     * Two Cat objects are considered equal if they have the same attributes
     * (including inherited attributes from Animal and TerrestrialAnimals)
     * and the same castrated status.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cat) || !super.equals(obj)) {
            return false;
        }
        Cat other = (Cat) obj;
        return castrated == other.castrated;
    }

    public String getCategory() {
        return "Terrestrial";
    }

}
