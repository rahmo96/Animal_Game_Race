/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import Animals.Animal;
import Mobility.Point;
import Util.Enums.CompetitionType;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a competition between animals.
 */
public class Competition implements Runnable {
    private static final int LAP_DISTANCE = 4800;
    private String name;
    private CompetitionType competitionType;
    private String raceType; // New attribute for race type
    private List<Animal> animals;
    private boolean isActive = false;
    private boolean isSelected = false; // Attribute to track if the competition is selected
    private Point finishLine;

    @Override
    public void run() {
        // Simulate the competition running over time
        List<Thread> animalThreads = new ArrayList<>();
        for (Animal animal : animals) {
            Thread animalThread = new Thread(String.valueOf(animal));
            animalThreads.add(animalThread);
            animalThread.start();
        }

        // Wait for all animals to finish
        for (Thread animalThread : animalThreads) {
            try {
                animalThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Competition was interrupted.");
                break;
            }
        }
        System.out.println("Competition has ended.");
    }

    public Competition(CompetitionType competitionType, String name) {
        this.name = name;
        this.competitionType = competitionType;
        this.raceType = raceType;
        this.animals = new ArrayList<>();
        setFinishLineBasedOnType();
    }

    /**
     * Gets the type of the competition.
     *
     * @return The CompetitionType of the competition.
     */
    public CompetitionType getCompetitionType() {
        return competitionType;
    }

    /**
     * Adds an animal to the competition, ensuring the animal type matches the competition type.
     *
     * @param animal The Animal object to be added to the competition.
     * @throws IllegalArgumentException If the animal's type doesn't match the competition type
     *                                  or if the competition is already active.
     */
    public synchronized void addAnimal(Animal animal) throws IllegalArgumentException {
        if (isActive) {
            throw new IllegalArgumentException("Cannot add animals while the competition is running.");
        }
        if (animal.getCategory().equalsIgnoreCase(this.competitionType.name())) {
            animals.add(animal);
        } else {
            throw new IllegalArgumentException("Animal type does not match competition type.");
        }
    }

    /**
     * Removes an animal from the competition.
     *
     * @param animal The Animal object to be removed from the competition.
     * @return true if the animal was successfully removed, false otherwise.
     * @throws IllegalArgumentException If the competition is already active.
     */
    public synchronized boolean removeAnimal(Animal animal) {
        if (isActive) {
            throw new IllegalArgumentException("Cannot remove animals while the competition is running.");
        }
        return animals.remove(animal);
    }

    private void setFinishLineBasedOnType() {
        switch (competitionType) {
            case Terrestrial:
                finishLine = new Point(144, 0);
                break;
            case Water:
                finishLine = new Point(750, 250);
                break;
            case Air:
                finishLine = new Point(750, 500);
                break;
            default:
                finishLine = new Point(144, 0);
        }
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    /**
     * Gets a copy of the list of animals participating in the competition.
     *
     * @return A new ArrayList containing copies of the Animal objects in the competition.
     */
    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }

    /**
     * Checks if the competition is currently active.
     *
     * @return true if the competition is active, false otherwise.
     */
    public boolean isActiveCompetition() {
        return isActive;
    }

    /**
     * Starts the competition.
     *
     * @throws IllegalStateException If the competition is already running.
     */
    public synchronized void startCompetition() {
        if (isActive) {
            throw new IllegalStateException("Competition is already running.");
        }
        isActive = true;
        new Thread(this).start();
    }

    /**
     * Stops the competition.
     *
     * @throws IllegalStateException If the competition is not running.
     */
    public synchronized void stopCompetition() {
        if (!isActive) {
            throw new IllegalStateException("Competition is not running.");
        }
        isActive = false;
        // ... (add your logic for stopping the competition, e.g., stopping a timer)
    }

    /**
     * Checks if any animal has reached the finish line.
     *
     * @return The Animal object that has won, or null if no one has won yet.
     */
    public Animal checkWinner() {
        for (Animal animal : animals) {
            if (animal.getLocation().getX() >= finishLine.getX()) {
                return animal;
            }
        }
        return null;
    }

    private Point getInitialAnimalPosition(Animal animal) {
        int animalIndex = animals.indexOf(animal); // Get the index of the animal in the list

        switch (competitionType) {
            case Terrestrial:
                return new Point(0, 0); // All terrestrial animals start at (0, 0)
            case Water:
                return new Point(0, 100 + animalIndex * 100); // Water animals start in different lanes
            case Air:
                return new Point(0, 400 + animalIndex * 100); // Air animals start in different lanes
            default:
                return new Point(0, 0); // Default position if competition type is unknown
        }
    }

    public String getName() {
        return name;
    }

    public String getRaceType() {
        return raceType;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public static int getLapDistance() {
        return LAP_DISTANCE;
    }

    public Point getFinishLine() {
        return finishLine;
    }

    public String getCompetitionName() {
        return name;
    }

    public void setCompetitionName(String competitionName) {
        this.name = competitionName;
    }

    public void setCompetitionType(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }
}
