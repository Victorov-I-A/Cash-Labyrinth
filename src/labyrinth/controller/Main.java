package labyrinth.controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
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
        view.createMain();

        {
            Group secondRoot = new Group();

            secondRoot.getChildren().add(controller.errorLabel);

            controller.errorWindow.setTitle("Error");
            controller.errorWindow.setScene(new Scene(secondRoot, 300, 80));

            controller.errorWindow.initModality(Modality.WINDOW_MODAL);
            controller.errorWindow.initOwner(primaryStage);
        }

        {
            Group root = new Group();

            root.getChildren().add(controller.pane);

            primaryStage.setTitle("Cash labyrinth");
            primaryStage.setScene(new Scene(root, 1200, 1000));
            primaryStage.show();
        }
    }
}
