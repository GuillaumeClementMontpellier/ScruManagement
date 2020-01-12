package gui.ticket;

import business.facade.GlobalFacade;
import business.system.Projet;
import business.system.User;
import business.system.Ticket;
import gui.main.HomeController;
import gui.main.MainControlleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

public class CreateTicketController implements MainControlleur {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    private Projet currentProject;
    private User currentUser;
    private HomeController homeControlleur;

    @FXML
    void handleCreationTicket(ActionEvent event) throws IOException {

        String titleTicket = titleField.getText();
        String descriptionTicket = descriptionField.getText();

        boolean success = false;
        Ticket newTicket = new Ticket(-1, titleTicket, descriptionTicket, "Unsolved");

        try {
            success = GlobalFacade.getInstance()
                    .addTicket(newTicket,
                            currentProject.getId());
        } catch (SQLException e) {
            //message.setText("Error adding User Story");
            //message.setVisible(true);
            return;
        }

        if (success) {
            homeControlleur.changeSubScene("../main/HomeController", newTicket);
        } else {
            //message.setText("Error adding User Story");
            //message.setVisible(true);
        }
    }

    public void exit() throws IOException {
        // TODO : goto Ticket Backlog
        System.out.println("Exit pressed");
        //homeControlleur.changeSubScene("", );
    }

    @Override
    public void setProjet(Projet projet) {
        this.currentProject = projet;
    }

    @Override
    public void setUser(User user) {
        this.currentUser = user;
    }

    @Override
    public void setHomeControlleur(HomeController homeControlleur) {
        this.homeControlleur = homeControlleur;
    }

    @Override
    public void init(Object param) {

    }
}
