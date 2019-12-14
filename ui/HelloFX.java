package ui;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class HelloFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // create a new business.Text shape
            Text messageText = new Text("Hello World! Lets learn JavaFX.");

            // stack page
            StackPane root = new StackPane();

            //Button ?
            Button btn = new Button();
            btn.setText("Display business.Message");
            btn.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    System.out.println("Hi there! You clicked me!");

                }
            });

            // add business.Text shape to Stack Pane
            ObservableList<Node> children = root.getChildren();
            children.add(messageText);
            children.add(btn);

            Scene scene = new Scene(root, 400, 400);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}