package labyrinth.model;

import java.util.*;

public class Room {

    private int cost;
    private Pair coordinate;
    private List<Room> neighbors = new ArrayList<>(); //соседние клетки, в которые можно попасть

    public Room(int cost, int x, int y) {
        this.cost = cost;
        coordinate = new Pair(x, y);
    }

    public int getCost() {
        return cost;
    }

    public Pair getCoordinate() {
        return coordinate;
    }

    public List<Room> getNeighbors() {
        return neighbors;
    }

    public void addToNeighbors(Room room) {
        neighbors.add(room);
    }
}
