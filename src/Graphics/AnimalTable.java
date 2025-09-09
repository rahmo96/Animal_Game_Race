/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import Animals.Animal;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * JTable specialized for displaying animals with images.
 */
public class AnimalTable extends JTable {
    public AnimalTable(List<Animal> animals) {
        super(new AnimalTableModel(animals));
        setRowHeight(30); // Adjust row height to accommodate images
        getColumnModel().getColumn(2).setCellRenderer(new ImageRenderer());
        getColumnModel().getColumn(3).setCellRenderer(new ImageRenderer());
    }

    private static class ImageRenderer extends JLabel implements TableCellRenderer {
        public ImageRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            if (value instanceof BufferedImage) {
                setIcon(new ImageIcon((BufferedImage) value));
            } else {
                setText(value != null ? value.toString() : "");
                setIcon(null);
            }
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            return this;
        }
    }
}
