package gui.ticket;


import business.facade.GlobalFacade;
import business.system.Projet;
import business.system.Ticket;
import business.system.User;
import business.system.UserStory;
import gui.main.AbstractControlleur;
import gui.main.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class TicketController extends AbstractControlleur {

    @FXML
    public Text titleField;

    @FXML
    public Text userStoryField;

    @FXML
    public Text descriptionField;

    @FXML
    public Text statusField;

    private Ticket currentTicket;

    @Override
    public void init(Object param) {

        this.currentTicket = (Ticket) param;

        titleField.setText(currentTicket.getTitleTicket());
        descriptionField.setText(currentTicket.getDescriptionTicket());
        statusField.setText(currentTicket.getStatusTicket());
    }

    public void handleExit(ActionEvent actionEvent) {
        // TODO: 12/01/2020 display ticket backlog
        System.out.println("Exit pressed");
        //homeControlleur.changeSubScene("", null);
    }
}
