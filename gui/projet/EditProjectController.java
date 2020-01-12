package gui.projet;

import business.facade.GlobalFacade;
import business.system.Projet;
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


public class EditProjectController {

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

    private Projet project;

    private boolean delete;

    public void setProject(Projet project) {
        this.project = project;
        this.delete = false;
        nameField.setText(project.getName());
        summaryField.setText(project.getSummary());
        typeField.setText(project.getType());
        deadLinePicker.setValue(project.getDeadline().toLocalDate());
    }

    public void setUser(User u) {
        this.user = u;
    }

    @FXML
    void handleConfirm(ActionEvent event) throws IOException, SQLException {

        String name = nameField.getText();
        String summary = summaryField.getText();
        String type = typeField.getText();
        Date deadline = Date.valueOf(deadLinePicker.getValue());

        Projet projet = new Projet(-1, name, summary, type, deadline);

        boolean success = GlobalFacade.getInstance().addProject(projet, user);

        if (!success) {
            message.setText("Error when Submitting Project");
        }else {
            Scrum.goToProjectList(user, getClass().getResource("ProjetList.fxml"));
        }
    }

    @FXML
    void handleReturn(ActionEvent event) throws IOException, SQLException {
        Scrum.goToProjectList(user, getClass().getResource("ProjetList.fxml"));
    }

    @FXML
    void handleDelete(ActionEvent actionEvent) throws SQLException, IOException {
        if (!delete) {
            message.setText("Are you sure ? this cannot be undone !");
            message.setVisible(true);
            return;
        }

        boolean success = GlobalFacade.getInstance().deleteProject(project);
        if (!success) {
            message.setText("Error when deleting Project");
        }else {
            Scrum.goToProjectList(user, getClass().getResource("ProjetList.fxml"));
        }

    }
}
