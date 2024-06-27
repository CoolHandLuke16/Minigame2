package mini2.controller;

import mini2.model.RoomDB;
import mini2.gameExceptions.GameException;

public class GameController {
    private Commands commands;
    public static final int FIRST_ROOM = 0;

    public GameController() throws GameException {
        commands = new Commands();
    }

    public String displayFirstRoom() throws GameException {
        return RoomDB.getInstance().getRoom(FIRST_ROOM).display();
    }

    public String executeCommand(String cmd) throws GameException {
        return commands.executeCommand(cmd);
    }

    public String printMap() throws GameException {
        return RoomDB.getInstance().getMap();
    }
}
