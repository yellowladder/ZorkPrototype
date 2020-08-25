package model;

public class InventoryItem {
    private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public InventoryItem(String itemName) {
        this.itemName = itemName;
    }
}
