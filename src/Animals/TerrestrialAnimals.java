package Animals;

import Mobility.Point;
import Olympics.Medal;
import Util.Enums;
import Graphics.CompetitionFrame;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javax.xml.stream.Location;
import java.awt.*;
import java.awt.image.BufferedImage;

import Graphics.CompetitionPanel;

/**
 * Represents a terrestrial animal with a specified number of legs, extending the Animal class.
 */
public abstract class TerrestrialAnimals extends Animal {
    private int noLegs;
    protected String animalpicname;



    public TerrestrialAnimals(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location, int noLegs , int size , int id , Location loc , Enums.Orientation orien , int maxEnergy , int energyPerMeter , CompetitionPanel pan , BufferedImage img1 , BufferedImage img2 , BufferedImage img3 , BufferedImage img4){
        super(name, gender, weight, speed, medals, location, size, id, loc, orien, maxEnergy, energyPerMeter, pan, img1, img2, img3, img4);
        this.noLegs = noLegs;
        this.setOrientation(Enums.Orientation.EAST);
        this.setCurrentImage(this.getImg1());
    }

    public TerrestrialAnimals(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point location,int noLegs,  int energy, int maxEnergy, int energyPerMeter) {
        super(name, gender, weight, speed, medals, location, energy, maxEnergy, energyPerMeter);
        this.noLegs = noLegs;
        this.setOrientation(Enums.Orientation.EAST);
        this.setCurrentImage(this.getImg1());
    }

    public int getNoOfLegs() {
        return noLegs;
    }

    @Override
    public String sound() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TerrestrialAnimals) || !super.equals(obj)) {
            return false;
        }
        TerrestrialAnimals other = (TerrestrialAnimals) obj;
        return noLegs == other.noLegs;
    }

    @Override
    public String toString() {
        return super.toString() + "\nNumber of Legs: " + getNoOfLegs();
    }

    @Override
    public String getSpecies(){
        return "";
    }

    @Override
    public String getAnimaleName() {
        return "";
    }

    @Override
    public String getCategory() {
        return "";
    }

    public void updatePosition() {
        switch (this.getOrientation()) {
            case EAST:
                setLocation(new Point(getLocation().getX() + this.getSpeed(), getLocation().getY()));
                if (getLocation().getX() >= getPanWidth()-100) {
                    setOrientation(Enums.Orientation.SOUTH);
                    setCurrentImage(getImg2());
                }
                break;
            case SOUTH:
                setLocation(new Point(getLocation().getX(), getLocation().getY() + getSpeed()));
                if (getLocation().getY() >= getPanHeight()-100) {
                    setOrientation(Enums.Orientation.WEST);
                    setCurrentImage(getImg3());
                }
                break;
            case WEST:
                setLocation(new Point(getLocation().getX() - getSpeed(), getLocation().getY()));
                if (getLocation().getX() <= 0) {
                    setOrientation(Enums.Orientation.NORTH);
                    setCurrentImage(getImg4());
                }
                break;
            case NORTH:
                setLocation(new Point(getLocation().getX(), getLocation().getY() - getSpeed()));
                if (getLocation().getY() <= 0) {
                    setOrientation(Enums.Orientation.EAST);
                    setCurrentImage(getImg1());
                }
                break;
        }
    }

    public double moveInDirection() {
        double flag=0;
        int movepixels= (int) (CompetitionFrame.MOVEPIXSELS*getSpeed());
        Point temp=getLocation();
        int tempi=0;
        switch (getOrientation()){
            case EAST:
                if(temp.getX()+movepixels>=705){
                    setOrientation(Enums.Orientation.SOUTH);
                    setCurrentImage(getImg2());
//                    loadImages(animalpicname);
                    tempi=temp.getX()+movepixels-705;
                    flag=move(new Point(705,5+tempi));
                }
                else flag=move(new Point((int) (temp.getX()+ CompetitionFrame.MOVEPIXSELS *getSpeed()),5));
                break;
            case NORTH:
                if(temp.getY()-movepixels<5){
                    setOrientation(Enums.Orientation.EAST);
                    setCurrentImage(getImg1());
                    loadImages(animalpicname);
                    flag=move(new Point(1,5));
                }
                else flag=move(new Point(1,temp.getY()-movepixels));
                break;
            case SOUTH:
                if(temp.getY()+movepixels>520){
                    setOrientation(Enums.Orientation.WEST);
                    setCurrentImage(getImg3());
                    //loadImages(animalpicname);
                    tempi=temp.getY()+movepixels-520;
                    flag=move(new Point(temp.getX()-tempi,520));
                }
                else flag=move(new Point(temp.getX(),temp.getY()+movepixels));
                break;
            case WEST:
                if(temp.getX()-movepixels<1){
                    setOrientation(Enums.Orientation.NORTH);
                    setCurrentImage(getImg4());
                    //loadImages(animalpicname);
                    tempi=movepixels-temp.getX();
                    flag=move(new Point(1,temp.getY()-tempi));
                }
                else flag=move(new Point(temp.getX()-movepixels,temp.getY()));
                break;
        }
        return flag;
    }


    public boolean setLocationT(Animal ani) {
        super.setLocation(ani.getLocation());
        setOrientation(ani.getOrientation());
        return true;
    }


}
