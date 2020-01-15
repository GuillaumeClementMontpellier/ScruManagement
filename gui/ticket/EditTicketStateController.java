package gui.ticket;

import business.facade.GlobalFacade;
import business.system.Ticket;
import business.system.UserStory;
import gui.main.AbstractController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class EditTicketStateController extends AbstractController {


    private Ticket currentTicket;
    private boolean delete;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Text message;
    @FXML
    private TextField stateField;
    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<UserStory> userStoryBox;
    @FXML
    private ComboBox<String> stateBox;

    @Override
    public void init(Object param) {
        currentTicket = (Ticket) param;

        descriptionField.setText(currentTicket.getDescription());
        stateField.setText(currentTicket.getStatusTicket());
        titleField.setText(currentTicket.getName());
        message.setText("");
        delete = false;

        UserStory userSto;
        try {
            userSto = GlobalFacade.getInstance().getUserStoryByID(currentTicket.getUserStory());
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        userStoryBox.setValue(userSto);

        stateField.setText(currentTicket.getStatusTicket());

        stateBox.getItems().addAll(Ticket.getStatus());
    }


    @FXML
    void handleEdit(ActionEvent event) throws SQLException {
        Ticket newTicket = new Ticket(currentTicket.getId(), currentTicket.getName(),
                descriptionField.getText(), stateBox.getValue(), userStoryBox.getValue().getId());
        boolean success = GlobalFacade.getInstance().updateTicket(newTicket, currentTicket);
        if (!success) {
            message.setText("Error editing Ticket");
        } else {
            handleReturn(null);
        }
    }

    @FXML
    void handleReturn(ActionEvent event) {
        getHomeController().goToTicketBacklog(null);
    }


}
