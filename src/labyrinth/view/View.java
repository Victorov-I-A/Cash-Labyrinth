package labyrinth.view;

import labyrinth.controller.Controller;

public class View {
    private Controller controller;


    public View(Controller controller) {
        this.controller = controller;
    }


    public void create() {
        controller.cashField.setPromptText("Начальная сумма");
        controller.cashField.setPrefSize(120, 25);
        controller.cashField.setLayoutX(1030);
        controller.cashField.setLayoutY(50);

        controller.startButton.setText("Старт!");
        controller.startButton.setPrefSize(70, 70);
        controller.startButton.setLayoutX(1080);
        controller.startButton.setLayoutY(880);

        controller.reset.setText("Сброс");
        controller.reset.setPrefSize(70, 70);
        controller.reset.setLayoutX(1000);
        controller.reset.setLayoutY(880);


        controller.playField.setPrefSize(770, 770);
        controller.playField.setLayoutX(215);
        controller.playField.setLayoutY(115);

        for (int i = 0; i < 15; i++){

            int sizeOfCurrentLine = 8;
            if (i % 2 == 0)
                sizeOfCurrentLine--;

            for (int j = 0; j < sizeOfCurrentLine; j++) {
                int x = i;
                int y = j;
                controller.matrixOfButton[x].get(y).setOnMouseClicked( e -> controller.doorAction(x, y));
            }
        }

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                int x = i;
                int y = j;
                controller.matrixOfTField[x][y].setOnMouseClicked( e -> controller.roomAction(x, y));
            }

        controller.startButton.setOnMouseClicked( e -> controller.startAction());

        controller.reset.setOnMouseClicked(e -> controller.resetAction());
    }
}