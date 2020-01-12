package gui.userstory;

import business.facade.GlobalFacade;
import business.system.UserStory;
import gui.main.AbstractControlleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class NewUserStoryCreation extends AbstractControlleur {

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

    @FXML
    void handleCreate(ActionEvent event) throws IOException {

        String name = nomField.getText();
        String descr = descrField.getText();
        int score = Integer.parseInt(scoreField.getText());

        Date deadline = Date.valueOf(datePicker.getValue());

        boolean success = false;
        UserStory newUS = new UserStory(-1, name, descr, score, deadline);

        try {
            success = GlobalFacade.getInstance().addUserStory(newUS, getProjet());
        } catch (SQLException e) {
            message.setText("Error adding User Story");
            message.setVisible(true);
            return;
        }

        if (success) {
            getHomeControlleur().changeSubScene("../userstory/UserStoryController", newUS);
        } else {
            message.setText("Error adding User Story");
            message.setVisible(true);
        }
    }

    public void exit() throws IOException {
        // TODO : goto US Backlog
        System.out.println("Exit pressed");
//        homeControlleur.changeSubScene("../userstory/UserStoryController", );
    }

    @Override
    public void init(Object param) {

    }
}