package gui.ticket;

import business.facade.GlobalFacade;
import business.system.Projet;
import business.system.Ticket;
import business.system.User;
import gui.main.AbstractControlleur;
import gui.main.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class CreateTicketController extends AbstractControlleur {

    @FXML
    private ChoiceBox userStoryField;

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Text message;

    public void handleCreationTicket(ActionEvent event) throws IOException {

        String titleTicket = titleField.getText();
        String descriptionTicket = descriptionField.getText();

        boolean success = false;
        Ticket newTicket = new Ticket(-1, titleTicket, descriptionTicket, "Unsolved");

        try {
            success = GlobalFacade.getInstance().addTicket(newTicket, getProjet().getId());
            System.out.println(success);
        } catch (SQLException e) {
            message.setText("Error creating Ticket 1");
            message.setVisible(true);
            return;
        }

        if (success) {
            getHomeControlleur().changeSubScene("../Ticket/TicketController", newTicket);
        } else {
            message.setText("Error creating Ticket 2");
            message.setVisible(true);
            return;
        }
    }

    public void exit() throws IOException {
        // TODO: 12/01/2020 display ticket backlog
        System.out.println("Exit pressed");
        //homeControlleur.changeSubScene("", null);
    }

    @Override
    public void init(Object param) {

    }
}
