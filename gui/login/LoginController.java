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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

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
    void goToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../register/register.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

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
            //TODO : Make main layout

            Parent root = FXMLLoader.load(getClass().getResource("../main/Home.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        }
    }
}

