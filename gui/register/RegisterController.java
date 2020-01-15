package gui.register;

import business.facade.GlobalFacade;
import business.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void goToLogin(ActionEvent event) throws IOException {
        Scrum.setScene(getClass().getResource("../login/Login.fxml"));
    }

    @FXML
    void register(ActionEvent event) throws IOException, SQLException {

        String username = emailField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        User u;
        try {
            u = GlobalFacade.getInstance().register(username, password, firstName, lastName);
        } catch (SQLException ex) {
            ex.printStackTrace();
            message.setText("Error registering");
            message.setVisible(true);
            return;
        }

        if (u == null) {
            message.setText("The email was already taken");
            message.setVisible(true);
            return;
        }

        Scrum.goToProjectList(u, getClass().getResource("../project/ProjectList.fxml"));

    }
}
