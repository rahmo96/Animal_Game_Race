/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;import Animals.Animal;
import Animals.Dog;
import Olympics.Medal;
import Util.Enums;
import Mobility.Point;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ZooPanel extends JPanel {
    private List<Animal> animals;
    private JTextArea infoArea;
    private JButton addAnimalButton;
    private JButton removeAnimalButton;
    private JButton displayAnimalsButton;
    private List<String> competitionTypes;
    private List<String> competitions;



    public ZooPanel() {
        competitionTypes = new ArrayList<>();
        competitions = new ArrayList<>();


        animals = new ArrayList<>();
        setLayout(new BorderLayout());
        initializeComponents();

    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane(infoArea);

        addAnimalButton = new JButton("Add Animal");
        removeAnimalButton = new JButton("Remove Animal");
        displayAnimalsButton = new JButton("Display Animals");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addAnimalButton);
        buttonPanel.add(removeAnimalButton);
        buttonPanel.add(displayAnimalsButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addListeners(Animal animal) {
        addAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAnimal(animal);
            }
        });
        removeAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAnimal();
            }
        });
        displayAnimalsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAnimals();
            }
        });
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        repaint();
    }
    private void removeAnimal() {
        if (animals.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No animals to remove.");
            return;
        }

        String[] animalNames = animals.stream().map(Animal::getName).toArray(String[]::new);
        String selectedName = (String) JOptionPane.showInputDialog(this,
                "Select an animal to remove:", "Remove Animal",
                JOptionPane.QUESTION_MESSAGE, null, animalNames, animalNames[0]);

        if (selectedName != null) {
            animals.removeIf(animal -> animal.getName().equals(selectedName));
            infoArea.append("Removed: " + selectedName + "\n");
        }
    }

    private void displayAnimals() {
        infoArea.setText(""); // Clear previous text
        if (animals.isEmpty()) {
            infoArea.append("No animals in the zoo.\n");
        } else {
            for (Animal animal : animals) {
                infoArea.append(animal.getName() + " (" + animal.getSpecies() + ")\n");
            }
        }
    }
    public ArrayList<Animal> getAnimals() {
        return (ArrayList<Animal>) animals;
    }

    // Main method for testing
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Zoo Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ZooPanel());
            frame.pack();
            frame.setVisible(true);
        });
    }
    protected void addCompetition(String competitionType) {
        if (competitionTypes.isEmpty()) {
            competitionTypes.add(competitionType);
            infoArea.append("Added: " + competitionType + "\n");
        }

        if (!competitionTypes.contains(competitionType)) {
            competitionTypes.add(competitionType);
            infoArea.append("Already contains: " + competitionType + "\n");
        }

    }

    public void clearAnimals() {
        animals.clear();
        repaint();
    }
}
