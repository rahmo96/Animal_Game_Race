/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import Olympics.*;

public class MedalDialog {

    public static List<Medal> showMedalEntryDialog(JDialog parentDialog, int numOfMedals) {
        List<Medal> medals = new ArrayList<>();

        for (int i = 0; i < numOfMedals; i++) {
            // Medal Type
            Medal.type medalType = (Medal.type) JOptionPane.showInputDialog(
                    parentDialog,
                    "Select Medal Type:",
                    "Medal Type",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    Medal.type.values(),
                    Medal.type.BRONZE
            );

            if (medalType == null) {
                // User canceled
                return null;
            }

            // Tournament Name
            String tournament = JOptionPane.showInputDialog(parentDialog, "Enter Tournament Name:");

            if (tournament == null || tournament.trim().isEmpty()) {
                // User canceled or entered an empty string
                JOptionPane.showMessageDialog(parentDialog, "Tournament name cannot be empty.");
                return null;
            }

            // Year Selection
            Integer year = (Integer) JOptionPane.showInputDialog(
                    parentDialog,
                    "Select Year:",
                    "Year",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    getYears(),
                    2024
            );

            if (year == null) {
                // User canceled
                return null;
            }

            medals.add(new Medal(medalType, tournament, year));
        }

        return medals;
    }

    private static Integer[] getYears() {
        Integer[] years = new Integer[2024 - 1900 + 1];
        for (int i = 1900; i <= 2024; i++) {
            years[i - 1900] = i;
        }
        return years;
    }
}
