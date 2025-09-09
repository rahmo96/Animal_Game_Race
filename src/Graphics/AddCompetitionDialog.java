/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import Animals.Animal;
import Util.Enums;

public class AddCompetitionDialog extends JDialog {
    private JTextField competitionNameField;
    private JComboBox<Enums.CompetitionType> competitionTypeComboBox;
    private JRadioButton relayButton;
    private JRadioButton regularButton;
    private ButtonGroup competitionTypeGroup;
    private JPanel animalGroupsPanel;
    private JButton addGroupButton;
    private JButton addCompetitionButton;
    private CompetitionPanel competitionPanel;
    private List<Animal> createdAnimals = new ArrayList<>();
    private Competition newCompetition;

    public AddCompetitionDialog(CompetitionPanel competitionPanel) {
        this.competitionPanel = competitionPanel;
        setLayout(new BorderLayout());

        // Panel for competition name and type
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));

        // Competition name label and field
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(new JLabel("Competition name:"));
        competitionNameField = new JTextField(20);
        namePanel.add(competitionNameField);

        northPanel.add(namePanel);

        // Competition type selection
        relayButton = new JRadioButton("Relay");
        regularButton = new JRadioButton("Regular");
        competitionTypeGroup = new ButtonGroup();
        competitionTypeGroup.add(relayButton);
        competitionTypeGroup.add(regularButton);

        JPanel competitionTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        competitionTypePanel.add(new JLabel("Competition Type:"));
        competitionTypePanel.add(relayButton);
        competitionTypePanel.add(regularButton);

        // Add competition type JComboBox
        competitionTypeComboBox = new JComboBox<>(Enums.CompetitionType.values());
        competitionTypePanel.add(competitionTypeComboBox);

        northPanel.add(competitionTypePanel);

        add(northPanel, BorderLayout.NORTH);

        // Animal groups panel
        animalGroupsPanel = new JPanel();
        animalGroupsPanel.setLayout(new GridLayout(0, 1));

        JScrollPane scrollPane = new JScrollPane(animalGroupsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Add competition button
        addCompetitionButton = new JButton("Add Competition");
        addCompetitionButton.addActionListener(e -> addCompetition());

        // Add group button
        addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(e -> addNewGroup());
        addGroupButton.setEnabled(false); // Initially disabled

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addCompetitionButton);
        buttonPanel.add(addGroupButton);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void addCompetition() {
        String competitionName = competitionNameField.getText();
        Enums.CompetitionType competitionType = (Enums.CompetitionType) competitionTypeComboBox.getSelectedItem();

        if (competitionName.isEmpty() || competitionType == null) {
            JOptionPane.showMessageDialog(this, "Please enter a competition name and select a competition type.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        newCompetition = new Competition(competitionType, competitionName);
        competitionPanel.addCompetition(newCompetition);

        // Enable the "Add Group" button and disable the "Add Competition" button
        addGroupButton.setEnabled(true);
        addCompetitionButton.setEnabled(false);
    }

    private void addNewGroup() {
        JPanel groupPanel = new JPanel();
        groupPanel.setLayout(new GridLayout(0, 1));

        JButton addAnimalButton = new JButton("Add Animal");
        addAnimalButton.addActionListener(e -> openAddAnimalDialog(groupPanel));

        groupPanel.add(addAnimalButton);
        animalGroupsPanel.add(groupPanel);
        animalGroupsPanel.revalidate();
        animalGroupsPanel.repaint();
    }

    private void openAddAnimalDialog(JPanel groupPanel) {
        Enums.CompetitionType competitionType = (Enums.CompetitionType) competitionTypeComboBox.getSelectedItem();
        AddAnimalDialog addAnimalDialog = new AddAnimalDialog(competitionType);
        addAnimalDialog.setVisible(true);
        List<Animal> createdAnimals = addAnimalDialog.getCreatedAnimals();
        if (!createdAnimals.isEmpty()) {
            for (Animal newAnimal : createdAnimals) {
                JLabel animalLabel = new JLabel(newAnimal.getName() + " (" + newAnimal.getSpecies() + ")");
                groupPanel.add(animalLabel);
                competitionPanel.addAnimal(newAnimal); // Add animal to the competition panel
            }
            groupPanel.revalidate();
            groupPanel.repaint();
        }
    }

    public boolean isConfirmed() {
        return relayButton.isSelected() || regularButton.isSelected();
    }

    public String getCompetitionName() {
        return competitionNameField.getText();
    }

    public Enums.CompetitionType getCompetitionType() {
        return (Enums.CompetitionType) competitionTypeComboBox.getSelectedItem();
    }

    public void setCompetition(List<Competition> competitions) {
        for (Competition competition : competitions) {
            if (competition.getName().equals(getCompetitionName())) {
                return;
            }
        }
    }

    public List<Animal> getCreatedAnimals() {
        return createdAnimals;
    }
}
