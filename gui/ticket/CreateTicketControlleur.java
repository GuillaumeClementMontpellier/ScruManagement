package gui.ticket;

import gui.main.AbstractControlleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class CreateTicketControlleur extends AbstractControlleur {

    @FXML
    private TextArea descrField;

    @FXML
    private ChoiceBox<?> UserStoryChoice;

    @FXML
    private Text message;

    @FXML
    void handleCreateTicket(ActionEvent event) {
        System.out.println("CreateTicketControlleur.handleCreateTicket");
    }

    @FXML
    void handleReturn(ActionEvent event) {
        System.out.println("CreateTicketControlleur.handleReturn");
    }

    @Override
    public void init(Object param) {
        message.setVisible(false);
    }
}
