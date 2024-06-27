package mini2.model;

import mini2.controller.Exit;
import mini2.controller.Room;
import mini2.controller.Item;
import mini2.gameExceptions.GameException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RoomDB {
    private static RoomDB instance;
    private ArrayList<Room> rooms;

    private RoomDB() throws GameException {
        rooms = new ArrayList<>();
        readRooms();
    }

    public static RoomDB getInstance() throws GameException {
        if (instance == null) {
            instance = new RoomDB();
        }
        return instance;
    }

    private void readRooms() throws GameException {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/data/Rooms.txt"))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the first line

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header line
                    continue;
                }

                System.out.println("Processing line: " + line); // Debugging line

                String[] parts = line.split("\\|");
                if (parts.length != 5) {
                    throw new GameException("Invalid room format: " + line);
                }

                int id = Integer.parseInt(parts[0].trim());
                String name = parts[1].trim();
                String description = parts[2].trim();
                String[] exitsData = parts[3].trim().split(",");
                String[] itemsData = parts[4].trim().split(",");

                Room room = new Room(id);
                room.setName(name);
                room.setDescription(description);

                for (String exitData : exitsData) {
                    if (!exitData.trim().isEmpty()) {
                        Exit exit = new Exit();
                        exit.buildExit(exitData.trim());
                        room.getExits().add(exit);
                    }
                }

                for (String itemData : itemsData) {
                    if (!itemData.trim().isEmpty()) {
                        int itemId = Integer.parseInt(itemData.trim());
                        Item item = ItemDB.getInstance().getItem(itemId);
                        room.getRoomItems().add(item);
                    }
                }

                rooms.add(room);
            }
        } catch (IOException e) {
            throw new GameException("Rooms file not found.");
        } catch (NumberFormatException e) {
            throw new GameException("Invalid number format in room data.");
        }
    }

    public Room getRoom(int roomID) throws GameException {
        if (roomID < 0 || roomID >= rooms.size()) {
            throw new GameException("Room ID not found.");
        }
        return rooms.get(roomID);
    }

    public ArrayList<Item> getItems(int roomID) throws GameException {
        Room room = getRoom(roomID);
        return room.getRoomItems();
    }

    public void updateRoom(Room room) throws GameException {
        int index = rooms.indexOf(room);
        if (index == -1) {
            throw new GameException("Room not found.");
        }
        rooms.set(index, room);
    }

    public String getMap() {
        StringBuilder map = new StringBuilder();
        for (Room room : rooms) {
            map.append(room.getDescription()).append("\n");
        }
        return map.toString();
    }
}
