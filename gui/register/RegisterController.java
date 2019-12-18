package gui.register;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class RegisterController {

    void goToLogin(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../login/login.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
    }
}
