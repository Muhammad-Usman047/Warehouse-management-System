package warehousemanagementsystem112;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 8632066432547182381L;
    
    private String itemID;
    private String itemName;
    private String quantity;
    private String location;
    private String alpha1;
    private String beta1;
    

    public Item(String itemID, String itemName, String quantity, String location,String alpha1,String beta1) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.location = location;
        this.alpha1 = alpha1;
        this.beta1 = beta1;
    }

    public String getAlpha1() {
        return alpha1;
    }

    public void setAlpha1(String alpha1) {
        this.alpha1 = alpha1;
    }

    public String getBeta1() {
        return beta1;
    }

    public void setBeta1(String beta1) {
        this.beta1 = beta1;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addQuantity(int quantity) {
        this.quantity = String.valueOf(Integer.parseInt(this.quantity) + quantity);
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID='" + itemID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
