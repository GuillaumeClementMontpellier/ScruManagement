package main;

import DAO.factory.AbstractFactoryDAO;
import DAO.mariadb.FactoryDAOMariaDB;
import business.system.Project;
import business.system.User;
import gui.main.HomeController;
import gui.project.CreateProjectController;
import gui.project.EditProjectController;
import gui.project.ProjectListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;

public class Scrum extends Application {

    private static Stage stageSingleton = null;

    public static void setScene(URL resource) throws IOException {
        Parent root = FXMLLoader.load(resource);

        Scene scene = new Scene(root);
        Scrum.stageSingleton.setScene(scene);
    }

    public static void goToMainScreen(User user, Project project, URL resource) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(resource);

        Parent root = loader.load();

        HomeController cont = loader.<HomeController>getController();

        cont.setUser(user);
        cont.setProject(project);

        Scene scene = new Scene(root);
        Scrum.stageSingleton.setScene(scene);

    }

    public static void goToProjectList(User user, URL resource) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(resource);

        Parent root = loader.load();

        ProjectListController cont = loader.<ProjectListController>getController();

        cont.setUser(user);

        Scene scene = new Scene(root);
        Scrum.stageSingleton.setScene(scene);

    }

    public static void goToEditProject(User user, Project project, URL resource) throws IOException {
        FXMLLoader loader = new FXMLLoader(resource);

        Parent root = loader.load();
        EditProjectController cont = loader.<EditProjectController>getController();

        cont.setProject(project);
        cont.setUser(user);

        Scene scene = new Scene(root);
        Scrum.stageSingleton.setScene(scene);
    }
    public static void goToProjectCreation(User user, URL resource) throws IOException {

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();

        CreateProjectController cont = loader.<CreateProjectController>getController();
        cont.setUser(user);

        Scene scene = new Scene(root);
        Scrum.stageSingleton.setScene(scene);

    }

    public static void main(String[] args) {
        Map<String, String> env = System.getenv();

        //parse java.env var
        String url = env.get("DATA_BASE");
        String dbUser = env.get("USER");
        String dbPwd = env.get("PASSWORD");

        //create factory and add it to facade
        AbstractFactoryDAO.setInstance(new FactoryDAOMariaDB(url, dbUser, dbPwd));

        //start
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);

        stageSingleton = stage;
        stage.setTitle("ScruManagement");

        //load fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/login/Login.fxml"));
        Parent root = loader.load();

        //display fxml
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
