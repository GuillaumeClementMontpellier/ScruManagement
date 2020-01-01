package gui.userstory;

import business.facade.GlobalFacade;
import business.system.Projet;
import business.system.User;
import business.system.UserStory;
import gui.main.HomeController;
import gui.main.MainControlleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

public class NewUserStoryCreation implements MainControlleur {

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

    private Projet projet;

    private User actveUser;

    private HomeController homeControlleur;

    @FXML
    void handleCreate(ActionEvent event) throws IOException {

        String name = nomField.getText();
        String descr = descrField.getText();
        int score = Integer.parseInt(scoreField.getText());

        Date deadline = Date.valueOf(datePicker.getValue());

        boolean success = false;
        UserStory newUS = new UserStory(-1, name, descr, score, deadline);

        try {
            success = GlobalFacade.getInstance()
                    .addUserStory(newUS,
                            projet.getId());
        } catch (SQLException e) {
            message.setText("Error adding User Story");
            message.setVisible(true);
            return;
        }

        if (success) {
            homeControlleur.changeSubScene("../userstory/UserStoryController", newUS);
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
    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public void setUser(User user) {
        this.actveUser = user;
    }

    @Override
    public void setHomeControlleur(HomeController homeControlleur) {
        this.homeControlleur = homeControlleur;
    }

    @Override
    public void init(Object param) {

    }
}