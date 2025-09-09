package Animals;
import java.awt.Graphics;
import Mobility.Point;
import Olympics.Medal;import Util.Enums;


/**
 * Represents an alligator, a type of water animal, with the additional attribute 'AreaOfLiving'.
 */
public class Alligator extends WaterAnimal implements IReptile{
    private String AreaOfLiving;
    private TerrestrialFeatures features;

    public Alligator(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location,int noLegs, double diveDepth, String areaOfLiving, int energy,int energyPerMeter,  int maxEnergy) {
        super(name, gender, weight, speed, medals, location, diveDepth, energy, maxEnergy, energyPerMeter);
        this.AreaOfLiving = areaOfLiving;
        features = new TerrestrialFeatures(name, gender, weight, speed, medals, location, noLegs, energy, energyPerMeter,  maxEnergy);
        features.animalpicname = "alligator2";
        loadImages(features.animalpicname);
        setCurrentImage(this.getImg1());
        this.setLocation(new Point(50,50));
    }

    /**
     * Returns the sound made by an Alligator.
     *
     * @return  a string representing the sound "Roar"
     */
    public String sound(){
        String sound = "Roar";
        return sound;
    }

    /**
     * Returns the area of living of an Alligator.
     *
     * @return  a string representing the area of living
     */
    public String getAreaOfLiving(){
        return AreaOfLiving;
    }

    @Override
    public String toString() {
        return super.toString() + "\nArea of Living: " + getAreaOfLiving();
    }

    @Override
    public String getSpecies() {
        return "Alligator";
    }

    @Override
    public boolean speedUp(double new_speed) {
        if (this.getSpeed()+ new_speed > IReptile.MAX_SPEED) {
            System.out.println("The Reptile cannot exceed the speed limit.");
            return false;
        } else {
            this.setSpeed(this.getSpeed() + new_speed);
            return true;
        }
    }



    @Override
    public void loadImages(String nm) {
        super.loadImages(nm);

    }
    public String getCategory() {
        return "Reptile";
    }

}
