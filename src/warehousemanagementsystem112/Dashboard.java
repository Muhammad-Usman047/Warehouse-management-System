package warehousemanagementsystem112;

import java.awt.Dimension;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dashboard implements ActionListener {
    JFrame frame;
    JButton warehouseOperationButton, stockMovementButton, inventoryLevelButton, signOutButton;

    public Dashboard() {
        frame = new JFrame("Dashboard");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        warehouseOperationButton = new JButton("Warehouse Operation");
        stockMovementButton = new JButton("Stock Movement");
        inventoryLevelButton = new JButton("Inventory Level");
        signOutButton = new JButton("SignOut");

        Dimension buttonSize = new Dimension(200, 30);
        warehouseOperationButton.setPreferredSize(buttonSize);
        stockMovementButton.setPreferredSize(buttonSize);
        inventoryLevelButton.setPreferredSize(buttonSize);
        signOutButton.setPreferredSize(buttonSize);

        panel.add(createButtonPanel(warehouseOperationButton));
        panel.add(createButtonPanel(stockMovementButton));
        panel.add(createButtonPanel(inventoryLevelButton));
        panel.add(createButtonPanel(signOutButton));

        frame.add(panel);
        frame.setVisible(true);

        warehouseOperationButton.addActionListener(this);
        stockMovementButton.addActionListener(this);
        inventoryLevelButton.addActionListener(this);
        signOutButton.addActionListener(this);
    }

    private JPanel createButtonPanel(JButton button) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        return buttonPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == warehouseOperationButton) {
            new WarehouseOperation();
        } else if (e.getSource() == stockMovementButton) {
            new StockMovement(new InventoryLevel());
        } else if (e.getSource() == inventoryLevelButton) {
            new InventoryLevel().setVisible(true);
        } else if (e.getSource() == signOutButton) {
            frame.dispose();
            new ManagerLogin();
        }
    }
}