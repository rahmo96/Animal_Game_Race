package Animals;

import Graphics.*;

import java.awt.*;

import Mobility.Mobile;
import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

import javax.imageio.ImageIO;
import javax.xml.stream.Location;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract class representing an animal with a name, gender, weight, speed, medals, and location.
 */
public abstract class Animal extends Mobile implements Sound, IDrawable, Cloneable, IAnimal {
    private String name;
    private Enums.Gender gender;
    private double weight;
    private double speed;
    private Medal[] medals;
    private int size;
    private int id;
    private Enums.Orientation orien;
    int energy;
    private int maxEnergy;
    private int energyPerMeter;
    private CompetitionPanel pan;
    private BufferedImage img1, img2, img3, img4;
    protected int initialX = 0;
    protected int initialY=0;
    private Point location;
    private BufferedImage currentImage;
    private double distanceCovered = 0;
    public static int animalsCreated=0;
    public static int animalsDeleted=0;
    protected AtomicBoolean canRun;


    /**
     * Creates a default Animal object with no specific details.
     */
    public Animal() {
        super();
    }

    /**
     * Creates an Animal object with the specified attributes.
     * @param name The name of the animal.
     * @param gender The gender of the animal.
     * @param weight The weight of the animal.
     * @param speed The speed of the animal.
     * @param medals An array of medals the animal has won.
     * @param location The current location of the animal.
     */
    public Animal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        this.medals = medals.clone(); // Create a copy to prevent external modification
        this.setLocation(location);
        this.canRun = new AtomicBoolean(true);

    }

    public Animal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, int size, int id, Location loc, Enums.Orientation orien, int maxEnergy, int energyPerMeter, CompetitionPanel pan, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        this.medals = medals.clone(); // Create a copy to prevent external modification
        this.setLocation(location);
        this.size = size;
        this.id = id;
        this.orien = orien;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        this.pan = pan;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.canRun = new AtomicBoolean(true);
    }

    public Animal(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, int energy, int maxEnergy, int energyPerMeter) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.speed = speed;
        this.medals = medals.clone(); // Create a copy to prevent external modification
        this.energy = energy;
        this.maxEnergy = maxEnergy;
        this.energyPerMeter = energyPerMeter;
        orien = Enums.Orientation.EAST;
        this.size = 65;
        this.canRun = new AtomicBoolean(true);

    }

    // Getters

    /**
     * Gets the name of the animal.
     * @return The name of the animal.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the gender of the animal.
     * @return The gender of the animal.
     */
    public Enums.Gender getGender() {
        return gender;
    }

    /**
     * Gets the weight of the animal.
     * @return The weight of the animal.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Gets an array of medals the animal has won.
     * @return A copy of the array of medals the animal has won.
     */
    public Medal[] getMedals() {
        return medals.clone(); // Return a copy to prevent external modification
    }

    // Abstract methods

    /**
     * Gets the species of the animal.
     * @return The species of the animal.
     */
    public abstract String getSpecies();

    public boolean setSpeed(double speed) {
        this.speed = speed;
        return this.speed == speed;
    }

    /**
     * Gets the sound the animal makes.
     * @return The sound the animal makes.
     */
    public abstract String sound();

    // Other methods

    /**
     * Makes the animal produce its sound.
     */
    public void makeSound() {
        System.out.println("This " + getName() + " said " + sound());
    }

    /**
     * Determines if this Animal object is equal to another object.
     * Two Animal objects are considered equal if they have the same name, gender, weight,
     * speed, medals, and location.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Animal animal = (Animal) obj;
        return Double.compare(animal.weight, weight) == 0 &&
                Double.compare(animal.speed, speed) == 0 &&
                Objects.equals(name, animal.name) &&
                gender == animal.gender &&
                Arrays.equals(medals, animal.medals) &&
                Objects.equals(getLocation(), animal.getLocation()); // Compare locations
    }

    /**
     * Returns a string representation of the animal.
     * @return A formatted string with the animal's details.
     */
    @Override
    public String toString() {
        return "Animal: " + this.getSpecies() + "\n" +
                "Name: " + getName() + "\n" +
                "Gender: " + gender + "\n" +
                "Weight: " + getWeight() + "\n" +
                "Speed: " + getSpeed() + "\n" +
                "Medals:\n" + Arrays.toString(medals) + "\n" +
                "Location: " + getLocation(); // Add location to the output
    }

    public int getId() {
        return id;
    }


    public Animal getAnimal() {
        return this;
    }

    @Override
    public String getAnimaleName() {
        return this.getName();
    }

    @Override
    public double move(Point p1) {
        if(energy-energyPerMeter<0){
            canRun.set(false);
            //JOptionPane.showMessageDialog(pan,"Not enough energy to run please eat!");
            System.out.println(this.energy);
            return 0;
        }
        double distance=super.move(p1);
        this.energy=this.energy-this.energyPerMeter;
        return distance;
    }

    @Override
    public void loadImages(String nm) {
        if (this instanceof TerrestrialAnimals) {
            try {
                img1 = resizeImage(ImageIO.read(new File(IDrawable.PICTURE_PATH + nm + "E.png")), size, size);
                img2 = resizeImage(ImageIO.read(new File(IDrawable.PICTURE_PATH + nm + "S.png")), size, size);
                img3 = resizeImage(ImageIO.read(new File(IDrawable.PICTURE_PATH + nm + "W.png")), size, size);
                img4 = resizeImage(ImageIO.read(new File(IDrawable.PICTURE_PATH + nm + "N.png")), size, size);
            } catch (IOException e) {
                System.out.println("Cannot load image");
            }
        } else {
            try {
                img1 = resizeImage(ImageIO.read(new File(IDrawable.PICTURE_PATH + nm + ".png")), size, size);
            } catch (IOException e) {
                System.out.println("Cannot load image for animal: " + nm);
            }
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    /**
     * draws img1 to panel
     * @param g
     */
    public void drawObject(Graphics g) {

        if (currentImage != null && this.getLocation() != null) {
            g.drawImage(currentImage, this.getX(), this.getY(), null);
        }
    }


    @Override
    public boolean eat(int energy) {
        // Example implementation, you may need to adjust according to your requirements
        if (energy > 0 && energy <= maxEnergy) {
            maxEnergy -= energy;
            return true;
        }
        return false;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getEnergy() {
        return energy;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getDistance() {
        return calcDistance(this.getLocation());
    }

    public double getEnergyConsumption() {
        return energyPerMeter;
    }

    public abstract String getCategory();


    public int getSpeed() {
        return (int) speed;
    }
    public void setPan(CompetitionPanel pan) {
        this.pan = pan;
    }

    public void resetPosition() {
        this.location = new Point(initialX, initialY);
    }

    public void setInitialPosition(Point p) {
        this.initialX = p.getX();
        this.initialY = p.getY();
    }

    public void setOrientation(Enums.Orientation orientation) {
        this.orien = orientation;
    }

    public Enums.Orientation getOrientation() {
        return this.orien;
    }
    public void setCurrentImage(BufferedImage img) {
        this.currentImage = img;
    }
    protected BufferedImage getCurrentImage() {
        return this.currentImage;
    }
    public BufferedImage getImg1() {
        return this.img1;
    }
    public BufferedImage getImg2() {
        return this.img2;
    }
    public BufferedImage getImg3() {
        return this.img3;
    }
    public BufferedImage getImg4() {
        return this.img4;
    }
    protected int getPanHeight() {
        return pan.getHeight();
    }
    protected int getPanWidth() {
        return pan.getWidth();
    }

    public int getEnergyPerMeter() {
        return energyPerMeter;
    }

    public int getSize() {
        return size;
    }

    public void myFinalize(){
        animalsDeleted++;
    }

    public int getCurrentEnergy() {
        return energy;
    }
    public int calcEnergySpent(){return energy-maxEnergy;}
}
