/**
 * Rahamim Tadela  208189621
 * Almog Ben Gur 207896697
 */

package Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Menu extends JMenuBar {

    private Map<String, JMenu> menus; // To keep track of menus

    public Menu() {
        menus = new HashMap<>();

        // Predefined menus
        addMenuItem("File", "Exit", e -> System.exit(0));
        addMenuItem("Help", "Help", e -> showHelpDialog());
    }

    /**
     * Adds a menu item to a menu. Creates the menu if it does not exist.
     * @param menuName The name of the menu.
     * @param itemName The name of the menu item.
     * @param actionListener The action listener for the menu item.
     */
    public void addMenuItem(String menuName, String itemName, ActionListener actionListener) {
        JMenu menu = menus.get(menuName);
        if (menu == null) {
            // Create new menu if it doesn't exist
            menu = new JMenu(menuName);
            menus.put(menuName, menu);
            add(menu);
        }

        // Create and add the menu item
        JMenuItem menuItem = new JMenuItem(itemName);
        menuItem.addActionListener(actionListener);
        menu.add(menuItem);
    }

    public JMenuBar getMenuBar() {
        return this;
    }

    private void showHelpDialog() {
        JDialog dialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Help", true);
        dialog.setLayout(new FlowLayout());

        JLabel messageLabel = new JLabel("<html>Home Work 2<br>GUI</html>");
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dialog.dispose()); // Close the dialog

        dialog.add(messageLabel);
        dialog.add(okButton);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Center on screen
        dialog.setVisible(true);
    }
}
