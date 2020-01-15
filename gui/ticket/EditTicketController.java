package gui.ticket;

import business.facade.GlobalFacade;
import business.system.Ticket;
import business.system.UserStory;
import gui.main.AbstractController;
import javafx.collections.ObservableList;
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
        UserStory value = userStoryChoice.getValue();
        int id = 0;
        if (value != null) {
            id = value.getId();
        }
        Ticket newTicket = new Ticket(currentTicket.getId(), titleField.getText(), descrField.getText(),
                currentTicket.getStatusTicket(), id);
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

        UserStory[] us;
        try {
            us = GlobalFacade.getInstance().getUserStoryByProject(getProject());
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        ObservableList<UserStory> items = userStoryChoice.getItems();
        items.addAll(us);
//        items.add(null);

        UserStory userSto;
        try {
            userSto = GlobalFacade.getInstance().getUserStoryByID(currentTicket.getUserStory());
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        userStoryChoice.setValue(userSto);

    }
}
