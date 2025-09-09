/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import Animals.Animal;
import Races.*;
import Util.Enums;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

;
/**
 * The main frame
 */
public class CompetitionFrame extends JFrame implements ActionListener {
    public static final int MOVEPIXSELS=1;
    protected JPanel btnPanel;
    protected JMenuBar menuBar;
    protected JMenu fileMenu,helpMenu;
    protected JMenuItem exitMenuItem,helpMenuItem;
    protected CompetitionPanel compPanel;
    protected JButton compBtn;
    protected JButton addAnimalBtn;
    protected JButton clearBtn;
    protected JButton eatBtn;
    protected JButton infoBtn;
    protected JButton exitBtn;
    //Frame Variables
    private Vector<Animal> animalVector;
    private Enums.CompetitionType comp;
    //HW3
    protected JButton startBtn;
    protected JButton raceDeBtn;
    protected JButton restartBtn;
    private AtomicBoolean startFlag;
    private Tournament tournament;
    private TournamentThread tournamentThread;
    private int numberOfGroups;
    private Animal[][] animals;
    private int tournamentType;//0-Regular 1-Courier .
    private RaceDetails raceDetailsPanel;

    /**
     * Constructor
     */
    public CompetitionFrame(){
        super("Competition");


        this.animalVector=new Vector<Animal>();

        startFlag=new AtomicBoolean();
        this.comp=null;


        compPanel=new CompetitionPanel();
        btnPanel=new JPanel();
        menuBar=new JMenuBar();
        fileMenu=new JMenu("File");
        helpMenu=new JMenu("Help");
        exitMenuItem=new JMenuItem("Exit");
        helpMenuItem=new JMenuItem("Help");

        compBtn=new JButton("Competition");
        addAnimalBtn=new JButton("Create Groups");
        clearBtn=new JButton("Clear");
        eatBtn=new JButton("Eat");
        infoBtn=new JButton("Info");
        exitBtn=new JButton("Exit");
        startBtn=new JButton("Start");
        raceDeBtn=new JButton("Scores");
        restartBtn=new JButton("Restart");

        raceDeBtn.setEnabled(false);
        startBtn.setEnabled(false);
        addAnimalBtn.setEnabled(false);
        clearBtn.setEnabled(false);
        eatBtn.setEnabled(false);

        exitMenuItem.addActionListener(this);
        helpMenuItem.addActionListener(this);
        compBtn.addActionListener(this);
        addAnimalBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        eatBtn.addActionListener(this);
        infoBtn.addActionListener(this);
        exitBtn.addActionListener(this);
        startBtn.addActionListener(this);
        raceDeBtn.addActionListener(this);
        restartBtn.addActionListener(this);


        fileMenu.add(exitMenuItem);
        helpMenu.add(helpMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);


        btnPanel.setSize(800,50);
        btnPanel.add(compBtn);
        btnPanel.add(addAnimalBtn);
        btnPanel.add(startBtn);
        btnPanel.add(raceDeBtn);
        btnPanel.add(clearBtn);
        btnPanel.add(eatBtn);
        btnPanel.add(infoBtn);
        btnPanel.add(restartBtn);
        btnPanel.add(exitBtn);


        add(compPanel);
        add(btnPanel,BorderLayout.SOUTH);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(790,650);
        setVisible(true);
    }

