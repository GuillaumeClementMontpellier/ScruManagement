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

        boolean success = false;
        Ticket newTicket = new Ticket(-1, titleTicket, descriptionTicket,
                "Unsolved", userStoryField.getValue().getId());

        try {

            success = GlobalFacade.getInstance().addTicket(newTicket, getProject());

        } catch (SQLException e) {
            message.setText("Error creating Ticket 1");
            message.setVisible(true);
            return;
        }

        if (success) {
            getHomeController().changeSubScene("../Ticket/TicketController", newTicket);
        } else {
            message.setText("Error creating Ticket 2");
            message.setVisible(true);
            return;
        }
    }

    public void exit() throws IOException {
        getHomeController().goToTicketBacklog(null);
    }


    @Override
    public void init(Object param) {
// TODO : get all UserStories by Project
    }
}
