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

import java.io.IOException;
import java.sql.SQLException;

public class CreateTicketController extends AbstractController {

    @FXML
    private ChoiceBox<UserStory> userStoryField;

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Text message;

    public void handleCreationTicket(ActionEvent event) throws IOException {

        String titleTicket = titleField.getText();
        String descriptionTicket = descriptionField.getText();

        boolean success ;
        UserStory value = userStoryField.getValue();
        int id = 0;
        if (value != null) {
            id = value.getId();
        }
        Ticket newTicket = new Ticket(-1, titleTicket, descriptionTicket,
                "Unsolved", id);

        try {
            success = GlobalFacade.getInstance().addTicket(newTicket, getProject());
        } catch (SQLException e) {
            e.printStackTrace();
            message.setText("Error creating Ticket");
            message.setVisible(true);
            return;
        }

        if (success) {
            getHomeController().changeSubScene("../Ticket/Ticket.fxml", newTicket);
        } else {
            message.setText("Error creating Ticket");
            message.setVisible(true);
        }
    }

    public void exit() throws IOException {
        getHomeController().goToTicketBacklog(null);
    }


    @Override
    public void init(Object param) {
        UserStory[] us;
        try {
            us = GlobalFacade.getInstance().getUserStoryByProject(getProject());
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        ObservableList<UserStory> items = userStoryField.getItems();
        items.addAll(us);
//        items.add(null);
    }
}
