package mini2.controller;

public class Item {
    private int itemID;
    private String itemName;
    private String itemDescription;

    public Item() {
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String display() {
        return itemDescription;
    }

    @Override
    public String toString() {
        return itemName + ": " + itemDescription;
    }
}
