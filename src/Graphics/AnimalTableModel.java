
/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;import Animals.Animal;
import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Table model for displaying animals in a JTable.
 */
public class AnimalTableModel extends AbstractTableModel {
    private static final String[] COLUMN_NAMES = {"Animal", "Category", "Type", "Speed", "Energy Amount","Max Energy", "Distance", "Energy Consumption"};
    private final List<Animal> animals;

    public AnimalTableModel(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public int getRowCount() {
        return animals.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Animal animal = animals.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> animal.getName();
            case 1 -> animal.getCategory();
            case 2 -> animal.getSpecies();
            case 3 -> animal.getSpeed();
            case 4 -> animal.getEnergy();
            case 5 -> animal.getMaxEnergy();
            case 6 -> animal.getTotalDistance();
            case 7 -> animal.getEnergyConsumption();
            default -> null;
        };
    }

}
