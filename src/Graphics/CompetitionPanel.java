
/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;import Animals.Animal;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * the competition panel on the main frame.
 */
public class CompetitionPanel extends JPanel {
    private static CompetitionPanel obs;
    Vector<Animal> animalVector;
    private BufferedImage backgroundImag;
    public static final String BACKGROUNG_PATH="src/graphics2/competitionBackground.png";
    //HW3
    public AtomicBoolean startFlag;
    /**
     * Constructor
     */
    public CompetitionPanel(){
        super();
        animalVector=null;
        setSize(610,446);
        try { backgroundImag = ImageIO.read(new File(CompetitionPanel.BACKGROUNG_PATH)); }
        catch (IOException e) { System.out.println("Cannot load image"); }
        startFlag=new AtomicBoolean();
        obs=this;
    }
    /**
     * sets the local animal vector.
     * @param animalVector new animal vector.
     */
    public void setAnimalVector(Vector<Animal> animalVector) {
        this.animalVector = animalVector;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImag,0,0,getWidth(),getHeight(),this);
        if(animalVector!=null){
            //animalVector.get(0).drawObject(g);
            for(int i=0;i<animalVector.size();i++){
                animalVector.get(i).drawObject(g);
            }
        }
    }

    /**
     * return panel instance.
     * @return panel instance.
     */
    public static CompetitionPanel getInstance(){
        if(obs==null)
            return null;
        return obs;
    }

    public void addAnimal(Animal newAnimal) {
        animalVector.add(newAnimal);
    }

    public void addCompetition(Competition newCompetition) {
        startFlag.set(true);
        repaint();
    }
}
