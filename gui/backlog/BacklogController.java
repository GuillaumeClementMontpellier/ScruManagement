package gui.backlog;

import business.facade.BacklogFacade;
import business.facade.GlobalFacade;
import business.system.Backlog;
import business.system.Column;
import business.system.Component;
import business.system.UserStory;
import gui.main.AbstractController;
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
    private String type;

    @Override
    public void init(Object param) {

        this.type = (String) param;

        try {
            this.backlog = bFacade.getProductBacklog(this.getProject());
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            this.allColumn = this.bFacade.getColumn(this.backlog);
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String label;
        for (int i = 0; i < allColumn.length; i++) {

            label = allColumn[i].getName();
//            System.out.println(label);

            FXMLLoader loaderTitle = new FXMLLoader(getClass().getResource("RowTitle.fxml"));
            Parent root;
            try {
                root = loaderTitle.load();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            RowTitleController rtc = loaderTitle.<RowTitleController>getController();
            rtc.setComponent(label);
            this.tab.add(root, i, 0);

            Component[] allUserStory;
            try {
                if (type.equals("Product") || type.equals("Sprint")) {
                    allUserStory = GlobalFacade.getInstance().getUserStory(allColumn[i]);
                } else {
                    allUserStory = GlobalFacade.getInstance().getTickets(allColumn[i]);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                continue;
            }

            for (int j = 0; j < allUserStory.length; j++) {
                Component us = allUserStory[j];
                FXMLLoader loaderRow = new FXMLLoader(getClass().getResource("Row.fxml"));

                try {
                    root = loaderRow.load();
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }

                RowController rc = loaderRow.<RowController>getController();
                rc.setComponent(us);
                rc.setController(getHomeController());

                this.tab.add(root, i, j + 1);
            }

        }
    }

}
