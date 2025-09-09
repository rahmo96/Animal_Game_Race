/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import Animals.Animal;
import Util.Enums;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class CreateGroupsPanel extends JPanel implements ActionListener {

    private static final int PANEL_ROWS = 3;
    private static final int PANEL_HEIGHT = 200;
    private static final int TEAM_LABEL_PADDING = 5;

    private JPanel panel1;
    private JPanel panel2;
    private JButton[] addBtns;
    private JLabel[] teamNamesLabels;
    private JList<Animal>[] jLists;
    private ButtonGroup buttonGroup;
    private JRadioButton courierRadioBtn;
    private JRadioButton regularRadioBtn;
    private DefaultListModel[] stringForJlists;
    private JScrollPane[] verticals;

    private Vector<Animal>[] animals;
    private int numberOfGroups;
    private Enums.CompetitionType compType;

    public CreateGroupsPanel(int numberOfGroups, Enums.CompetitionType compType, Animal[][] animals){
        //setSize(300,800);
        this.numberOfGroups=numberOfGroups;
        this.compType=compType;
        this.animals = new Vector[numberOfGroups];
        addBtns=new JButton[numberOfGroups];
        jLists=new JList[numberOfGroups];
        teamNamesLabels=new JLabel[numberOfGroups];
        stringForJlists=new DefaultListModel[numberOfGroups];
        verticals=new JScrollPane[numberOfGroups];

        for(int i=0;i<numberOfGroups;i++){
            this.animals[i]=new Vector<Animal>();
            stringForJlists[i] = new DefaultListModel<String>();
        }
        if(animals!=null){
            for(int i=0;i<animals.length;i++) {
                this.animals[i].addAll(Arrays.asList(animals[i]));
                stringForJlists[i].addAll(Arrays.asList(animals[i]));
            }

        }
        setLayout(new BorderLayout());


        panel1=new JPanel();//new GridLayout(1,2));
        panel2=new JPanel(new GridLayout(3,numberOfGroups));
        buttonGroup=new ButtonGroup();
        regularRadioBtn=new JRadioButton("Regular Tournament");
        regularRadioBtn.setSelected(true);
        courierRadioBtn=new JRadioButton("Courier Tournament");
        buttonGroup.add(regularRadioBtn);
        buttonGroup.add(courierRadioBtn);

        panel2.setPreferredSize(new Dimension(100*numberOfGroups,200));

        add(panel1,BorderLayout.NORTH);
        add(panel2,BorderLayout.SOUTH);
        panel1.add(regularRadioBtn);
        panel1.add(courierRadioBtn);
        for(int i =0;i<numberOfGroups;i++){
            teamNamesLabels[i]=new JLabel("Team "+i+1+":");
            panel2.add(teamNamesLabels[i]);
        }
        for(int i =0;i<numberOfGroups;i++){
            addBtns[i]=new JButton("Add animal");
            addBtns[i].addActionListener(this);
            panel2.add(addBtns[i]);
        }
        for(int i =0;i<numberOfGroups;i++){
            jLists[i]=new JList(stringForJlists[i]);
            verticals[i]=new JScrollPane(jLists[i]);
            panel2.add(verticals[i]);
        }
    }




    private void showAddAnimalDialog(int btnNumber) {
        // Create the dialog
        JDialog dialog = new JDialog((Frame) null, "Add Animal", true);
        AddAnimalDialog panel = new AddAnimalDialog(this.compType);

        // Create OK and Cancel buttons
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // Add action listeners to the buttons
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animals[btnNumber].addAll(panel.getCreatedAnimals());
                updateAnimalList(btnNumber);
                if (animals[btnNumber].size() >= 2) {
                    regularRadioBtn.setEnabled(false);
                    courierRadioBtn.setSelected(true);
                }
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add the custom panel and button panel to the dialog
        dialog.setLayout(new BorderLayout());
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);


        // Set the dialog size and make it visible
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int btnNumber = -1;
        for (int i = 0; i < numberOfGroups; i++) {
            if (e.getSource() == addBtns[i]) {
                btnNumber = i;
            }
        }
        if (btnNumber != -1) {
            showAddAnimalDialog(btnNumber);
        }
    }





    private void updateAnimalList(int btnNumber) {
        stringForJlists[btnNumber].removeAllElements();
        for (Animal animal : animals[btnNumber]) {
            stringForJlists[btnNumber].addElement(animal);
        }
    }

    public int getTournamentType() {
        return courierRadioBtn.isSelected() ? 1 : 0;
    }

    public Animal[][] getAnimals() {
        Animal[][] temp=new Animal[numberOfGroups][];
        for(int i=0;i<numberOfGroups;i++){
            temp[i]=new Animal[animals[i].size()];
            for(int j=0;j<animals[i].size();j++){
                temp[i][j]=animals[i].get(j);
            }
        }
        return temp;
    }



}

