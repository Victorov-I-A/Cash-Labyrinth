package labyrinth.model;

import java.util.*;

public class Logic {
    private HashSet<Room> finalWay = new HashSet<>();
    private int finalCost = -2147483648;

    private Room startRoom;
    private Room endRoom;

    public Logic(int[][] matrix, int[][] horizonDoors, int[][] verticalDoors, Pair startCoordinate, Pair endCoordinate) {
        Room[][] matrixOfRoom = new Graph(matrix).createGraph(horizonDoors,verticalDoors);
        startRoom = matrixOfRoom[startCoordinate.getX()][startCoordinate.getY()];
        endRoom = matrixOfRoom[endCoordinate.getX()][endCoordinate.getY()];
    }

    public void findWay() {
        HashSet<Room> finalWay = new HashSet<>();
        int finalCost = -2147483648;

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
                    (!previous.getWay().contains(neighbor)) {
                    allWays.addLast(previous.nextStep(neighbor));
                }
                neighbor.removeFromNeighbors(previous.getRoom());
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
