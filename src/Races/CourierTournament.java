 /** Rahamim Tadela  208189621 */
package Races;

import Animals.Animal;
import Animals.AnimalThread;
import Graphics.RaceDetails;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * the Courier tournament class
 */
public class CourierTournament extends Tournament {
    public CourierTournament(Animal[][] animals, int distance, RaceDetails p) {
        super(animals,distance,p);
    }

    @Override
    protected void setup(Animal[][] animals) {
        System.out.println("Setting Courier");
        Scores scores=new Scores();
        AtomicBoolean mstartFlag=new AtomicBoolean();
        AtomicBoolean[] startFlags;
        int numberOfTeams=animals.length;
        int numberOfAnimals=0;
        for(int i=0;i<numberOfTeams;i++){
            numberOfAnimals=animals[i].length;
            startFlags=new AtomicBoolean[numberOfAnimals];
            startFlags[0]=new AtomicBoolean();
            new AnimalThread(animals[i][0],raceDistance/numberOfAnimals,mstartFlag,startFlags[0]).start();
            for(int j=1;j<numberOfAnimals;j++){
                startFlags[j]=new AtomicBoolean();
                new AnimalThread(animals[i][j],raceDistance/numberOfAnimals,startFlags[j-1],startFlags[j]).start();
            }
            new Thread(new Referee(startFlags[numberOfAnimals-1],"Team "+i,scores)).start();
        }
        thread = new TournamentThread(scores,mstartFlag,numberOfTeams,panel);
    }
}
