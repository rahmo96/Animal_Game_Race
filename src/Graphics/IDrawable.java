/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;import java.awt.Graphics;

public interface IDrawable {
    public final static String PICTURE_PATH ="src/graphics2/";
    public void loadImages(String nm);
    public void drawObject (Graphics g);
    public double moveInDirection();

}