    /**
     * Temporary main to start the program.
     * @param args arguments recived.
     */
    public static void main(String[] args) {
        new CompetitionFrame();
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==exitMenuItem||e.getSource()==exitBtn){
            dispose();
        }//exit on the menu OR exit button
        if(e.getSource()==helpMenuItem){
            JOptionPane.showMessageDialog(this,"Home Work 2\nGUI");
        }//help on menu
        if(e.getSource()==addAnimalBtn){
            if(this.comp!=null) {
                CreateGroupsPanel panel=new CreateGroupsPanel(numberOfGroups,comp,animals);
                int x = JOptionPane.OK_OPTION;
                int result = JOptionPane.showConfirmDialog(this,panel, "Create Groups", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    animals = panel.getAnimals();
                    new LetsDraw(animals,comp).letsDraw();
                    updateVector();
                    compPanel.setAnimalVector(animalVector);
                    tournamentType=panel.getTournamentType();
                    eatBtn.setEnabled(true);
                    startBtn.setEnabled(true);
                    clearBtn.setEnabled(true);
                }
            }else JOptionPane.showMessageDialog(this,"Please select a competition type first!");
        }//add animal button
        if(e.getSource()==clearBtn){
            if(animalVector.size()==0){
                clearBtn.setEnabled(false);
            }
            else {
                Object[] possibilities = animalVector.toArray();
                Animal animalToRemove = (Animal) JOptionPane.showInputDialog(
                        this,
                        "Choose animal to remove",
                        "Remove Animal",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        animalVector.toArray(),
                        animalVector.get(0));
                if (animalToRemove != null) {
                    animalToRemove.myFinalize();
                    animalVector.removeElementAt(animalVector.indexOf(animalToRemove));
                    removeFromAnimals(animalToRemove);
                    compPanel.removeAll();
                    compPanel.repaint();
                    JOptionPane.showMessageDialog(this, "Animal Removed");
                }
            }
        }//clear button
        if(e.getSource()==eatBtn) {
            JPanel panel=new JPanel();
            String[] stringList=new String[animalVector.size()];
            for(int i=0;i<stringList.length;i++){
                stringList[i]=animalVector.get(i).getName();
            }
            JComboBox animalJComboBox=new JComboBox(stringList);
            JTextField eatTextField=new JTextField();
            panel.setLayout(new GridLayout(2,0));
            panel.setSize(200,400);
            panel.add(new JLabel("Animal:"));
            panel.add(animalJComboBox);
            panel.add(new JLabel("Enter energy amount:"));
            panel.add(eatTextField);
            int result =JOptionPane.showConfirmDialog(this,panel,"Feed Animal",JOptionPane.OK_CANCEL_OPTION);
            if(result==JOptionPane.OK_OPTION){
                Animal tempAnimal=animalVector.get(animalJComboBox.getSelectedIndex());
                if(tempAnimal.eat(Integer.parseInt(eatTextField.getText()))) {
                    tempAnimal.moveInDirection();
                    compPanel.repaint();
                    System.out.println("Animal Fed");
                }
            }
        }//eat button
        if(e.getSource()==compBtn){
            SelectCompetition panel=new SelectCompetition();
            int result =JOptionPane.showConfirmDialog(this,panel,"Select Competition",JOptionPane.OK_CANCEL_OPTION);
            if(result==JOptionPane.OK_OPTION){
                numberOfGroups=panel.getNumberOfTeams();
                if(numberOfGroups>0) {
                    setComp(panel.getSelectedType());
                    raceDetailsPanel = new RaceDetails(numberOfGroups);
                    System.out.println(numberOfGroups);
                    compBtn.setEnabled(false);
                    addAnimalBtn.setEnabled(true);
                }
                else
                    JOptionPane.showMessageDialog(this,"Please press save\nTry again.");
            }
        }//competition button
        if(e.getSource()==infoBtn){
            DisplayAnimals panel=new DisplayAnimals(animalVector);
            JOptionPane.showMessageDialog(this, panel, "Display Animals", JOptionPane.INFORMATION_MESSAGE);
        }//info button
        if(e.getSource()==startBtn){
            switch (tournamentType){
                case 0:
                    tournament=new ReqularTournament(animals, LetsDraw.CurrentRaceDistance,raceDetailsPanel);
                    break;
                case 1:
                    tournament=new CourierTournament(animals, LetsDraw.CurrentRaceDistance,raceDetailsPanel);
                    break;
                default:

                    break;
            }
            if(tournament!=null){
                new Thread(tournament.getThread()).start();
                raceDeBtn.setEnabled(true);
            }
        }//Start button
        if(e.getSource()==raceDeBtn){
            JOptionPane.showMessageDialog(this, raceDetailsPanel, "Display Animals", JOptionPane.INFORMATION_MESSAGE);
        }//Starting the rave button
        if(e.getSource()==restartBtn){
            this.dispose();
            new CompetitionFrame();
        }
    }

    /**
     * Sets the competition type to what the user entered
     * @param type competition type
     */
    private void setComp(Enums.CompetitionType type){
        this.comp=type;
    }

    /**
     * Updates the animal vector to match the animal groups;
     */
    private void updateVector(){
        animalVector.removeAllElements();
        synchronized (animals) {
            for (int i = 0; i < animals.length; i++) {
                for (int j = 0; j < animals[i].length; j++) {
                    animalVector.add(animals[i][j]);
                }
            }
        }
    }

    /**
     * Removing animal from one of the animal teams.
     * @param ani Animal to be removed.
     */
    private void removeFromAnimals(Animal ani){
        int r=-1;
        int c=-1;
        synchronized (animals) {
            for (int i = 0; i < animals.length; i++) {
                for (int j = 0; j < animals[i].length; j++) {
                    if (animals[i][j].equals(ani)) {
                        r = i;
                        c = j;
                    }
                }
            }
        }

        //Creating new team with the same animals from the other team without the animal to remove.
        Animal[] newTeam=new Animal[animals[r].length-1];
        int z=0;
        synchronized (animals) {
            for (int k = 0; k < animals[r].length; k++) {
                if (k == c)
                    continue;
                newTeam[z] = animals[r][k];
                z++;
            }
            animals[r] = newTeam;
        }
    }
}
