package ui.button;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginButton extends Button {

    public class LoginEventHandler implements EventHandler<ActionEvent> {
        private final TextField utf;
        private final PasswordField pwtf;

        /*LoginFacade lf*/

        public LoginEventHandler(TextField userTextField, PasswordField pwBox /*,lf*/) {
            super();

            this.utf = userTextField;
            this.pwtf = pwBox;

            // this.lf = lf;
        }


        @Override
        public void handle(ActionEvent e) {
            //lf.login()
            utf.setText("Tried");
        }

    }

    public LoginButton(String s, TextField userTextField, PasswordField pwBox/*, lf*/) {
        super(s);

        // Valid Event
        this.setOnAction(new LoginEventHandler(userTextField, pwBox/*lf*/));
    }
}
