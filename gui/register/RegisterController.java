package gui.register;

import business.facade.GlobalFacade;
import business.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Scrum;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterController {
    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private Label message;

    @FXML
    void goToLogin(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../login/LoginFXML.fxml"));

        Scene scene = new Scene(root);
        Scrum.getStage().setScene(scene);
    }
    @FXML
    void register(ActionEvent event) throws IOException{
        System.out.println("Try register");

        String username = emailField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        User u = null;
        try {
            u = GlobalFacade.getInstance().register(username, password, firstName, lastName);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (u == null) {
            message.setText("The email was already taken");
            message.setVisible(true);

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("../main/Home.fxml"));

            Scene scene = new Scene(root);
            Scrum.getStage().setScene(scene);
        }
    }
}
