package labyrinth.model;

public class Room {
    private int cost;
    private boolean isVisited;

    public Room(int cost) {
        this.cost = cost;
        isVisited = false;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isPassed() {
        return isVisited;
    }

    public void refreshVisit() {
        isVisited = false;
    }
}
