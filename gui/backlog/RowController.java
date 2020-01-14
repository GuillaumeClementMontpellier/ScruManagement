package gui.backlog;

import business.system.Component;
import gui.main.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class RowController {

    @FXML
    private Text label;

    private HomeController controller;
    private Component component;

    public void setComponent(Component component) {
        this.component = component;
        label.setText(component.getName());
    }

    public void setController(HomeController controller) {
        this.controller = controller;
    }

    public void handleSee(ActionEvent actionEvent) throws IOException {
        // TODO : see dynamic typing
        controller.changeSubScene("../userstory/UserStory.fxml", this.component);
    }
}
