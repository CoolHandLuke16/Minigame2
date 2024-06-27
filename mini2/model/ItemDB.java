package mini2.model;

import mini2.controller.Item;
import mini2.gameExceptions.GameException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ItemDB {
    private static ItemDB instance;
    private ArrayList<Item> items;

    private ItemDB() throws GameException {
        items = new ArrayList<>();
        readItems();
    }

    public static ItemDB getInstance() throws GameException {
        if (instance == null) {
            instance = new ItemDB();
        }
        return instance;
    }

    private void readItems() throws GameException {
        try (BufferedReader reader = new BufferedReader(new FileReader("resources/data/Items.txt"))) {
            String line;
            boolean isFirstLine = true; // Flag to skip the first line

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // Skip the header line
                    continue;
                }

                System.out.println("Processing item line: " + line); // Debugging line

                int id = Integer.parseInt(line.trim());
                String name = reader.readLine().trim();
                String description = reader.readLine().trim();
                Item item = new Item();
                item.setItemID(id);
                item.setItemName(name);
                item.setItemDescription(description);
                items.add(item);
            }
        } catch (IOException e) {
            throw new GameException("Items file not found.");
        } catch (NumberFormatException e) {
            throw new GameException("Invalid number format in item data.");
        }
    }

    public Item getItem(int id) throws GameException {
        for (Item item : items) {
            if (item.getItemID() == id) {
                return item;
            }
        }
        throw new GameException("Item ID not found.");
    }
}
