package Animals;

import Mobility.Point;
import Olympics.Medal;
import Util.Enums;

public class TerrestrialFeatures extends TerrestrialAnimals {
    private int noLegs;

    public TerrestrialFeatures(String name, Enums.Gender gender, double weight, double speed, Medal[] medals, Point p1,int energy, int noLegs, int energyPerMeter,  int maxEnergy) {
        super(name, gender, weight, speed, medals, p1, noLegs, energy, energyPerMeter , maxEnergy);
        setNoLegs(noLegs);
    }

    public int getNoLegs() {
        return noLegs;
    }

    public void setNoLegs(int noLegs) {
        if (noLegs < 0) {
            throw new IllegalArgumentException("Number of legs cannot be negative.");
        }
        this.noLegs = noLegs;
    }

    public String walk() {
        return "Walking with " + noLegs + " legs.";
    }



}
