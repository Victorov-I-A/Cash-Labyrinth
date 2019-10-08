package labyrinth.model;

import java.util.LinkedList;

public class Graph {
    private final int numberOfRooms = 64;
    private LinkedList<Room>[] adjList;

    public Graph() {
        adjList = new LinkedList[numberOfRooms];

        for (int i = 0; i < numberOfRooms; i++) {
            adjList[0] = new LinkedList<>();
        }
    }

    public void addRoom(Room room, int indexOfList) {
        adjList[indexOfList].add(room);
    }
}
