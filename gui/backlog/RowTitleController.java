package gui.backlog;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class RowTitleController {
    @FXML
    private Label label;

    public void setLabel(String label) {
        this.label.setText(label);
    }
}
