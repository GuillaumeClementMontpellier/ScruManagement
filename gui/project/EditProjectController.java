package gui.project;

import business.facade.GlobalFacade;
import business.system.Project;
import business.system.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Date;


public class EditProjectController {

    @FXML
    private Text message;

    @FXML
    private TextArea summaryField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField typeField;

    @FXML
    private DatePicker deadLinePicker;

    private User user;

    private Project project;

    private boolean delete;

    public void setProject(Project project) {
        this.project = project;
        this.delete = false;
        nameField.setText(project.getName());
        summaryField.setText(project.getSummary());
        typeField.setText(project.getType());
        deadLinePicker.setValue(project.getDeadline().toLocalDate());
    }

    public void setUser(User u) {
        this.user = u;
    }

    @FXML
    void handleConfirm(ActionEvent event) {
        System.out.println("EditProjectController.handleConfirm");

        String name = nameField.getText();
        String summary = summaryField.getText();
        String type = typeField.getText();
        Date deadline = Date.valueOf(deadLinePicker.getValue());

        Project project = new Project(-1, name, summary, type, deadline);

        GlobalFacade.getInstance().addProject(project, user);
    }

    @FXML
    void handleReturn(ActionEvent event) {
        System.out.println("EditProjectController.handleReturn");
    }

    @FXML
    void handleDelete(ActionEvent actionEvent) {
        if (!delete) {
            message.setText("Are you sure ? this cannot be undone !");
            message.setVisible(true);
            return;
        }

        // TODO : delete Project
        System.out.println("EditProjectController.handleDelete");

    }
}
