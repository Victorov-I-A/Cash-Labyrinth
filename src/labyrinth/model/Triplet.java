package labyrinth.model;

import java.util.HashSet;

public class Triplet {
    private HashSet<Room> way;
    private Room room;
    private int cost;

    public Triplet(HashSet<Room> way, Room room, int cost) {
        this.way = way;
        this.room = room;
        this.cost = cost;
    }

    public HashSet<Room> getWay() {
        return way;
    }

    public int getCost() {
        return cost;
    }

    public Room getRoom() {
        return room;
    }

    public Triplet nextStep(Room room) {
        HashSet<Room> newWay = new HashSet<>();

        getWay().forEach(newWay::add);
        newWay.add(room);

        return new Triplet(newWay, room, getCost() + room.getCost());
    }
}
