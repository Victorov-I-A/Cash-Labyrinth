package labyrinth.model;

public class Graph {
    private Room[][] matrixOfRoom = new Room[8][8];

    public Graph(int[][] matrix) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                matrixOfRoom[i][j] = new Room(matrix[i][j], i, j);
            }
    }

    public Room[][] createGraph(int[][] horizonDoors, int[][] verticalDoors) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                Room firstRoom = matrixOfRoom[i][j];
                Room secondRoom;

                if (j != 7 && verticalDoors[i][j] == 1) {
                    secondRoom = matrixOfRoom[i][j + 1];
                    firstRoom.addToNeighbors(secondRoom);
                    secondRoom.addToNeighbors(firstRoom);
                }
                if (i != 7 && horizonDoors[i][j] == 1) {
                    secondRoom = matrixOfRoom[i + 1][j];
                    firstRoom.addToNeighbors(secondRoom);
                    secondRoom.addToNeighbors(firstRoom);
                }
            }
        return matrixOfRoom;
    }
}
