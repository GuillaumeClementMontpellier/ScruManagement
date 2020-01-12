package gui.project;

import business.facade.GlobalFacade;
import business.system.Project;
import business.system.User;
import gui.main.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Scrum;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


public class EditProjectController extends AbstractController {

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

    private Project project;

    private boolean delete;

    public void setProject(Project project) {
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
    void handleConfirmEdit(ActionEvent event) throws SQLException {
        System.out.println("EditProjectController.handleConfirm");

        String name = nameField.getText();
        String summary = summaryField.getText();
        String type = typeField.getText();
        Date deadline = Date.valueOf(deadLinePicker.getValue());

        if (user.equals(GlobalFacade.getInstance().getProjectAdmin(project))) {
            int idProject = project.getId();
            Project editProject = new Project(idProject, name, summary, type, deadline);
            GlobalFacade.getInstance().editProject(editProject);
        }
        else {
            message.setText("Unauthorized action. Ask the administrator project to do this");
            message.setVisible(true);
            return;
        }
    }

    @FXML
    void handleReturn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../project/Project.fxml"));
    }

    @FXML
    void handleDelete(ActionEvent actionEvent) {
        if (!delete) {
            message.setText("Are you sure? This cannot be undone!");
            message.setVisible(true);
            return;
        }
    }

    void handleConfirmDelete(ActionEvent actionEvent) throws IOException, SQLException {
        if (!delete) {
            message.setText("Are you sure? This cannot be undone!");
            message.setVisible(true);
            return;
        }
        Scrum.goToProjectList(user, getClass().getResource("ProjectList.fxml"));
    }


    @Override
    public void init(Object param) {

    }
}
