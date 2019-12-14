package ui.parent;

import business.system.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import ui.button.LoginButton;

public class LoginLayout extends GridPane {

    private Text errorLogin;

    public LoginLayout() {

        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        // Up
        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        this.add(sceneTitle, 0, 0, 2, 1);

        // User Name
        Label userName = new Label("User Name:");
        this.add(userName, 0, 1);
        TextField userTextField = new TextField();
        this.add(userTextField, 1, 1);

        // Password
        Label pw = new Label("Password:");
        this.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        this.add(pwBox, 1, 2);

        // Valid
        Button btn = new LoginButton("Connect", userTextField, pwBox, this);
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        this.add(hbBtn, 1, 4);

        // Text display result
        this.errorLogin = new Text();
        this.add(errorLogin, 1, 6);
    }

    public void goToMain(User u) {
        this.errorLogin.setText("Welcome Back " + u.getFirstName() + " " + u.getLastName() + " !");
    }

    public void notLogin() {
        this.errorLogin.setText("Invalid Password or UserName.");
    }
}
