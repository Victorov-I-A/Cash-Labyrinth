package test;

import labyrinth.model.Graph;
import labyrinth.model.Logic;
import labyrinth.model.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Test {

    @org.junit.jupiter.api.Test
    void graphTest() {
        int[][] matrix = new int[8][8];
        int[][] horizonDoors = new int[7][8];
        int[][] verticalDoors = new int[8][7];

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                matrix[i][j] = 1;
                if (i != 7)
                    horizonDoors[i][j] = 0;
                if (j != 7)
                    verticalDoors[i][j] = 0;
            }

        horizonDoors[0][0] = 1;
        verticalDoors[0][0] = 1;

        Graph graph = new Graph(matrix, new Pair(0,0), new Pair(0, 1));
        graph.createGraph(horizonDoors, verticalDoors);

        assertTrue(graph.getStartRoom().getNeighbors().contains(graph.getMatrixOfRoom()[1][0]));
        assertTrue(graph.getStartRoom().getNeighbors().contains(graph.getEndRoom()));
    }

    @org.junit.jupiter.api.Test
    void logicTest() {
        int[][] matrix = new int[8][8];
        int[][] horizonDoors = new int[7][8];
        int[][] verticalDoors = new int[8][7];

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                matrix[i][j] = 8;
                if (i != 7)
                    horizonDoors[i][j] = 0;
                if (j != 7 && i == 0)
                    verticalDoors[i][j] = 1;
                else if (j != 7)
                    verticalDoors[i][j] = 0;
            }

        Logic logic = new Logic(matrix, horizonDoors, verticalDoors, new Pair(0, 0), new Pair(0, 7));
            logic.findWay();
        assertEquals(48, logic.getFinalCost());
    }

    @org.junit.jupiter.api.Test
    void nullTest() {
        int[][] matrix = new int[8][8];
        int[][] horizonDoors = new int[7][8];
        int[][] verticalDoors = new int[8][7];

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                matrix[i][j] = 1;
                if (i != 7)
                    horizonDoors[i][j] = 0;
                if (j != 7)
                    verticalDoors[i][j] = 0;
            }
        Logic logic = new Logic(matrix, horizonDoors, verticalDoors, new Pair(0, 0), new Pair(0, 7));
            logic.findWay();
        assertTrue(logic.getFinalWay().isEmpty());
    }
}
