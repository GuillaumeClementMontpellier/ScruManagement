package gui.project;

import business.facade.GlobalFacade;
import business.system.Project;
import business.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Scrum;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


public class CreateProjectController {

    @FXML
    private Text message;

    @FXML
    private TextArea summaryField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField typeField;

    @FXML
    private DatePicker deadLinePicker;

    private User user;

    public void setUser(User u) {
        this.user = u;
    }

    @FXML
    void handleConfirm(ActionEvent event) throws SQLException, IOException {
        String name = nameField.getText();
        String summary = summaryField.getText();
        String type = typeField.getText();
        Date deadline = Date.valueOf(deadLinePicker.getValue());

        Project project = new Project(-1, name, summary, type, deadline);
        boolean success = GlobalFacade.getInstance().createProject(project, user);
        if (!success) {
            message.setText("Erreur lors de creation de project");
            message.setVisible(true);
            return;
        }

        Scrum.goToProjectList(user, getClass().getResource("ProjectList.fxml"));

    }

    @FXML
    void handleReturn(ActionEvent event) throws SQLException, IOException {
        Scrum.goToProjectList(user, getClass().getResource("ProjectList.fxml"));
    }

}
