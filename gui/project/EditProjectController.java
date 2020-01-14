package gui.project;

import business.facade.GlobalFacade;
import business.system.Project;
import business.system.User;
import gui.main.AbstractController;
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
import java.time.LocalDate;


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
    private boolean initCalled;

    public void setProject(Project project) {
        this.project = project;
        this.delete = false;
        initCalled = false;
        nameField.setText(project.getName());
        summaryField.setText(project.getSummary());
        typeField.setText(project.getType());
        System.out.println(project.getDeadline());
        if (project.getDeadline() != null) {

            deadLinePicker.setValue(project.getDeadline().toLocalDate());
        }
    }

    public void setUser(User u) {
        this.user = u;
    }


    @FXML
    void handleConfirmEdit(ActionEvent event) throws SQLException, IOException {

        String name = nameField.getText();
        String summary = summaryField.getText();
        String type = typeField.getText();
        LocalDate value = deadLinePicker.getValue();
        Date deadline = null;
        if (value != null) {
            deadline = Date.valueOf(value);
        }

        if (user.equals(GlobalFacade.getInstance().getProjectAdmin(project))) {
            int idProject = project.getId();
            Project editProject = new Project(idProject, name, summary, type, deadline);
            boolean success = GlobalFacade.getInstance().editProject(editProject);

            if (success) {
                handleReturn(null);
            } else {
                message.setText("Error editing project");
                message.setVisible(true);
            }

        } else {
            message.setText("Unauthorized action. Ask the administrator project to do this");
            message.setVisible(true);
            return;
        }
    }

    @FXML
    void handleReturn(ActionEvent event) throws IOException, SQLException {
        if (initCalled) {
            getHomeController().changeSubScene("../backlog/Backlog.fxml", null);
        } else {
            Scrum.goToProjectList(user, getClass().getResource("ProjectList.fxml"));
        }
    }

    @FXML
    void handleDelete(ActionEvent actionEvent) throws SQLException, IOException {
        if (!delete) {
            message.setText("Are you sure? This cannot be undone!");
            message.setVisible(true);
            return;
        }

        boolean success = GlobalFacade.getInstance().deleteProject(project);
        if (success) {
            Scrum.goToProjectList(user, getClass().getResource("ProjectList.fxml"));
        } else {
            message.setText("Error deleting project");
            message.setVisible(true);
        }
    }


    @Override
    public void init(Object param) {
        initCalled = true;
    }
}
