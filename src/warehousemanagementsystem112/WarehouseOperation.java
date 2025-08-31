package warehousemanagementsystem112;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WarehouseOperation implements ActionListener {
    private JFrame frame;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton updateProductButton;
    private JButton viewProductsButton;

    public WarehouseOperation() {
        frame = new JFrame("Warehouse Operations");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Warehouse Operations:"));

        addProductButton = new JButton("Add Product");
        removeProductButton = new JButton("Remove Product");
        updateProductButton = new JButton("Update Product");
        viewProductsButton = new JButton("View Products");

        Dimension buttonSize = new Dimension(200, 30);
        addProductButton.setPreferredSize(buttonSize);
        removeProductButton.setPreferredSize(buttonSize);
        updateProductButton.setPreferredSize(buttonSize);
        viewProductsButton.setPreferredSize(buttonSize);

        panel.add(createButtonPanel(addProductButton));
        panel.add(createButtonPanel(removeProductButton));
        panel.add(createButtonPanel(updateProductButton));
        panel.add(createButtonPanel(viewProductsButton));

        frame.add(panel);
        frame.setVisible(true);

        addProductButton.addActionListener(this);
        removeProductButton.addActionListener(this);
        updateProductButton.addActionListener(this);
        viewProductsButton.addActionListener(this);
    }

    private JPanel createButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        return buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == addProductButton) {
            showAddProductPanel();
        } else if (source == removeProductButton) {
            showRemoveProductPanel();
        } else if (source == updateProductButton) {
            showUpdateProductPanel();
        } else if (source == viewProductsButton) {
            new InventoryLevel().setVisible(true);
        }
    }

    private void showAddProductPanel() {
        JFrame addFrame = createFrame("Add Product", 300, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JTextField idField = createLabeledTextField(panel, "Item ID:");
        JTextField nameField = createLabeledTextField(panel, "Item Name:");
        JTextField quantityField = createLabeledTextField(panel, "Quantity:");
        JTextField locationField = createLabeledTextField(panel, "Location:");
        JTextField alphafield = createLabeledTextField(panel, "alpha:");
        JTextField betafield = createLabeledTextField(panel, "beta:");
        JButton submitButton = new JButton("Submit");

        panel.add(new JLabel());
        panel.add(submitButton);

        addFrame.add(panel);
        addFrame.setVisible(true);

        submitButton.addActionListener(e -> {
            String itemID = idField.getText();
            String itemName = nameField.getText();
            String quantity = quantityField.getText();
            String location = locationField.getText();
            String all = alphafield.getText();
            String bll = alphafield.getText();
            
            if (!itemID.matches("\\d+")) {
                JOptionPane.showMessageDialog(addFrame, "Item ID must be a number.");
                return;
            }

            if (!quantity.matches("\\d+")) {
                JOptionPane.showMessageDialog(addFrame, "Quantity must be a number.");
                return;
            }

            if (FileHandling.itemExists(itemID, itemName, location)) {
                JOptionPane.showMessageDialog(addFrame, "Item ID, Name, or Location already exists. Please use the Update Product option.");
                return;
            }

            Item newItem = new Item(itemID, itemName, quantity, location,all,bll);
            boolean added = FileHandling.addItem(newItem);
            if (added) {
                JOptionPane.showMessageDialog(addFrame, "Item added successfully.");
                new InventoryLevel().updateTable(FileHandling.readFromFile());
                addFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(addFrame, "Error adding item. Please try again.");
            }
        });
    }

    private void showRemoveProductPanel() {
        JFrame removeFrame = createFrame("Remove Product", 300, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JTextField idField = createLabeledTextField(panel, "Item ID:");
        JButton submitButton = new JButton("Submit");

        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(submitButton);

        removeFrame.add(panel);
        removeFrame.setVisible(true);

        submitButton.addActionListener(e -> {
            String itemID = idField.getText();

            if (!itemID.matches("\\d+")) {
                JOptionPane.showMessageDialog(removeFrame, "Item ID must be a number.");
                return;
            }

            boolean removed = FileHandling.removeItem(itemID);
            if (removed) {
                JOptionPane.showMessageDialog(removeFrame, "Item removed successfully.");
                new InventoryLevel().updateTable(FileHandling.readFromFile());
                removeFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(removeFrame, "Item ID not found.");
            }
        });
    }

    private void showUpdateProductPanel() {
        JFrame updateFrame = createFrame("Update Product", 300, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        JTextField idField = createLabeledTextField(panel, "Item ID:");
        JTextField nameField = createLabeledTextField(panel, "Item Name:");
        JTextField quantityField = createLabeledTextField(panel, "Quantity:");
        JTextField locationField = createLabeledTextField(panel, "Location:");
        JTextField alphafield = createLabeledTextField(panel, "alpha:");
        JTextField betafield = createLabeledTextField(panel, "beta:");
        
        JButton submitButton = new JButton("Submit");

        panel.add(new JLabel());
        panel.add(submitButton);

        updateFrame.add(panel);
        updateFrame.setVisible(true);

        submitButton.addActionListener(e -> {
            String itemID = idField.getText();
            String itemName = nameField.getText();
            String quantity = quantityField.getText();
            String location = locationField.getText();
            String all = alphafield.getText();
            String bll = alphafield.getText();
            
            if (!itemID.matches("\\d+")) {
                JOptionPane.showMessageDialog(updateFrame, "Item ID must be a number.");
                return;
            }

            if (!quantity.matches("\\d+")) {
                JOptionPane.showMessageDialog(updateFrame, "Quantity must be a number.");
                return;
            }

            if (!FileHandling.itemExistsById(itemID)) {
                JOptionPane.showMessageDialog(updateFrame, "No item exists with this ID.");
                return;
            }

            if (!FileHandling.itemExistsByName(itemName)) {
                JOptionPane.showMessageDialog(updateFrame, "No item exists with this Name.");
                return;
            }

            boolean updated = FileHandling.updateItem(new Item(itemID, itemName, quantity, location,all,bll));
            if (updated) {
                JOptionPane.showMessageDialog(updateFrame, "Item updated successfully.");
                new InventoryLevel().updateTable(FileHandling.readFromFile());
                updateFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(updateFrame, "Item ID not found.");
            }
        });
    }

    private JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    private JTextField createLabeledTextField(JPanel panel, String labelText) {
        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField();
        panel.add(label);
        panel.add(textField);
        return textField;
    }
}
