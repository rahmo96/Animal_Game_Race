
/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import Util.Enums;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel to select the competition type.
 */
public class SelectCompetition extends JPanel implements ActionListener {
    private String[] CompTypes={"Air","Water","Terrestrial"};
    private Enums.CompetitionType selectedType;
    private int numberOfTeams;
    protected JComboBox typeComboBox;
    protected JLabel mainLabel;
    protected JLabel numberLabel;
    protected JTextField numberTextField;
    protected JButton saveBtn;

    /**
     * Constructor
     */
    public SelectCompetition(){
        selectedType=Enums.CompetitionType.Air;

        setSize(50,50);
        setLayout(new GridLayout(3,0));
        typeComboBox=new JComboBox(CompTypes);
        mainLabel=new JLabel("Select competition type");
        numberLabel=new JLabel("Number of Teams:");
        numberTextField=new JTextField("0");
        saveBtn=new JButton("Save");

        typeComboBox.addActionListener(this);
        saveBtn.addActionListener(this);

        add(mainLabel, TOP_ALIGNMENT);
        add(typeComboBox);
        add(numberLabel);
        add(numberTextField);
        add(saveBtn);



        setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==saveBtn){
            setSelectedType(CompTypes[typeComboBox.getSelectedIndex()]);
            try {
                numberOfTeams = Integer.parseInt(numberTextField.getText());
            }catch (NumberFormatException z){
                JOptionPane.showMessageDialog(this,"Enter a number!");
                numberOfTeams=0;
            }
            if(numberOfTeams>5){
                JOptionPane.showMessageDialog(this,"Number of teams cant be higher then 5");
                numberTextField.setText("5");
                numberOfTeams=5;
            }
            if(numberOfTeams<=0){
                JOptionPane.showMessageDialog(this,"Number of teams cant be lower then 1");
                numberTextField.setText("1");
                numberOfTeams=1;
            }
        }

    }

    /**
     * get the selected competition type
     * @return selected type
     */
    public Enums.CompetitionType getSelectedType(){
        return selectedType;
    }

    /**
     * get the number of teams
     * @return the number of teams
     */
    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    /**
     * turns string into a CompetitionType enum.
     * @param type
     */
    public void setSelectedType(String type) {
        switch (type){
            case "Air":
                selectedType=Enums.CompetitionType.Air;
                break;
            case "Water":
                selectedType=Enums.CompetitionType.Water;
                break;
            case "Terrestrial":
                selectedType=Enums.CompetitionType.Terrestrial;
                break;
        }

    }
}
