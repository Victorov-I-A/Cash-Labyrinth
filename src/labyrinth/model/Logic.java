package labyrinth.model;

public class Logic {
    private int[][] matrixOfCash;
    private int[][] matrixOfOpenDoors;
    private int cash;

    public  Logic(int[][] matrixOfCash, int[][] matrixOfOpenDoors, int cash) {
        this.matrixOfCash = matrixOfCash;
        this.matrixOfOpenDoors = matrixOfOpenDoors;
        this.cash = cash;

        System.out.println(matrixOfCash);
        System.out.println(matrixOfOpenDoors);
    }
}
