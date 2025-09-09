package System;

/**
 * The main system class for interacting with and creating animal objects.
 * Provides a command-line interface to create different types of animals, view their information,
 * and make them produce sounds.
 */
public class Sys {

    /**
     * The main entry point of the program.
     * - Prompts the user to input the number of animals to create.
     * - Uses the AnimalCreator to guide the user through the creation process.
     * - After creation, presents an interactive menu to view animal details or make them sound.
     *
     * @param args Command-line arguments (not used in this program).
     */
/*    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Competition competition = null;

        competition = new Competition(Competition.CompetitionType.Terrestrial);

        Animal[] animals = null;

        // Get number of animals and create the array (using a while loop for validation)
        int numberOfAnimals = 0;
        while (numberOfAnimals <= 0) {
            System.out.print("How many animals do you want to create? ");
            try {
                numberOfAnimals = scanner.nextInt();
                if (numberOfAnimals <= 0) {
                    System.out.println("Number of animals must be greater than 0. Please try again.");
                } else {
                    animals = new Animal[numberOfAnimals];
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }

        // Create animals using the AnimalCreator
        for (int i = 0; i < numberOfAnimals; i++) {
            System.out.println("\nCreating Animal " + (i + 1));

            animals[i] = AnimalCreator.createAnimal(scanner, competition); // Pass the competition object
            if (animals[i] != null) {
                System.out.println(animals[i].getSpecies() + " created successfully!");
                competition.addAnimal(animals[i]); // Add the animal to the competition
            }
        }

        // Create animals using the AnimalCreator
        for (int i = 0; i < numberOfAnimals; i++) {
            System.out.println("\nCreating Animal " + (i + 1));
            int choice;
            do {
                System.out.println("What kind of animal do you want to create?");
                System.out.println("1. Terrestrial");
                System.out.println("2. Water");
                System.out.println("3. Air");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                try {
                    choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline after number input
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next(); // Discard invalid input
                    choice = 0; // Reset choice to invalid to continue the loop
                }

                if (choice >= 1 && choice <= 3) { // Valid choices
                    Enums.AnimalType animalType = Enums.AnimalType.values()[choice - 1];
                    animals[i] = AnimalCreator.createAnimal(scanner, animalType);
                    if (animals[i] != null) {
                        System.out.println(animals[i].getSpecies() + " created successfully!");
                    }
                } else if (choice != 4) { // Invalid choice (not exit)
                    System.out.println("Invalid choice!");
                }
            } while (choice < 1 || choice > 4); // Repeat until valid choice or exit

            if (choice == 4) {
                break; // Exit the loop if the user chooses 4 (Exit)
            }
        }

        // Animal interaction menu
        int currentAnimalIndex = 0;
        while (currentAnimalIndex < animals.length) {
            Animal currentAnimal = animals[currentAnimalIndex];
            if (currentAnimal != null) {
                int actionChoice;
                do {
                    System.out.println("\nAnimal " + (currentAnimalIndex + 1) + " (" + currentAnimal.getSpecies() + "):");
                    System.out.println("1. Show Information");
                    System.out.println("2. Make Sound");
                    System.out.println("3. Next Animal");
                    System.out.println("4. Previous Animal");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");

                    try {
                        actionChoice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.next(); // Clear invalid input
                        actionChoice = 0; // Reset choice to invalid to continue the loop
                    }

                    switch (actionChoice) {
                        case 1:
                            System.out.println(currentAnimal.toString());
                            break;
                        case 2:
                            currentAnimal.makeSound();
                            break;
                        case 3:
                            currentAnimalIndex++;
                            break;
                        case 4:
                            if (currentAnimalIndex > 0) {
                                currentAnimalIndex--;
                            } else {
                                System.out.println("No previous animal.");
                            }
                            break;
                        case 5:
                            currentAnimalIndex = animals.length; // Exit the loop
                            break;
                        default:
                            System.out.println("Invalid choice!");
                    }
                } while (actionChoice != 3 && actionChoice != 4 && actionChoice != 5); // Repeat until valid choice or exit
            } else {
                // Skip to next animal if current animal is null
                currentAnimalIndex++;
            }
        }

        scanner.close(); // Close the scanner once you are done with it
    }*/
}
