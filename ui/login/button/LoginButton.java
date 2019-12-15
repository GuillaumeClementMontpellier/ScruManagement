package ui.login.button;

import business.facade.GlobalFacade;
import business.system.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.login.LoginLayout;

import java.sql.SQLException;

public class LoginButton extends Button {

    public LoginButton(String s, TextField userTextField, PasswordField pwBox, LoginLayout loginLayout) {
        super(s);

        // Set Event Handler for Validation
        this.setOnAction(new LoginEventHandler(userTextField, pwBox, loginLayout));
    }

    public static class LoginEventHandler implements EventHandler<ActionEvent> {
        private final TextField userTextField;
        private final PasswordField passTextField;
        private final LoginLayout ll;

        public LoginEventHandler(TextField userTextField, PasswordField pwBox, LoginLayout loginLayout) {
            super();

            this.ll = loginLayout;

            this.userTextField = userTextField;
            this.passTextField = pwBox;
        }


        @Override
        public void handle(ActionEvent e) {

            String username = userTextField.getText();
            String password = passTextField.getText();

            User u = null;
            try {
                u = GlobalFacade.getInstance().login(username, password);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            if (u == null) {
                ll.notLogin();
            } else {
                ll.goToMain(u);
            }
        }

    }
}
