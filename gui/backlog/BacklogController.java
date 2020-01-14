package gui.backlog;

import business.facade.BacklogFacade;
import business.system.Backlog;
import business.system.Column;
import business.system.Component;
import business.system.ProductBacklog;
import gui.main.AbstractController;
import gui.project.ProjectController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.SQLException;

public class BacklogController extends AbstractController {
    @FXML
    private GridPane tab;

    private BacklogFacade bFacade = new BacklogFacade();

    private Backlog backlog = null;
    private Column[] allColumn = null;

    @Override
    public void init(Object param) {
        try {
            this.backlog =  bFacade.getProductBacklog(this.getProject());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.allColumn = this.bFacade.getColumn(this.backlog);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String label;
        for (int i = 0; i < allColumn.length; i++) {
            label = allColumn[i].getName();
            System.out.println(label);


            FXMLLoader loader = new FXMLLoader(getClass().getResource("RowTitle.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            RowTitleController rtc = loader.<RowTitleController>getController();
            rtc.setLabel(label);
            this.tab.add(root,i,0);
        }
    }

}
