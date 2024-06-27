package mini2.controller;

import mini2.model.ItemDB;
import mini2.model.RoomDB;
import mini2.gameExceptions.GameException;

import java.util.ArrayList;

public class Room {
    private int roomID;
    private String name;
    private String description;
    private ArrayList<Exit> exits;
    private ArrayList<Item> items;
    private boolean visited;
    private RoomDB rdb;
    private ItemDB idb;

    public Room() throws GameException {
        this.exits = new ArrayList<>();
        this.items = new ArrayList<>();
        this.rdb = RoomDB.getInstance();
        this.idb = ItemDB.getInstance();
    }

    public Room(int id) throws GameException {
        this();
        this.roomID = id;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setExits(ArrayList<Exit> exits) {
        this.exits = exits;
    }

    public ArrayList<Item> getRoomItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Item removeItem(String itemName) throws GameException {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        throw new GameException("Item not found in room.");
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public String display() {
        return buildDescription() + "\n" + buildItems() + "\n" + displayExits();
    }

    private String buildDescription() {
        return description;
    }

    private String buildItems() {
        if (items.isEmpty()) {
            return "No items in the room.";
        }
        StringBuilder itemsDescription = new StringBuilder("Items in the room:\n");
        for (Item item : items) {
            itemsDescription.append(item.toString()).append("\n");
        }
        return itemsDescription.toString();
    }

    private String displayExits() {
        if (exits.isEmpty()) {
            return "No exits.";
        }
        StringBuilder exitsDescription = new StringBuilder("Exits:\n");
        for (Exit exit : exits) {
            exitsDescription.append(exit.toString()).append("\n");
        }
        return exitsDescription.toString();
    }

    public int validDirection(char cmd) throws GameException {
        for (Exit exit : exits) {
            if (exit.getDirection().charAt(0) == cmd) {
                return exit.getDestination();
            }
        }
        throw new GameException("Invalid direction.");
    }

    public Room retrieveByID(int roomNum) throws GameException {
        return rdb.getRoom(roomNum);
    }

    public void updateRoom() throws GameException {
        rdb.updateRoom(this);
    }
}
