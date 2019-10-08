package labyrinth.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import labyrinth.model.Logic;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    VBox playField;
    private TextField[][] matrixOfRoom = new TextField[8][8];
    private Button[][] matrixOfDoor = new Button[15][8];
    private int[][] matrixOfCash = new int[8][8];
    private int[][] matrixOfOpenDoors = new int[15][8];


    public Controller() {

        playField = new VBox();
        playField.prefHeight(770);
        playField.prefWidth(770);
        //заполняем массив закрытыми(0) дверьми
        refreshDoors();

        for (int i = 1; i < 16; i++) {

            HBox hBox = new HBox();
            hBox.prefHeight(770);
            hBox.prefWidth(70);

            if (i % 2 == 1)
                for (int j = 0; j < 15; j++) {
                    if (j % 2 == 0) {

                        TextField textField = new TextField();
                        textField.prefHeight(70);
                        textField.prefWidth(70);

                        hBox.getChildren().add(textField);
                        matrixOfRoom[(int)Math.ceil(i / 2)][(int)Math.ceil(j / 2)] = textField;
                    }
                    else {

                        Button button = new Button();
                        button.prefWidth(30);
                        button.prefHeight(70);

                        int finalI = i;
                        int finalJ = j;
                        button.setOnAction(e -> {
                            if (matrixOfOpenDoors[finalI - 1][(int)Math.floor(finalJ / 2)] == 0)

                                matrixOfOpenDoors[finalI - 1][(int)Math.floor(finalJ / 2)] = 1;

                            else
                                matrixOfOpenDoors[finalI - 1][(int)Math.floor(finalJ / 2)] = 0;
                        });

                        hBox.getChildren().add(button);
                        matrixOfDoor[i - 1][(int)Math.floor(j / 2)] = button;
                    }
                }
            else
                for (int j = 0; j < 8; j++) {

                    Button button = new Button();
                    button.prefWidth(70);
                    button.prefHeight(30);

                    int finalI = i;
                    int finalJ = j;
                    button.setOnAction(e -> {
                        if (matrixOfOpenDoors[finalI - 1][finalJ] == 0)

                            matrixOfOpenDoors[finalI - 1][finalJ] = 1;

                        else
                            matrixOfOpenDoors[finalI - 1][finalJ] = 0;
                    });

                    hBox.getChildren().add(button);
                    matrixOfDoor[i - 1][j] = button;
                }
            playField.getChildren().add(hBox);
        }

    }

    @FXML
    private TextField cash;

    @FXML
    private Button startButton;

    @FXML
    private void startButtonAction(ActionEvent e) {

        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 8; j++) {
                matrixOfCash[i][j] = Integer.parseInt(matrixOfRoom[i][j].getText());
            }
        Logic logic = new Logic(matrixOfCash, matrixOfOpenDoors, Integer.parseInt(cash.getText()));
    }

    public void refreshDoors() {

        for (int i = 0; i < 15; i++)
            for (int j = 0; j < 8; j++) {
                matrixOfOpenDoors[i][j] = 0;
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
