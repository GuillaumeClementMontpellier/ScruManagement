package gui.project;

import business.facade.GlobalFacade;
import business.system.Collaborator;
import business.system.Projet;
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
        String role = "Scrum Master";

        int idUser = user.getId();

        int idRole;
        if (role.equals("Scrum Master")) {
            idRole = 2;
        } else if (role.equals("Product Owner")) {
            idRole = 3;
        } else {
            idRole = 4;
        }

        if (name != null) {
            try {
                Projet newProject = GlobalFacade.getInstance().createProject(name, summary, type, deadline);
                int idProject = newProject.getId();

                Collaborator collaborator = GlobalFacade.getInstance().addCollaborator(idProject, idUser, idRole, true);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("../main/Home.fxml"));

                Parent root = loader.load();
                HomeController cont = loader.<HomeController>getController();

                cont.setProject(newProject);
                cont.setUser(user);

                Scene scene = new Scene(root);
                Scrum.getStage().setScene(scene);

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
