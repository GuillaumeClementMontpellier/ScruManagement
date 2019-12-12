package ui.button;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.parent.LoginLayout;

public class LoginButton extends Button {

    public static class LoginEventHandler implements EventHandler<ActionEvent> {
        private final TextField userTextField;
        private final PasswordField passTextField;
        private final LoginLayout ll;

        /*LoginFacade lf*/

        public LoginEventHandler(TextField userTextField, PasswordField pwBox, LoginLayout loginLayout/*,lf*/) {
            super();

            this.ll = loginLayout;

            this.userTextField = userTextField;
            this.passTextField = pwBox;

            // this.lf = lf;
        }


        @Override
        public void handle(ActionEvent e) {

            userTextField.setText("Tried");

            String username = userTextField.getText();
            String password = passTextField.getText();

            try {
                // User u = lf.login(username, password);
                // if (u == null) {
                //
                // } else {
                //    ll.goToMain(u);
                // }
            } catch(Exception except){
                except.printStackTrace();

                // TODO : Error Login
            }
        }

    }

    public LoginButton(String s, TextField userTextField, PasswordField pwBox,LoginLayout loginLayout/*, lf*/) {
        super(s);

        // Valid Event
        this.setOnAction(new LoginEventHandler(userTextField, pwBox, loginLayout/*lf*/));
    }
}
