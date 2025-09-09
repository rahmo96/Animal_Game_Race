package Animals;


import Mobility.Point;
import Olympics.Medal;
import Util.Enums;
import Graphics.CompetitionPanel;
import java.awt.image.BufferedImage;
import java.util.Objects;
import javax.xml.stream.Location;

/**
 * Represents a dog, a type of terrestrial animal, with the additional attribute 'breed'.
 */
public class Dog extends TerrestrialAnimals {
    private String breed;


    public Dog(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, int numLegs, String breed, int energy,int energyPerMeter,  int maxEnergy) {
        super(name, gender, weight, speed, medals, location, numLegs, energy, maxEnergy, energyPerMeter);
        this.breed = breed;
        animalpicname = "dog2";
        loadImages(animalpicname);
        this.setInitialPosition(location);
        setCurrentImage(this.getImg1());


    }


    /**
     * Gets the species of the animal.
     *
     * @return A string indicating the species ("Dog").
     */
    @Override
    public String getSpecies() {
        return "Dog";
    }

    /**
     * Gets the sound the dog makes.
     *
     * @return The sound "Woof Woof" as a string.
     */
    @Override
    public String sound() {
        return "Woof Woof";
    }

    /**
     * Determines if this Dog object is equal to another object.
     * Two Dog objects are considered equal if they have the same attributes (including
     * inherited attributes from Animal and TerrestrialAnimals) and the same breed.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dog) || !super.equals(obj)) {
            return false;
        }
        Dog other = (Dog) obj;
        return Objects.equals(breed, other.breed);
    }

    /**
     * Returns a string representation of the dog's information.
     * Includes name, gender, weight, speed, medals, location, breed, and number of legs.
     *
     * @return A formatted string with the dog's details.
     */
    @Override
    public String toString() {
        return super.toString() +
                "\nBreed: " + breed +
                "\nNumber of Legs: " + getNoOfLegs();  // Access the number of legs from the parent class
    }

    public String getCategory() {
        return "Terrestrial";
    }

}
