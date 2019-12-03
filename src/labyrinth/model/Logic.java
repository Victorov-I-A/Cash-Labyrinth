package labyrinth.model;

import java.util.*;

public class Logic {
    private HashSet<Room> finalWay = new HashSet<>();
    private int finalCost;

    private Room startRoom;
    private Room endRoom;

    public Logic(int[][] matrix, int[][] horizonDoors, int[][] verticalDoors, Pair startCoordinate, Pair endCoordinate) {
        Graph graph = new Graph(matrix, startCoordinate, endCoordinate);
        graph.createGraph(horizonDoors,verticalDoors);
        startRoom = graph.getStartRoom();
        endRoom = graph.getEndRoom();
    }

    public void findWay() {
        HashSet<Room> finalWay = new HashSet<>();
        int finalCost = Integer.MIN_VALUE;

        ArrayDeque<Triplet> allWays = new ArrayDeque<>();

        allWays.addFirst(new Triplet(new HashSet<>(), startRoom, 0));
        allWays.getFirst().getWay().add(startRoom);

        while (!allWays.isEmpty()) {
            Triplet previous = allWays.getFirst();

            for (Room neighbor: previous.getRoom().getNeighbors()) {
                if (neighbor == endRoom && previous.getCost() > finalCost) {
                    finalWay = previous.getWay();
                    finalCost = previous.getCost();
                } else if
                    (!previous.getWay().contains(neighbor) && neighbor != endRoom) {
                    allWays.addLast(previous.nextStep(neighbor));
                }
            }

            allWays.removeFirst();
        }
        finalWay.remove(startRoom);
        this.finalWay = finalWay;
        this.finalCost = finalCost;
    }

    public LinkedList<Pair> getFinalWay() {
        LinkedList<Pair> coordinates = new LinkedList<>();

        finalWay.forEach(room ->
                coordinates.add(room.getCoordinate())
        );
        return coordinates;

    }

    public int getFinalCost() {
        return finalCost;
    }
}
