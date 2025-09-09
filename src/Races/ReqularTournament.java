package Races;

import Animals.Animal;
import Animals.AnimalThread;
import Graphics.RaceDetails;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The regular tournament class.
 */
public class ReqularTournament extends Tournament {

    /**
     * constructor
     * @param animals Animals
     * @param distance total distance
     * @param p the race details panel
     */
    public ReqularTournament(Animal[][] animals, int distance, RaceDetails p) {
        super(animals, distance, p);
    }

    @Override
    protected void setup(Animal[][] animals) {
        System.out.println("Setting Regular");

        if (animals == null || animals.length == 0) {
            throw new IllegalArgumentException("Animals array is empty or not initialized.");
        }

        int numberOfGroups = animals.length;
        AtomicBoolean startFlag = new AtomicBoolean();
        AtomicBoolean[] finishFlags = new AtomicBoolean[numberOfGroups];
        Referee[] referees = new Referee[numberOfGroups];
        Scores scores = new Scores();
        AnimalThread[] animalThreads = new AnimalThread[numberOfGroups];

        for (int i = 0; i < numberOfGroups; i++) {
            if (animals[i] == null || animals[i].length == 0) {
                throw new IllegalArgumentException("Sub-array at index " + i + " is empty or not initialized.");
            }

            finishFlags[i] = new AtomicBoolean();
            referees[i] = new Referee(finishFlags[i], animals[i][0].getName(), scores);
            animalThreads[i] = new AnimalThread(animals[i][0], raceDistance, startFlag, finishFlags[i]);
            new Thread(referees[i]).start();
            new Thread(animalThreads[i]).start();
        }

        thread = new TournamentThread(scores, startFlag, numberOfGroups, panel);
    }
}
