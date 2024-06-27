package mini2.controller;

import mini2.model.RoomDB;
import mini2.gameExceptions.GameException;

import java.util.List;

public class Commands {
    private Player player;

    protected static final List<Character> VALID_DIRECTIONS = List.of('N', 'S', 'E', 'W', 'U', 'D');
    protected static final List<Character> ITEM_COMMANDS = List.of('G', 'R', 'I');
    public static final int EXIT_COMMAND = 0;

    public Commands() throws GameException {
        player = new Player();
    }

    protected String executeCommand(String cmd) throws GameException {
        int commandType = validateCommand(cmd);
        switch (commandType) {
            case 1:
                return move(cmd);
            case 2:
                return itemCommand(cmd);
            case 3:
                return player.look();
            case 4:
                return player.showBackpack();
            case EXIT_COMMAND:
                return "Exiting game.";
            default:
                throw new GameException("Invalid command.");
        }
    }

    private int validateCommand(String cmdLine) throws GameException {
        char command = cmdLine.charAt(0);
        if (VALID_DIRECTIONS.contains(command)) {
            return 1;
        } else if (ITEM_COMMANDS.contains(command)) {
            return 2;
        } else if (command == 'L') {
            return 3;
        } else if (command == 'B') {
            return 4;
        } else if (command == 'X') {
            return EXIT_COMMAND;
        } else {
            throw new GameException("Invalid command.");
        }
    }

    private String move(String cmd) throws GameException {
        char direction = cmd.charAt(0);
        Room currentRoom = RoomDB.getInstance().getRoom(player.getCurRoom());
        int nextRoomID = currentRoom.validDirection(direction);
        player.setCurRoom(nextRoomID);
        Room nextRoom = RoomDB.getInstance().getRoom(nextRoomID);
        return nextRoom.display();
    }

    private String itemCommand(String cmd) throws GameException {
        char action = cmd.charAt(0);
        String itemName = cmd.substring(2).trim();
        Room currentRoom = RoomDB.getInstance().getRoom(player.getCurRoom());
        switch (action) {
            case 'G':
                return get(itemName, currentRoom);
            case 'R':
                return remove(itemName, currentRoom);
            case 'I':
                return lookItem(itemName, currentRoom);
            default:
                throw new GameException("Invalid item command.");
        }
    }

    private String get(String itemName, Room room) throws GameException {
        Item item = room.removeItem(itemName);
        player.addItem(item);
        return itemName + " has been picked up.";
    }

    private String remove(String itemName, Room room) throws GameException {
        Item item = player.removeItem(itemName);
        room.addItem(item);
        return itemName + " has been dropped.";
    }

    private String lookItem(String itemName, Room room) throws GameException {
        Item item = room.getItem(itemName);
        if (item != null) {
            return item.display();
        }
        item = player.getItem(itemName);
        if (item != null) {
            return item.display();
        }
        return "I don't see " + itemName + ".";
    }
}
