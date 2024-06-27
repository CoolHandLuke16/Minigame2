package mini2.controller;

import mini2.gameExceptions.GameException;

import java.util.List;

public class Exit {
    private String direction;
    private int destination;
    private static final List<String> VALID_DIRECTIONS = List.of("N", "S", "E", "W", "U", "D");

    public Exit() {
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void buildExit(String ex) throws GameException {
        String[] parts = ex.split(" ");
        if (parts.length != 2 || !VALID_DIRECTIONS.contains(parts[0])) {
            throw new GameException("Invalid exit format.");
        }
        this.direction = parts[0];
        this.destination = Integer.parseInt(parts[1]);
    }

    @Override
    public String toString() {
        return direction + " " + destination;
    }
}
