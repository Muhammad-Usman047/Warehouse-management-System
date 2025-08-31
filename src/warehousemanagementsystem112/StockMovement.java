package warehousemanagementsystem112;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockMovement implements ActionListener {
    JFrame frame;
    JTextField idField, quantityField,nameField;
    JButton pickButton, packButton, shipButton;
    InventoryLevel inventoryLevel;
    boolean isItemPicked = false;  // Flag to check if the item has been picked
    int pickedItemId = -1;         // Stores the ID of the picked item
    int pickedQuantity = 0;        // Stores the quantity of the picked item
    String pickedItemName = "";    // Stores the name of the picked item

    public StockMovement(InventoryLevel inventoryLevel) {
        this.inventoryLevel = inventoryLevel;

        frame = new JFrame("Stock Movement");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel idLabel = new JLabel("Item ID:");
        idField = new JTextField(9);
        JLabel nameLabel = new JLabel("Item Name");
        nameField=new JTextField(9);
        
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(9);

        pickButton = new JButton("Pick");
        packButton = new JButton("Pack");
        shipButton = new JButton("Ship");

        Dimension buttonSize = new Dimension(200, 30);
        pickButton.setPreferredSize(buttonSize);
        packButton.setPreferredSize(buttonSize);
        shipButton.setPreferredSize(buttonSize);

        panel.add(createButtonPanel(idLabel, idField));
         panel.add(createButtonPanel(nameLabel, nameField));
        panel.add(createButtonPanel(quantityLabel, quantityField));
        panel.add(createButtonPanel(pickButton));
        panel.add(createButtonPanel(packButton));
        panel.add(createButtonPanel(shipButton));

        frame.add(panel);
        frame.setVisible(true);

        pickButton.addActionListener(this);
        packButton.addActionListener(this);
        shipButton.addActionListener(this);
    }

    private JPanel createButtonPanel(JComponent... components) {
        JPanel buttonPanel = new JPanel();
        for (JComponent component : components) {
            buttonPanel.add(component);
        }
        return buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String itemID = idField.getText();
        String quantityStr = quantityField.getText();
        String itemName=nameField.getText();
        
        if (!itemID.matches("\\d+")) {
            JOptionPane.showMessageDialog(frame, "Item ID must be a number.");
            return;
        }

        if (!quantityStr.matches("\\d+")) {
            JOptionPane.showMessageDialog(frame, "Quantity must be a number.");
            return;
        }
       
        int itemId = Integer.parseInt(itemID);
        int quantity = Integer.parseInt(quantityStr);

        if (e.getSource() == pickButton) {
            if (inventoryLevel.getItemNameById(itemId) != null && inventoryLevel.getItemQuantityById(itemId) >= quantity) {
                pickedItemId = itemId;
                pickedQuantity = quantity;
                pickedItemName = inventoryLevel.getItemNameById(itemId);
                isItemPicked = true;
                JOptionPane.showMessageDialog(frame, "Item picked successfully. Name: " + pickedItemName + ", ID: " + pickedItemId + ", Quantity: " + pickedQuantity);
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to pick item. Check if the item ID and quantity are valid and available.");
            }
        } else if (e.getSource() == packButton) {
            if (isItemPicked && pickedItemId == itemId && pickedQuantity == quantity) {
                JOptionPane.showMessageDialog(frame, "Item packed successfully. Name: " + pickedItemName + ", ID: " + pickedItemId + ", Quantity: " + pickedQuantity);
            } else {
                JOptionPane.showMessageDialog(frame, "Item must be picked before packing.");
            }
        } else if (e.getSource() == shipButton) {
            if (isItemPicked && pickedItemId == itemId && pickedQuantity == quantity) {
                boolean success = inventoryLevel.shipItem(itemId, quantity);
                if (success) {
                    JOptionPane.showMessageDialog(frame, "Item shipped successfully. Name: " + pickedItemName + ", ID: " + pickedItemId + ", Quantity: " + pickedQuantity);
                    inventoryLevel.updateTable(FileHandling.readFromFile());
                    isItemPicked = false;  // Reset the flag after shipping
                    pickedItemId = -1;     // Reset the picked item ID
                    pickedQuantity = 0;
                    pickedItemName = "";
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to ship item. Check if the item ID and quantity are valid and available.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Item must be picked before shipping.");
            }
        }
    }
}

