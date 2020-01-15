package gui.ticket;


import business.facade.GlobalFacade;
import business.system.Ticket;
import business.system.UserStory;
import gui.main.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class TicketController extends AbstractController {

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

        int idUserStory = currentTicket.getUserStory();
        try {
            UserStory us = GlobalFacade.getInstance().getUserStoryByID(idUserStory);
            if (us != null) {
                userStoryField.setText("on " + us);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        titleField.setText(currentTicket.getName());
        descriptionField.setText(currentTicket.getDescription());
        statusField.setText(currentTicket.getStatusTicket());
    }

    public void handleExit(ActionEvent actionEvent) {
        getHomeController().goToTicketBacklog(null);
    }

    public void handleEditState(ActionEvent actionEvent) throws IOException {
        getHomeController().changeSubScene("../ticket/EditTicketState.fxml", currentTicket);
    }

    public void handleEdit(ActionEvent actionEvent) throws IOException {
        getHomeController().changeSubScene("../ticket/EditTicket.fxml", currentTicket);
    }
}
