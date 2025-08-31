package warehousemanagementsystem112;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandling {
    private static final String FILE_PATH = "inventory_data.txt";

    public static List<Item> readFromFile() {
        List<Item> data = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return data;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            data = (List<Item>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeToFile(List<Item> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean addItem(Item newItem) {
        List<Item> data = readFromFile();
        for (Item item : data) {
            if (item.getItemID().equals(newItem.getItemID())) {
                return false; // Item ID already exists
            }
        }
        data.add(newItem);
        writeToFile(data);
        return true;
    }

    public static boolean removeItem(String itemID) {
        List<Item> data = readFromFile();
        boolean removed = data.removeIf(item -> item.getItemID().equals(itemID));
        writeToFile(data);
        return removed;
    }

    public static boolean updateItem(Item updatedItem) {
        List<Item> data = readFromFile();
        for (Item item : data) {
            if (item.getItemID().equals(updatedItem.getItemID())) {
                item.setItemName(updatedItem.getItemName());
                item.setQuantity(updatedItem.getQuantity());
                item.setLocation(updatedItem.getLocation());
                writeToFile(data);
                return true;
            }
        }
        return false;
    }

    public static boolean itemExists(String itemID, String itemName, String location) {
        List<Item> data = readFromFile();
        for (Item item : data) {
            if (item.getItemID().equals(itemID) || item.getItemName().equals(itemName) || item.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }

    public static boolean itemExistsById(String itemID) {
        List<Item> data = readFromFile();
        for (Item item : data) {
            if (item.getItemID().equals(itemID)) {
                return true;
            }
        }
        return false;
    }
    public static boolean itemExistsByName(String itemName) {
        List<Item> data = readFromFile();
        for (Item item : data) {
            if (item.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }
//     public static void printAllItems() {
//        List<Item> data = readFromFile();
//        for (Item item : data) {
//            System.out.println(item);
//        }
//    }
}
