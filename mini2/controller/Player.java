package mini2.controller;

import mini2.model.RoomDB;
import mini2.gameExceptions.GameException;

import java.util.ArrayList;

public class Player {
    private int curRoom;
    private ArrayList<Item> inventory;

    protected Player() {
        inventory = new ArrayList<>();
    }

    public int getCurRoom() {
        return curRoom;
    }

    public void setCurRoom(int current) {
        this.curRoom = current;
    }

    protected ArrayList<Item> getInventory() {
        return inventory;
    }

    protected void addItem(Item it) {
        inventory.add(it);
    }

    protected Item removeItem(String itemName) {
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                return item;
            }
        }
        return null;
    }

    protected Item getItem(String itemName) {
        for (Item item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    protected String look() throws GameException {
        return RoomDB.getInstance().getRoom(curRoom).display();
    }

    protected String showBackpack() {
        if (inventory.isEmpty()) {
            return "Your backpack is empty.";
        }
        StringBuilder backpackContents = new StringBuilder("Your backpack contains:\n");
        for (Item item : inventory) {
            backpackContents.append(item.toString()).append("\n");
        }
        return backpackContents.toString();
    }
}
