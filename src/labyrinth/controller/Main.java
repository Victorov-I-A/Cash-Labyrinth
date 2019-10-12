package labyrinth.controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import labyrinth.view.View;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Controller controller = new Controller();

        View view = new View(controller);
        view.create();

        Group root = new Group();
        root.getChildren().add(controller.pane);

        primaryStage.setTitle("Cash labyrinth");
        primaryStage.setScene(new Scene(root, 1200, 1000));
        primaryStage.show();
    }
}
