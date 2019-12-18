package gui.login;

import business.facade.GlobalFacade;
import business.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Scrum;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Text message;

    public void initialize() {
        // TODO
    }

    @FXML
    void goToRegister(ActionEvent event) {
        // TODO

    }

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        System.out.println("Try Login");

        String username = usernameField.getText();
        String password = passwordField.getText();

        User u = null;
        try {
            u = GlobalFacade.getInstance().login(username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        if (u == null) {
            message.setText("Invalid Mail or Password");
            message.setVisible(true);

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("../main/Home.fxml"));

            Scene scene = new Scene(root);
            Scrum.getStage().setScene(scene);
        }
    }
}

