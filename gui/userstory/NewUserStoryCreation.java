package gui.userstory;

import business.facade.GlobalFacade;
import business.system.UserStory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import main.Scrum;

import java.sql.Date;
import java.sql.SQLException;

public class NewUserStoryCreation {

    @FXML
    private TextField nomField;

    @FXML
    private TextArea descrField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField scoreField;

    @FXML
    private Text message;

    private int projectID;

    @FXML
    void handleCreate(ActionEvent event) {

        String name = nomField.getText();
        String descr = descrField.getText();
        int score = Integer.parseInt(scoreField.getText());

        Date deadline = Date.valueOf(datePicker.getValue());

        boolean success = false;

        try {
            success = GlobalFacade.getInstance().addUserStory(new UserStory(-1, name, descr, projectID, score, deadline));
        } catch (SQLException e) {
            message.setText("Error adding User Story");
            message.setVisible(true);
            return;
        }

        if (success) {
            exit();
        } else {
            message.setText("Error adding User Story");
            message.setVisible(true);
        }

    }

    public void exit() {
        System.out.println("Exit pressed");
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }
}