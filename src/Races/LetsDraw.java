
package Races;

import Animals.Animal;
import Animals.TerrestrialAnimals;
import Util.Enums.*;
import Mobility.Point;
import Util.Enums;

/**
 * The drawing animals class
 */
public class LetsDraw {
    private static final int TERRESTRIALDISTANCE=2600;
    private static final int WATERDISTANCE=540;
    private static final int AIRDISTANCE=690;
    public static int CurrentRaceDistance;
    private Point[] startingPoints;
    private Animal[][] animals;
    private Enums.CompetitionType competitionType;

    /**
     * Constructor
     * @param anis Animals
     * @param cmptype Competition type
     */
    public LetsDraw(Animal[][] anis, Enums.CompetitionType cmptype){
        animals=anis;
        competitionType=cmptype;
        initPoints();
    }

    /**
     * Initialize the starting points based of the comptition type
     */
    private void initPoints(){
        switch (competitionType){
            case Terrestrial:
                startingPoints=new Point[1];
                startingPoints[0]=new Point(1,5);
                CurrentRaceDistance=TERRESTRIALDISTANCE;
                break;
            case Water:
                startingPoints=new Point[4];
                startingPoints[0]=new Point(68,83);
                startingPoints[1]=new Point(68,230);
                startingPoints[2]=new Point(68,383);
                startingPoints[3]=new Point(68,526);
                CurrentRaceDistance=WATERDISTANCE;
                break;
            case Air:
                startingPoints=new Point[5];
                for(int i=0;i<startingPoints.length;i++){
                    startingPoints[i]=new Point(1,5+i*150);
                }
                CurrentRaceDistance=AIRDISTANCE;
                break;
        }
    }

    /**
     * placing each terrestrial animal in her spot according to how many teammates
     */
    private void setAnimalsPointTerrestrial(){
        TerrestrialAnimals temp;
        int numOfTeams=animals.length;
        int numOfAnimals;
        int distancePerMember;
        for(int i=0;i<numOfTeams;i++){
            numOfAnimals=animals[i].length;
            distancePerMember=TERRESTRIALDISTANCE/numOfAnimals;
            animals[i][0].setLocation(new Point(startingPoints[0].getX()+65*i,startingPoints[0].getY()));
            for(int j=1;j<numOfAnimals;j++){
                temp= (TerrestrialAnimals) animals[i][j];
                temp.setLocationT(animals[i][j-1]);
                setDisToPoint(distancePerMember,animals[i][j]);
            }
        }
    }
    /**
     * placing each water/air animal in her spot according to how many teammates
     */
    private void setAnimalsPoints() {
        int numOfTeams = animals.length;
        int numOfAnimals, timesneeded;
        int distancePerMember;
        int div;
        switch (competitionType) {
            case Water:
                div = WATERDISTANCE;
                break;
            case Air:
                div = AIRDISTANCE;
                break;
            default:
                div = 1; // This default might also be risky if used in division
        }
        for (int i = 0; i < numOfTeams; i++) {
            numOfAnimals = animals[i].length;
            if (numOfAnimals > 0) { // Check to ensure we are not dividing by zero
                distancePerMember = div / numOfAnimals;
                animals[i][0].setLocation(startingPoints[i]);
                for (int j = 1; j < numOfAnimals; j++) {
                    animals[i][j].setLocation(new Point(animals[i][j-1].getLocation().getX() + distancePerMember, animals[i][j-1].getLocation().getY()));
                }
            }
        }
    }

    /**
     * Calling the correct function based of the competition type
     */
    public void letsDraw(){
        switch (competitionType){
            case Terrestrial:
                setAnimalsPointTerrestrial();
                break;
            default:
                setAnimalsPoints();
                break;
        }
    }

    /**
     * Calculating the terrestrial animal location (keeping in mind the current race track points)
     * @param dis the distance the animal needs to move
     * @param ani the animal
     */
    private void setDisToPoint(int dis,Animal ani) {
        while (dis > 0) {
            Point p1 = ani.getLocation();
            switch (ani.getOrientation()) {
                case EAST:
                    if (p1.getX() + dis > 705) {
                        ani.setLocation(new Point(705, 5 ));
                        dis=dis-(705-p1.getX());
                        ani.setOrientation(Orientation.SOUTH);
                    }
                    else {
                        ani.setLocation(new Point(p1.getX() + dis, p1.getY()));
                        dis=0;
                    }
                    break;
                case SOUTH:
                    if (p1.getY() + dis > 520) {
                        ani.setLocation(new Point(705, 520));
                        dis=dis-(520-p1.getY());
                        ani.setOrientation(Orientation.WEST);
                        ani.setCurrentImage(ani.getImg2());
                    }
                    else {
                        ani.setLocation(new Point(p1.getX(), p1.getY() + dis));
                        dis=0;
                    }
                    break;
                case WEST:
                    if (p1.getX() - dis < 1) {
                        ani.setLocation(new Point(1, 605));
                        dis=dis-(dis-p1.getX());
                        ani.setOrientation(Orientation.NORTH);
                        ani.setCurrentImage(ani.getImg3());
                    }
                    else {
                        ani.setLocation(new Point(p1.getX() - dis, p1.getY()));
                        dis=0;
                    }
                    break;
                case NORTH:
                    ani.setLocation(new Point(p1.getX(), p1.getY() - dis));
                    dis=0;
                    break;
            }

        }
    }
}
