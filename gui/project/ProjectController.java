package gui.project;

import business.facade.GlobalFacade;
import business.system.Project;
import business.system.User;
import gui.main.HomeController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import main.Scrum;

import java.io.IOException;
import java.sql.SQLException;

public class ProjectController {

    private Project project;
    private User user;

    @FXML
    private Text projectName;

    @FXML
    private Text message;

    @FXML
    void loadProject() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../main/Home.fxml"));

        Parent root = loader.load();
        HomeController cont = loader.<HomeController>getController();

        cont.setProject(project);
        cont.setUser(user);

        Scene scene = new Scene(root);
        Scrum.getStage().setScene(scene);

    }

    public void setProject(Project p) {
        this.project = p;
        this.projectName.setText(p.getName());
    }

    public void setUser(User u) {
        this.user = u;
    }

    public void editProject(MouseEvent mouseEvent) throws IOException, SQLException {
        if (user.equals(GlobalFacade.getInstance().getProjectAdmin(project))) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProject.fxml"));

            Parent root = loader.load();
            EditProjectController cont = loader.<EditProjectController>getController();

            cont.setProject(project);
            cont.setUser(user);

            Scene scene = new Scene(root);
            Scrum.getStage().setScene(scene);
        } else {
            message.setText("Unauthorized action. Ask the administrator project to do this");
            message.setVisible(true);
            return;
        }
    }
}
