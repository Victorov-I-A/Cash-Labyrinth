package labyrinth.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import labyrinth.model.Logic;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Logic logic;
    private int[][] matrixOfRoom;
    private List<Integer>[] matrixOfDoor = new ArrayList[15];
    private boolean isStart = false;
    private boolean isEnd = false;
    private TextField start;
    private TextField end;
    private int cash;

    public TextField[][] matrixOfTField = new TextField[8][8];
    public List<Button>[] matrixOfButton = new ArrayList[15];

    public final Pane pane = new Pane();
    public final TextField cashField = new TextField();
    public final Button startButton = new Button();
    public final Button reset = new Button();
    public final VBox playField = new VBox();

    public final Label errorLabel = new Label();
    public final Stage errorWindow = new Stage();

    public Controller() {
        pane.getChildren().addAll(playField, cashField, startButton, reset);

        for (int i = 0; i < 15; i++) {

            HBox hBox = new HBox();
            hBox.prefHeight(770);
            hBox.prefWidth(70);

            matrixOfButton[i] = new ArrayList<>();
            matrixOfDoor[i] = new ArrayList<>();

            if (i % 2 == 0)
                for (int j = 0; j < 15; j++) {
                    if (j % 2 == 0) {

                        TextField textField = new TextField();
                        textField.setPrefSize(70, 70);

                        hBox.getChildren().add(textField);
                        matrixOfTField[i / 2][j / 2] = textField;
                    }
                    else {

                        Button button = new Button();
                        button.setPrefSize(30, 70);

                        hBox.getChildren().add(button);
                        matrixOfButton[i].add(button);
                        matrixOfDoor[i].add(0);
                    }
                }
            else {
                hBox.setSpacing(30);

                for (int j = 0; j < 8; j++) {

                    Button button = new Button();
                    button.setPrefSize(70, 30);

                    hBox.getChildren().add(button);
                    matrixOfButton[i].add(button);
                    matrixOfDoor[i].add(0);
                }
            }
            playField.getChildren().add(hBox);
        }
    }

    public void doorAction(int x, int y) {
        if (matrixOfDoor[x].get(y).equals(0)) {
            matrixOfButton[x].get(y).setStyle("-fx-background-color: white");
            matrixOfDoor[x].set(y, 1);
        }
        else {
            matrixOfButton[x].get(y).setStyle("-fx-background-color");
            matrixOfDoor[x].set(y, 0);
        }
    }

    public void roomAction(int x, int y) {
        if (isStart == false && matrixOfTField[x][y] != end) {

            matrixOfTField[x][y].setStyle("-fx-background-color: yellow");
            matrixOfTField[x][y].setText("start");
            matrixOfTField[x][y].setEditable(false);

            isStart = true;
            start = matrixOfTField[x][y];
            return;
        }
        if (isStart == true && matrixOfTField[x][y] == start) {

            matrixOfTField[x][y].setStyle("-fx-background-color");
            matrixOfTField[x][y].setText("");
            matrixOfTField[x][y].setEditable(true);

            isStart = false;
            start = null;
            return;
        }
        if (isEnd == false && matrixOfTField[x][y] != start) {
            matrixOfTField[x][y].setStyle("-fx-background-color: yellow");
            matrixOfTField[x][y].setText("end");
            matrixOfTField[x][y].setEditable(false);

            isEnd = true;
            end = matrixOfTField[x][y];
            return;
        }
        if (isEnd == true && matrixOfTField[x][y] == end) {

            matrixOfTField[x][y].setStyle("-fx-background-color");
            matrixOfTField[x][y].setText("");
            matrixOfTField[x][y].setEditable(true);

            isEnd = false;
            end = null;
        }
    }

    public void resetAction() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                matrixOfTField[i][j].setStyle("-fx-background-color");
                matrixOfTField[i][j].setText("");
                matrixOfTField[i][j].setEditable(true);

                matrixOfRoom = null;
                isEnd = false;
                isStart = false;
                start = null;
                end = null;
            }

        for (int i = 0; i < 15; i++) {
            matrixOfButton[i].forEach(button -> button.setStyle("-fx-background-color"));
            int size = matrixOfDoor[i].size();
            for (int j = 0; j < size; j++)
                matrixOfDoor[i].set(j, 0);
        }
    }

    public void startAction() {
        int[][] matrixOfRoom = new int[8][8];

        try {
            cash = Integer.parseInt(cashField.getText());
        }  catch (NumberFormatException e) {
            errorLabel.setText("Поле с начальной суммой не заполнено");
            errorWindow.show();
        }

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (matrixOfTField[i][j] != end && matrixOfTField[i][j] != start) {
                    try {
                    matrixOfRoom[i][j] = Integer.parseInt(matrixOfTField[i][j].getText());
                    } catch (NumberFormatException e) {
                        errorLabel.setText("Данные в поле " + i + 1 + ":" + j + 1 + " введены некорректно");
                        errorWindow.show();
                    }
                }
                else
                    matrixOfRoom[i][j] = 0;
            }

        logic = new Logic(matrixOfRoom, matrixOfDoor);
    }
}
