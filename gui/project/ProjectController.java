package gui.project;

import business.facade.GlobalFacade;
import business.system.Project;
import business.system.User;
import javafx.fxml.FXML;
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
    void loadProject() throws IOException, SQLException {
        Scrum.goToMainScreen(user, project, getClass().getResource("../main/Home.fxml"));
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

            Scrum.goToEditProject(user, project, getClass().getResource("../project/EditProject.fxml"));

        } else {
            message.setText("Unauthorized action. Ask the administrator project to do this");
            message.setVisible(true);
            return;
        }
    }
}
