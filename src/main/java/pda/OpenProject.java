package pda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import primavera.objects.OpenProjectHelper;

public class OpenProject {
    @FXML
    public TreeView projectTree;

    public void openProject(ActionEvent actionEvent) {

    }

    public void close(ActionEvent actionEvent) {

    }

    public void clickProject(MouseEvent mouseEvent) {

    }

    @FXML
    public void initialize() {
        OpenProjectHelper openProjectHelper = new OpenProjectHelper();
        TreeItem<String> root = new TreeItem<>("Primavera");
        addEPSChild(root);


        System.out.println("testInit");
    }

    private void addEPSChild(TreeItem node) {

    }

    public Integer getProjectId() {
        return 0;
    }
}
