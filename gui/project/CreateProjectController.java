package gui.project;

import business.facade.GlobalFacade;
import business.system.Project;
import business.system.User;
import gui.main.HomeController;
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
import java.time.LocalDate;


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

        LocalDate value = deadLinePicker.getValue();
        Date deadline = null;
        if (value != null) {
            deadline = Date.valueOf(value);
        }


        if (name != null) {
            try {
                Project newProject = GlobalFacade.getInstance().createProject(name, summary, type, deadline, user);
                Scrum.goToMainScreen(user, newProject,getClass().getResource("../main/Home.fxml") );

            } catch (SQLException sql) {
                sql.printStackTrace();
                message.setText("Database error, try later");
                message.setVisible(true);
                return;
            }
        } else {
            message.setText("Name must be filled");
            message.setVisible(true);
            return;
        }

    }

    @FXML
    void handleReturn(ActionEvent event) throws SQLException, IOException {
        Scrum.goToProjectList(user, getClass().getResource("ProjectList.fxml"));
    }


}
