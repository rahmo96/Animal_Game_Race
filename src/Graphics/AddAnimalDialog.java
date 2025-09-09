/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import Animals.*;
import Olympics.Medal;
import Mobility.Point;
import Util.Enums;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class AddAnimalDialog extends JPanel implements ActionListener, DocumentListener {
    // Common Fields
    private JLabel typeLabel, animalLabel, nameLabel, genderLabel, weiLabel, speLabel, energyLabel, EPMLabel, medalsLabel, MaxEnergyLabel;
    private JComboBox<Enums.AnimalType> typeComboBox;
    private JComboBox<String> animalComboBox;
    private JComboBox<Enums.Gender> genderComboBox;
    private JTextField nameTextField, weiTextField, speTextField, energyTextField, EPMTextField, MaxEnergyTextField;
    private JButton createBtn;
    private Vector<Animal> animals;
    private static final int MAXANIMALS = 5;

    // Terrestrial Fields
    private JLabel numLegsLabel, breedLabel, castratedLabel, poisonousLabel, lengthLabel;
    private JTextField numLegsField, breedField, lengthField;
    private JCheckBox castratedCheckBox;
    private JComboBox<Enums.Poisonous> poisonousComboBox;

    // Water Fields
    private JLabel diveDepthLabel, areaOfLivingLabel, foodTypeLabel, waterTypeLabel;
    private JTextField diveDepthField, areaOfLivingField, foodTypeField;
    private JComboBox<Enums.WaterType> waterTypeComboBox;

    // Air Fields
    private JLabel wingspanLabel, altitudeLabel, familyLabel;
    private JTextField wingspanField, altitudeField, familyField;

    // Instance Variable for numOfMedals
    private int numOfMedals;
    private JSpinner medalsSpinner;

    // Constructor
    public AddAnimalDialog(Enums.CompetitionType compType) {
        this.animals = new Vector<>();
        setLayout(new GridLayout(0, 2));
        initializeCommonFields();
        initializeTerrestrialFields();
        initializeWaterFields();
        initializeAirFields();

        addCommonFieldsToPanel();
        addActionListeners();

        updateAnimalComboBox(); // Update the animal combo box based on the selected competition type
        String selectedAnimal = (String) animalComboBox.getSelectedItem();
        updateFieldsForSelectedAnimal(selectedAnimal);
    }

    private void initializeCommonFields() {
        typeLabel = new JLabel("Type");
        typeComboBox = new JComboBox<>(Enums.AnimalType.values());

        animalLabel = new JLabel("Animal");
        animalComboBox = new JComboBox<>();
        animalComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAnimal = (String) animalComboBox.getSelectedItem();
                updateFieldsForSelectedAnimal(selectedAnimal);
            }
        });
        nameLabel = new JLabel("Name");
        nameTextField = new JTextField();
        genderLabel = new JLabel("Gender");
        genderComboBox = new JComboBox<>(Enums.Gender.values());
        weiLabel = new JLabel("Weight");
        weiTextField = new JTextField();
        speLabel = new JLabel("Speed");
        speTextField = new JTextField();
        energyLabel = new JLabel("Energy");
        energyTextField = new JTextField();
        EPMLabel = new JLabel("Energy per meter");
        EPMTextField = new JTextField();
        MaxEnergyLabel = new JLabel("Max Energy");
        MaxEnergyTextField = new JTextField();
        medalsLabel = new JLabel("Number of Medals");
        medalsSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1)); // Spinner for medals
        createBtn = new JButton("Create");
        createBtn.setEnabled(false);
    }

    private void initializeTerrestrialFields() {
        numLegsLabel = new JLabel("Number of Legs");
        numLegsField = new JTextField();
        breedLabel = new JLabel("Breed");
        breedField = new JTextField();
        castratedLabel = new JLabel("Castrated");
        castratedCheckBox = new JCheckBox();
        poisonousLabel = new JLabel("Poisonous");
        poisonousComboBox = new JComboBox<>(Enums.Poisonous.values());
        lengthLabel = new JLabel("Length");
        lengthField = new JTextField();
    }

    private void initializeWaterFields() {
        diveDepthLabel = new JLabel("Dive Depth");
        diveDepthField = new JTextField();
        areaOfLivingLabel = new JLabel("Area of Living");
        areaOfLivingField = new JTextField();
        foodTypeLabel = new JLabel("Food Type");
        foodTypeField = new JTextField();
        waterTypeLabel = new JLabel("Water Type");
        waterTypeComboBox = new JComboBox<>(Enums.WaterType.values());
    }

    private void initializeAirFields() {
        wingspanLabel = new JLabel("Wingspan (meters)");
        wingspanField = new JTextField();
        altitudeLabel = new JLabel("Altitude (meters)");
        altitudeField = new JTextField();
        familyLabel = new JLabel("Family");
        familyField = new JTextField();
    }

    private void addCommonFieldsToPanel() {
        add(typeLabel);
        add(typeComboBox);
        add(animalLabel);
        add(animalComboBox);
        add(nameLabel);
        add(nameTextField);
        add(genderLabel);
        add(genderComboBox);
        add(weiLabel);
        add(weiTextField);
        add(speLabel);
        add(speTextField);
        add(energyLabel);
        add(energyTextField);
        add(EPMLabel);
        add(EPMTextField);
        add(MaxEnergyLabel);
        add(MaxEnergyTextField);
        add(medalsLabel);
        add(medalsSpinner);
        add(createBtn);
    }

    private void addActionListeners() {
        typeComboBox.addActionListener(this);
        animalComboBox.addActionListener(this);
        createBtn.addActionListener(this);
        nameTextField.getDocument().addDocumentListener(this);
        weiTextField.getDocument().addDocumentListener(this);
        speTextField.getDocument().addDocumentListener(this);
        energyTextField.getDocument().addDocumentListener(this);
        EPMTextField.getDocument().addDocumentListener(this);
        MaxEnergyTextField.getDocument().addDocumentListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createBtn) {
            createAnimal();
        } else if (e.getSource() == typeComboBox) {
            updateAnimalComboBox();
        } else if (e.getSource() == animalComboBox) {
            String selectedAnimal = (String) animalComboBox.getSelectedItem();
            updateFieldsForSelectedAnimal(selectedAnimal);
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        checkFields();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkFields();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        checkFields();
    }

    private void checkFields() {
        boolean allFieldsFilled = !nameTextField.getText().isEmpty() && !weiTextField.getText().isEmpty() && !speTextField.getText().isEmpty() &&
                !energyTextField.getText().isEmpty() && !EPMTextField.getText().isEmpty() && !MaxEnergyTextField.getText().isEmpty();
        createBtn.setEnabled(allFieldsFilled);
    }

    private void createAnimal() {
        try {
            String name = nameTextField.getText();
            Enums.Gender gender = (Enums.Gender) genderComboBox.getSelectedItem();
            double weight = Double.parseDouble(weiTextField.getText());
            double speed = Double.parseDouble(speTextField.getText());
            int energy = Integer.parseInt(energyTextField.getText());
            int energyPerMeter = Integer.parseInt(EPMTextField.getText());
            int maxEnergy = Integer.parseInt(MaxEnergyTextField.getText());
            numOfMedals = (int) medalsSpinner.getValue(); // Get value from spinner
            Medal[] medals = new Medal[numOfMedals]; // Initialize medals array

            String animalType = (String) animalComboBox.getSelectedItem();
            Animal animal = null; // Declare the animal variable outside the switch statement

            switch (animalType) {
                case "Dog":
                    int numLegs = Integer.parseInt(numLegsField.getText());
                    String breed = breedField.getText();
                    animal = new Dog(name, gender, weight, speed, medals, new Point(0, 0), numLegs, breed, energy, energyPerMeter, maxEnergy);
                    break;
                case "Cat":
                    numLegs = Integer.parseInt(numLegsField.getText());
                    boolean castrated = castratedCheckBox.isSelected();
                    animal = new Cat(name, gender, weight, speed, medals, new Point(0, 0), numLegs, castrated, energy, energyPerMeter, maxEnergy);
                    break;
                case "Snake":
                    Enums.Poisonous poisonous = (Enums.Poisonous) poisonousComboBox.getSelectedItem();
                    double length = Double.parseDouble(lengthField.getText());
                    animal = new Snake(name, gender, weight, speed, medals, new Point(0, 0), poisonous, length, 0, energy, energyPerMeter, maxEnergy);
                    break;
                case "Alligator":
                    numLegs = Integer.parseInt(numLegsField.getText());
                    double diveDepth = Double.parseDouble(diveDepthField.getText());
                    String areaOfLiving = areaOfLivingField.getText();
                    String foodType = foodTypeField.getText();
                    Enums.WaterType waterType = (Enums.WaterType) waterTypeComboBox.getSelectedItem();
                    animal = new Alligator(name, gender, weight, speed, medals, new Point(0, 0), numLegs, diveDepth, areaOfLiving, energy, energyPerMeter, maxEnergy);
                    break;
                case "Dolphin":
                    diveDepth = Double.parseDouble(diveDepthField.getText());
                    areaOfLiving = areaOfLivingField.getText();
                    foodType = foodTypeField.getText();
                    waterType = (Enums.WaterType) waterTypeComboBox.getSelectedItem();
                    animal = new Dolphin(name, gender, weight, speed, medals, new Point(0, 0), diveDepth, waterType, energy, energyPerMeter, maxEnergy);
                    break;
                case "Whale":
                    diveDepth = Double.parseDouble(diveDepthField.getText());
                    areaOfLiving = areaOfLivingField.getText();
                    foodType = foodTypeField.getText();
                    waterType = (Enums.WaterType) waterTypeComboBox.getSelectedItem();
                    animal = new Whale(name, gender, weight, speed, medals, new Point(0, 0), diveDepth, foodType, energy, energyPerMeter, maxEnergy);
                    break;
                case "Eagle":
                    double wingspan = Double.parseDouble(wingspanField.getText());
                    double altitude = Double.parseDouble(altitudeField.getText());
                    animal = new Eagle(name, gender, weight, speed, medals, new Point(0, 0), wingspan, altitude, energy, energyPerMeter, maxEnergy);
                    break;
                case "Pigeon":
                    wingspan = Double.parseDouble(wingspanField.getText());
                    String family = familyField.getText();
                    animal = new Pigeon(name, gender, weight, speed, medals, new Point(0, 0), wingspan, family, energy, energyPerMeter, maxEnergy);
                    break;
            }

            animals.add(animal);
            JOptionPane.showMessageDialog(this, animal.getName() + " has been added.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values.");
        }
    }

    private void updateAnimalComboBox() {
        Enums.AnimalType selectedType = (Enums.AnimalType) typeComboBox.getSelectedItem();
        animalComboBox.removeAllItems();

        switch (selectedType) {
            case Terrestrial:
                for (Enums.TerrestrialAnimalType type : Enums.TerrestrialAnimalType.values()) {
                    animalComboBox.addItem(type.name());
                }
                break;
            case Water:
                for (Enums.WaterAnimalType type : Enums.WaterAnimalType.values()) {
                    animalComboBox.addItem(type.name());
                }
                break;
            case Air:
                for (Enums.AirAnimalType type : Enums.AirAnimalType.values()) {
                    animalComboBox.addItem(type.name());
                }
                break;
        }
        String selectedAnimal = (String) animalComboBox.getSelectedItem();
        if (selectedAnimal != null) {
            updateFieldsForSelectedAnimal(selectedAnimal);
        }
    }

    private void updateFieldsForSelectedAnimal(String selectedAnimal) {
        if (selectedAnimal == null) {
            return; // Exit the method if selectedAnimal is null
        }

        // Hide all specific fields initially
        hideAllSpecificFields();

        // Show common fields
        typeLabel.setVisible(true);
        typeComboBox.setVisible(true);
        animalLabel.setVisible(true);
        animalComboBox.setVisible(true);
        nameLabel.setVisible(true);
        nameTextField.setVisible(true);
        genderLabel.setVisible(true);
        genderComboBox.setVisible(true);
        weiLabel.setVisible(true);
        weiTextField.setVisible(true);
        speLabel.setVisible(true);
        speTextField.setVisible(true);
        energyLabel.setVisible(true);
        energyTextField.setVisible(true);
        EPMLabel.setVisible(true);
        EPMTextField.setVisible(true);
        MaxEnergyLabel.setVisible(true);
        MaxEnergyTextField.setVisible(true);
        medalsLabel.setVisible(true);
        medalsSpinner.setVisible(true);
        createBtn.setVisible(true);

        // Show specific fields based on the selected animal
        switch (selectedAnimal) {
            case "Dog":
                showDogFields();
                break;
            case "Cat":
                showCatFields();
                break;
            case "Snake":
                showSnakeFields();
                break;
            case "Alligator":
                showAlligatorFields();
                break;
            case "Dolphin":
                showDolphinFields();
                break;
            case "Whale":
                showWhaleFields();
                break;
            case "Eagle":
                showEagleFields();
                break;
            case "Pigeon":
                showPigeonFields();
                break;
        }

        refreshUI(); // Ensure the UI updates
    }

    private void showCommonFields() {
        // Show common fields
        typeLabel.setVisible(true);
        typeComboBox.setVisible(true);
        animalLabel.setVisible(true);
        animalComboBox.setVisible(true);
        nameLabel.setVisible(true);
        nameTextField.setVisible(true);
        genderLabel.setVisible(true);
        genderComboBox.setVisible(true);
        weiLabel.setVisible(true);
        weiTextField.setVisible(true);
        speLabel.setVisible(true);
        speTextField.setVisible(true);
        energyLabel.setVisible(true);
        energyTextField.setVisible(true);
        EPMLabel.setVisible(true);
        EPMTextField.setVisible(true);
        MaxEnergyLabel.setVisible(true);
        MaxEnergyTextField.setVisible(true);
        medalsLabel.setVisible(true);
        medalsSpinner.setVisible(true);
        createBtn.setVisible(true);
    }

    private void hideAllFields() {
        // Hide all fields
        typeLabel.setVisible(false);
        typeComboBox.setVisible(false);
        animalLabel.setVisible(false);
        animalComboBox.setVisible(false);
        nameLabel.setVisible(false);
        nameTextField.setVisible(false);
        genderLabel.setVisible(false);
        genderComboBox.setVisible(false);
        weiLabel.setVisible(false);
        weiTextField.setVisible(false);
        speLabel.setVisible(false);
        speTextField.setVisible(false);
        energyLabel.setVisible(false);
        energyTextField.setVisible(false);
        EPMLabel.setVisible(false);
        EPMTextField.setVisible(false);
        MaxEnergyLabel.setVisible(false);
        MaxEnergyTextField.setVisible(false);
        medalsLabel.setVisible(false);

    }


    private void hideAllSpecificFields() {
        numLegsLabel.setVisible(false);
        numLegsField.setVisible(false);
        breedLabel.setVisible(false);
        breedField.setVisible(false);
        castratedLabel.setVisible(false);
        castratedCheckBox.setVisible(false);
        poisonousLabel.setVisible(false);
        poisonousComboBox.setVisible(false);
        lengthLabel.setVisible(false);
        lengthField.setVisible(false);
        diveDepthLabel.setVisible(false);
        diveDepthField.setVisible(false);
        areaOfLivingLabel.setVisible(false);
        areaOfLivingField.setVisible(false);
        foodTypeLabel.setVisible(false);
        foodTypeField.setVisible(false);
        waterTypeLabel.setVisible(false);
        waterTypeComboBox.setVisible(false);
        wingspanLabel.setVisible(false);
        wingspanField.setVisible(false);
        altitudeLabel.setVisible(false);
        altitudeField.setVisible(false);
        familyLabel.setVisible(false);
        familyField.setVisible(false);

    }
    private void removeAllFields() {
        remove(typeLabel);
        remove(typeComboBox);
        remove(animalLabel);
        remove(animalComboBox);
        remove(nameLabel);
        remove(nameTextField);
        remove(genderLabel);
        remove(genderComboBox);
        remove(weiLabel);
        remove(weiTextField);
        remove(speLabel);
        remove(speTextField);
        remove(energyLabel);
        remove(energyTextField);
        remove(EPMLabel);
        remove(EPMTextField);
        remove(MaxEnergyLabel);
        remove(MaxEnergyTextField);
        remove(medalsLabel);
        remove(medalsSpinner);
        remove(createBtn);

        // Remove specific fields
        remove(numLegsLabel);
        remove(numLegsField);
        remove(breedLabel);
        remove(breedField);
        remove(castratedLabel);
        remove(castratedCheckBox);
        remove(poisonousLabel);
        remove(poisonousComboBox);
        remove(lengthLabel);
        remove(lengthField);
        remove(diveDepthLabel);
        remove(diveDepthField);
        remove(areaOfLivingLabel);
        remove(areaOfLivingField);
        remove(foodTypeLabel);
        remove(foodTypeField);
        remove(waterTypeLabel);
        remove(waterTypeComboBox);
        remove(wingspanLabel);
        remove(wingspanField);
        remove(altitudeLabel);
        remove(altitudeField);
        remove(familyLabel);
        remove(familyField);
    }


    private void showDogFields() {
        removeAllFields();
        addCommonFieldsToPanel();
        showCommonFields();
        add(numLegsLabel);
        add(numLegsField);
        add(breedLabel);
        add(breedField);
        numLegsLabel.setVisible(true);
        numLegsField.setVisible(true);
        breedLabel.setVisible(true);
        breedField.setVisible(true);
        add(createBtn, BorderLayout.SOUTH);

    }

    private void showCatFields() {
        hideAllFields();
        showCommonFields();
        add(numLegsLabel);
        add(numLegsField);
        add(castratedLabel);
        add(castratedCheckBox);
        numLegsLabel.setVisible(true);
        numLegsField.setVisible(true);
        castratedLabel.setVisible(true);
        castratedCheckBox.setVisible(true);
        add(createBtn, BorderLayout.SOUTH);

    }

    private void showSnakeFields() {
        removeAllFields();
        addCommonFieldsToPanel();
        showCommonFields();
        add(poisonousLabel);
        add(poisonousComboBox);
        add(lengthLabel);
        add(lengthField);
        poisonousLabel.setVisible(true);
        poisonousComboBox.setVisible(true);
        lengthLabel.setVisible(true);
        lengthField.setVisible(true);
        add(createBtn, BorderLayout.SOUTH);

    }

    private void showAlligatorFields() {
        removeAllFields();
        addCommonFieldsToPanel();
        showCommonFields();
        add(numLegsLabel);
        add(numLegsField);
        add(diveDepthLabel);
        add(diveDepthField);
        add(areaOfLivingLabel);
        add(areaOfLivingField);
        numLegsLabel.setVisible(true);
        numLegsField.setVisible(true);
        diveDepthLabel.setVisible(true);
        diveDepthField.setVisible(true);
        areaOfLivingLabel.setVisible(true);
        areaOfLivingField.setVisible(true);
        add(createBtn, BorderLayout.SOUTH);

    }

    private void showDolphinFields() {
        removeAllFields();
        addCommonFieldsToPanel();
        showCommonFields();
        add(diveDepthLabel);
        add(diveDepthField);
        add(waterTypeLabel);
        add(waterTypeComboBox);
        diveDepthLabel.setVisible(true);
        diveDepthField.setVisible(true);
        waterTypeLabel.setVisible(true);
        waterTypeComboBox.setVisible(true);
        add(createBtn, BorderLayout.SOUTH);

    }

    private void showWhaleFields() {
        removeAllFields();
        addCommonFieldsToPanel();
        showCommonFields();
        add(diveDepthLabel);
        add(diveDepthField);
        add(foodTypeLabel);
        add(foodTypeField);
        diveDepthLabel.setVisible(true);
        diveDepthField.setVisible(true);
        foodTypeLabel.setVisible(true);
        foodTypeField.setVisible(true);
        add(createBtn, BorderLayout.SOUTH);

    }

    private void showEagleFields() {
        removeAllFields();
        addCommonFieldsToPanel();
        showCommonFields();
        add(wingspanLabel);
        add(wingspanField);
        add(altitudeLabel);
        add(altitudeField);
        wingspanLabel.setVisible(true);
        wingspanField.setVisible(true);
        altitudeLabel.setVisible(true);
        altitudeField.setVisible(true);
        familyLabel.setVisible(true);
        familyField.setVisible(true);
        add(createBtn, BorderLayout.SOUTH);

    }

    private void showPigeonFields() {
        removeAllFields();
        addCommonFieldsToPanel();
        showCommonFields();
        add(wingspanLabel);
        add(wingspanField);
        add(familyLabel);
        add(familyField);
        wingspanLabel.setVisible(true);
        wingspanField.setVisible(true);
        altitudeLabel.setVisible(true);
        altitudeField.setVisible(true);
        familyLabel.setVisible(true);
        familyField.setVisible(true);
        add(createBtn, BorderLayout.SOUTH);
    }

    private void refreshUI() {
        revalidate();
        repaint();
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public Vector<Animal> getCreatedAnimals() {
        return animals;
    }
}
