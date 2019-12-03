package labyrinth.controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import labyrinth.model.Logic;
import labyrinth.model.Pair;

public class Controller {
    private Logic logic;
    private int[][] horizonDoors = new int[7][8];
    private int[][] verticalDoors = new int[8][7];
    private boolean isStart = false;
    private boolean isEnd = false;
    private TextField start;
    private TextField end;
    private int cash;

    public TextField[][] matrixOfTField = new TextField[8][8];
    public Button[][] horizonButtons = new Button[7][8];
    public Button[][] verticalButtons = new Button[8][7];

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

            if (i % 2 == 0) {
                for (int j = 0; j < 8; j++) {
                    TextField textField = new TextField();
                    textField.setPrefSize(70, 70);

                    hBox.getChildren().add(textField);
                    matrixOfTField[i / 2][j] = textField;

                    if (j != 7) {
                        Button button = new Button();
                        button.setPrefSize(30, 70);

                        hBox.getChildren().add(button);
                        verticalButtons[i / 2][j] = button;
                        verticalDoors[i / 2][j] = 0;
                    }
                }
            }
            if (i % 2 == 1) {
                hBox.setSpacing(30);

                for (int j = 0; j < 8; j++) {
                    Button button = new Button();
                    button.setPrefSize(70, 30);

                    hBox.getChildren().add(button);
                    horizonButtons[i / 2][j] = button;
                    horizonDoors[i / 2][j] = 0;
                }
            }
            playField.getChildren().add(hBox);
        }
    }

    public void horizonDoorAction(int x, int y) {
        if (horizonDoors[x][y] == 0) {
            horizonButtons[x][y].setStyle("-fx-background-color: white");
            horizonDoors[x][y] = 1;
        } else {
            horizonButtons[x][y].setStyle("-fx-background-color");
            horizonDoors[x][y] = 0;
        }
    }

    public void verticalDoorAction(int x, int y) {
        if (verticalDoors[x][y] == 0) {
            verticalButtons[x][y].setStyle("-fx-background-color: white");
            verticalDoors[x][y] = 1;
        } else {
            verticalButtons[x][y].setStyle("-fx-background-color");
            verticalDoors[x][y] = 0;
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

                if (i != 7) {
                    horizonButtons[i][j].setStyle("-fx-background-color");
                    horizonButtons[i][j].setDisable(false);

                    horizonDoors[i][j] = 0;
                }
                if (j != 7) {
                    verticalButtons[i][j].setStyle("-fx-background-color");
                    verticalButtons[i][j].setDisable(false);

                    verticalDoors[i][j] = 0;
                }

            }
        startButton.setText("Старт!");
        startButton.setOnMouseClicked(e -> startAction());

        isEnd = false;
        isStart = false;
        start = null;
        end = null;

        cash = 0;
        cashField.setText("");
    }

    public void startAction() {
        if (!isStart) {
            errorLabel.setText("Выберите поле старта");
            errorWindow.show();
            return;

        }

        if (!isEnd) {
            errorLabel.setText("Выберите поле выхода");
            errorWindow.show();
            return;

        }

        try {
            cash = Integer.parseInt(cashField.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText("Поле с начальной суммой не заполнено");
            errorWindow.show();
            return;

        }

        int[][] matrixOfRoom = new int[8][8];
        Pair startCoordinate = null;
        Pair endCoordinate = null;

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (matrixOfTField[i][j] == start) {
                    matrixOfRoom[i][j] = 0;
                    startCoordinate = new Pair(i, j);
                } else if (matrixOfTField[i][j] == end) {
                    matrixOfRoom[i][j] = 0;
                    endCoordinate = new Pair(i, j);
                } else try {
                    matrixOfRoom[i][j] = Integer.parseInt(matrixOfTField[i][j].getText());
                } catch (NumberFormatException e) {
                    matrixOfTField[i][j].setText("0");
                    matrixOfRoom[i][j] = 0;
                }
                matrixOfTField[i][j].setEditable(false);
                if (i != 7)
                    horizonButtons[i][j].setDisable(true);
                if (j != 7)
                    verticalButtons[i][j].setDisable(true);
            }

        logic = new Logic(matrixOfRoom, horizonDoors, verticalDoors, startCoordinate, endCoordinate);
        logic.findWay();

        if (logic.getFinalWay().isEmpty()) {
            errorLabel.setText("Поле задано неверно");
            errorWindow.show();
            redactionAction();
            return;
        }

        for (Pair coordinate : logic.getFinalWay()) {
            matrixOfTField[coordinate.getX()][coordinate.getY()].setStyle("-fx-background-color: #B0E0E6");
        }

        cashField.setText(String.valueOf(cash + logic.getFinalCost()));

        startButton.setText("Редактировать");
        startButton.setOnMouseClicked(e -> redactionAction());
    }

    public void redactionAction() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (matrixOfTField[i][j] != end && matrixOfTField[i][j] != start) {
                    matrixOfTField[i][j].setStyle("-fx-background-color");
                    matrixOfTField[i][j].setEditable(true);
                }

                if (i != 7) {
                    horizonButtons[i][j].setDisable(false);
                }
                if (j != 7) {
                    verticalButtons[i][j].setDisable(false);
                }

            }
        cashField.setText(String.valueOf(cash));

        startButton.setText("Старт!");
        startButton.setOnMouseClicked(e -> startAction());
    }
}
