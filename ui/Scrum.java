package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.parent.LoginParent;

public class Scrum extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Scrum Welcome");

//        LoginFacade lf = new LoginFacade();

        // Setup
        Scene scene = new Scene(new LoginParent(/*lf*/), 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
