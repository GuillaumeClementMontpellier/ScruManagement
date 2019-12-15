import DAO.factory.AbstractFactoryDAO;
import DAO.mariadb.FactoryDAOMariaDB;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.login.LoginLayout;

public class Scrum extends Application {

    public static void main(String[] args) {
        //parse java.env var
        String url = "";
        String dbUser = "";
        String dbPwd = "";

        //create factory and add it to facade
        AbstractFactoryDAO.setInstance(new FactoryDAOMariaDB(url, dbUser, dbPwd));

        //start
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Scrum Welcome");

        // Setup
        Scene scene = new Scene(new LoginLayout(primaryStage), 300, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
