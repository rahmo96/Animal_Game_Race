package Animals;

import Graphics.CompetitionPanel;
import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

import javax.xml.stream.Location;
import java.awt.image.BufferedImage;

/**
 * Represents a snake, a type of terrestrial reptile, with additional attributes like
 * poisonous status and length.
 */
public class Snake extends TerrestrialAnimals implements IReptile {
    private Enums.Poisonous poisonous;
    private double length;



    public Snake(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, Enums.Poisonous poisonous, double length, int i, int energy,int energyPerMeter,  int maxEnergy) {
        super(name, gender, weight, speed, medals, location, i, energy, maxEnergy, energyPerMeter);
        this.poisonous = poisonous;
        this.length = length;
        animalpicname = "snake2";
        loadImages(animalpicname);
        location = new Point(0, 0);
        this.setInitialPosition(location);
        this.setOrientation(Enums.Orientation.EAST);
        this.setCurrentImage(this.getImg1());



    }


    @Override
    public String sound() {
        return "ssssssssss";
    }

    /**
     * Gets the species of the animal.
     * @return A string indicating the species ("Snake").
     */
    @Override
    public String getSpecies() {
        return "Snake";
    }

    /**
     * Determines if this Snake object is equal to another object.
     * Two Snake objects are considered equal if they have the same attributes (including
     * inherited attributes) and the same poisonous status and length.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Snake) || !super.equals(obj)) {
            return false;
        }
        Snake snake = (Snake) obj;
        return poisonous == snake.poisonous &&
                Double.compare(snake.length, length) == 0;
    }
    @Override
    public boolean speedUp(double newSpeed) {
        if (newSpeed >= 0 && newSpeed <= MAX_SPEED) {
            setSpeed(newSpeed);
            return true;
        } else {
            System.out.println("The Reptile cannot exceed the speed limit!");
            return false;
        }
    }
    public String getCategory() {
        return "Terrestrial";
    }


}
