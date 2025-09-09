
/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import javax.swing.*;
import java.util.Map;
import java.util.Set;

/**
 * the panel to display the scores
 */
public class RaceDetails extends JPanel {
    private String[] columNamea={"No.","Name","Time"};
    private Object[][] teams;
    private int numOfTeams;
    private Map scores;
    protected JTable jTable;

    /**
     * Constructor
     * @param num number of teams
     */
    public RaceDetails(int num){
        setSize(600,400);
        teams=new Object[num][3];
        jTable=new JTable(teams,columNamea);
        jTable.setFillsViewportHeight(true);
        add(new JScrollPane(jTable));

    }

    /**
     * Updating the main jtable
     * @param s the updated scores
     */
    public void updateTable(Map s){
        scores=s;
        int i=0;
        Set values=scores.keySet();
        for(Object key:values){
            teams[i][0]=i+1;
            teams[i][1]=key;
            teams[i][2]=scores.get(key);
            i++;
        }
        repaint();
        System.out.println("Table updated");
    }

}
