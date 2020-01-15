package gui.backlog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RowTitleController {
    @FXML
    private Label label;

    public void setComponent(String label) {
        this.label.setText(label);

    }
}
