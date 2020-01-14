package gui.backlog;

import business.system.Component;
import business.system.UserStory;
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
        // Todo : Change Bad Smell !
        if (component instanceof UserStory) {
            controller.changeSubScene("../userstory/UserStory.fxml", this.component);
        } else {
            controller.changeSubScene("../ticket/Ticket.fxml", this.component);
        }
    }
}
