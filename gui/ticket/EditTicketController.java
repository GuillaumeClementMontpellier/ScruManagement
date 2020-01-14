package gui.ticket;

import business.facade.GlobalFacade;
import business.system.Ticket;
import business.system.UserStory;
import gui.main.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class EditTicketController extends AbstractController {


    @FXML
    private TextArea descrField;

    @FXML
    private Text message;

    @FXML
    private TextField titleField;

    @FXML
    private ChoiceBox<UserStory> userStoryChoice;

    private Ticket currentTicket;
    private boolean delete;

    @FXML
    void handleDelete(ActionEvent event) throws SQLException {
        if (!delete) {
            delete = true;
            message.setText("Are you Sure ?");
            message.setVisible(true);
            return;
        }

        boolean success = GlobalFacade.getInstance().deleteTicket(currentTicket);
        if (!success) {
            message.setText("Error deleting Ticket");
            message.setVisible(true);
        } else {
            handleReturn(null);
        }
    }

    @FXML
    void handleEdit(ActionEvent event) throws SQLException {
        Ticket newTicket = new Ticket(currentTicket.getId(), currentTicket.getName(), descrField.getText(),
                currentTicket.getStatusTicket(), userStoryChoice.getValue().getId());
        boolean success = GlobalFacade.getInstance().updateTicket(newTicket, currentTicket);
        if (!success) {
            message.setText("Error editing Ticket");
            message.setVisible(true);

        } else {
            handleReturn(null);
        }
    }

    @FXML
    void handleReturn(ActionEvent event) {

        getHomeController().goToTicketBacklog(null);
    }

    @Override
    public void init(Object param) {

        currentTicket = (Ticket) param;

        descrField.setText(currentTicket.getDescription());
        titleField.setText(currentTicket.getName());
        message.setVisible(false);
        delete = false;
        // Todo : choices of state and User Story Box
    }
}
