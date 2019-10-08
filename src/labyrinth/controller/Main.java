package labyrinth.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = (Pane)FXMLLoader.load(getClass().getResource("../view/mainWindow.fxml"));

        Controller controller = new Controller();
        ((Pane) root).getChildren().add(controller.playField);
        controller.playField.setLayoutX(215);
        controller.playField.setLayoutY(115);

        primaryStage.setTitle("Cash labyrinth");
        primaryStage.setScene(new Scene(root, 1200, 1000));
        primaryStage.show();
    }
}
