package gui.project;

import business.facade.GlobalFacade;
import business.system.*;
import gui.main.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Scrum;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;


public class CreateProjectController extends AbstractController {

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

    @FXML
    private TextField roleField;

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
        String role = roleField.getText();

        int idUser = user.getId();
        int idRole;
        if (role == "Scrum Master") {
            idRole = 2;
        }
        else if (role == "Product Owner") {
            idRole = 3;
        }
        else {
            idRole = 4;
        }

        if (name != null) {
            try {
                Project newProject = GlobalFacade.getInstance().createProject(name, summary, type, deadline);
                int idProject = newProject.getId();
                this.setProject(newProject);

                Collaborator collaborator = GlobalFacade.getInstance().addCollaborator(idProject, idUser, idRole, true);

                Parent root = FXMLLoader.load(getClass().getResource("../project/Project.fxml"));
            }
            catch (SQLException sql) {
                message.setText("Database error, try later");
                message.setVisible(true);
                return;
            }
        }
        else {
            message.setText("Name must be filled");
            message.setVisible(true);
            return;
        }

    }

    @FXML
    void handleReturn(ActionEvent event) throws SQLException, IOException {
        Scrum.goToProjectList(user, getClass().getResource("ProjectList.fxml"));
    }

    @Override
    public void init(Object param) {

    }

}
