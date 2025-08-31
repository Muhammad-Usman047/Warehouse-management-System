package warehousemanagementsystem112;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class InventoryLevel extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public InventoryLevel() {
        setTitle("Inventory Level");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();
        model.addColumn("Item ID");
        model.addColumn("Item Name");
        model.addColumn("Quantity");
        model.addColumn("Location");
        model.addColumn("alpha");
        model.addColumn("beta");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        updateTable(FileHandling.readFromFile());
    }

    public void updateTable(List<Item> data) {
        model.setRowCount(0);
        for (Item item : data) {
            model.addRow(new Object[]{item.getItemID(), item.getItemName(), item.getQuantity(), item.getLocation(),item.getAlpha1(),item.getBeta1()});
        }
    }

    public boolean pickItem(int itemId, int qty) {
        return checkItemQuantity(itemId, qty);
    }

    public boolean packItem(int itemId, int qty) {
        return checkItemQuantity(itemId, qty);
    }

    public boolean shipItem(int itemId, int qty) {
        boolean success = updateItemQuantity(itemId, -qty);
        if (success) {
            FileHandling.writeToFile(readTableData());
        }
        return success;
    }

    private boolean checkItemQuantity(int itemId, int qty) {
        for (int i = 0; i < model.getRowCount(); i++) {
            int id = Integer.parseInt(model.getValueAt(i, 0).toString());
            if (id == itemId) {
                int currentQty = Integer.parseInt(model.getValueAt(i, 2).toString());
                return currentQty >= qty;
            }
        }
        return false;
    }

    private boolean updateItemQuantity(int itemId, int qtyChange) {
        for (int i = 0; i < model.getRowCount(); i++) {
            int id = Integer.parseInt(model.getValueAt(i, 0).toString());
            if (id == itemId) {
                int currentQty = Integer.parseInt(model.getValueAt(i, 2).toString());
                if (qtyChange < 0 && Math.abs(qtyChange) > currentQty) {
                    JOptionPane.showMessageDialog(this, "Not enough quantity available.");
                    return false; // Operation failed
                }
                int newQty = currentQty + qtyChange;
                model.setValueAt(newQty, i, 2);
                return true; // Operation successful
            }
        }
        JOptionPane.showMessageDialog(this, "Item ID not found.");
        return false; // Operation failed
    }

    private List<Item> readTableData() {
        List<Item> data = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String itemId = model.getValueAt(i, 0).toString();
            String itemName = model.getValueAt(i, 1).toString();
            String quantity = model.getValueAt(i, 2).toString();
            String location = model.getValueAt(i, 3).toString();
            String a1 = model.getValueAt(i, 4).toString();
            String b1 = model.getValueAt(i, 5).toString();
            data.add(new Item(itemId, itemName, quantity, location,a1,b1));
        }
        return data;
    }

    public String getItemNameById(int itemId) {
        for (int i = 0; i < model.getRowCount(); i++) {
            int id = Integer.parseInt(model.getValueAt(i, 0).toString());
            if (id == itemId) {
                return model.getValueAt(i, 1).toString();
            }
        }
        return null;
    }

    public int getItemQuantityById(int itemId) {
        for (int i = 0; i < model.getRowCount(); i++) {
            int id = Integer.parseInt(model.getValueAt(i, 0).toString());
            if (id == itemId) {
                return Integer.parseInt(model.getValueAt(i, 2).toString());
            }
        }
        return 0;
    }
}