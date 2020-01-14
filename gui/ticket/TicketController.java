package gui.ticket;


import business.system.Ticket;
import gui.main.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

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

        titleField.setText(currentTicket.getName());
        descriptionField.setText(currentTicket.getDescription());
        statusField.setText(currentTicket.getStatusTicket());
    }

    public void handleExit(ActionEvent actionEvent) {
        // TODO: 12/01/2020 display ticket backlog
        getHomeController().goToTicketBacklog(null);
    }
}
