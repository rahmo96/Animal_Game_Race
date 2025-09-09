
/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import Animals.Animal;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Panel to display all the info on animals in the competition.
 */
public class DisplayAnimals extends JPanel {
    private static String[] columnames={"Animal","Category","Type",
            "Energy amount","Distance","Energy consumed"};
    private Object[][] objects;
    protected JTable jTable;

    /**
     * Constructor
     * @param animals animals to display in the panel.
     */
    public DisplayAnimals(Vector<Animal> animals){
        setSize(600,400);
        objects=new Object[animals.size()][6];
        for(int i=0;i<animals.size();i++){
            objects[i][0]= animals.elementAt(i).getName();
            objects[i][1]= animals.elementAt(i).getCategory();
            objects[i][2]= animals.elementAt(i).getSpecies();
            objects[i][3]=animals.elementAt(i).getCurrentEnergy();
            objects[i][4]= animals.elementAt(i).getTotalDistance();
            objects[i][5]= (double) animals.elementAt(i).calcEnergySpent();

        }
        jTable=new JTable(objects,columnames);
        jTable.setFillsViewportHeight(true);
        add(new JScrollPane(jTable));
        setVisible(true);

    }
}
