package pda;

import com.primavera.integration.client.bo.BusinessObjectException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import primavera.objects.OpenProjectHelper;
import primavera.objects.OpenProjectTreeItem;

import java.net.URL;
import java.util.ResourceBundle;

public class OpenProject implements Initializable {

    private Integer selectProjectId = 0;

    private OpenProjectHelper openProjectHelper;
    @FXML
    public TreeView projectTree;

    public void openProject(ActionEvent actionEvent) {
        setAndClose(actionEvent);
    }

    public void close(Event actionEvent) {
        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void clickProject(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() > 1) {
            setAndClose(mouseEvent);
        }
    }

    private void setAndClose(Event event) {
        TreeItem<OpenProjectTreeItem> selectedItem = (TreeItem<OpenProjectTreeItem>) projectTree.getSelectionModel().getSelectedItem();
        if (selectedItem.getValue().getType() == 1) {
            selectProjectId = selectedItem.getValue().getObjectId();
            close(event);
        }
    }

    private void addEPSChild(TreeItem node, Integer id) throws BusinessObjectException {
        Image epsImage = new Image(OpenProject.class.getResourceAsStream("../images/EPS.gif"));
        if (openProjectHelper.getEpsHelper().containsKey(id)) {
            for (OpenProjectTreeItem eps : openProjectHelper.getEpsHelper().get(id)) {
                TreeItem<OpenProjectTreeItem> child = new TreeItem<>(eps, new ImageView(epsImage));
                node.getChildren().add(child);
                addProject(child, eps.getObjectId());
                addEPSChild(child, eps.getObjectId());
            }
        }
    }

    private void addProject(TreeItem node, Integer id) throws BusinessObjectException {
        Image projectImage = new Image(OpenProject.class.getResourceAsStream("../images/project.gif"));
        if (openProjectHelper.getProjectListHelper().containsKey(id)) {
            for (OpenProjectTreeItem project : openProjectHelper.getProjectListHelper().get(id)) {
                TreeItem<OpenProjectTreeItem> child = new TreeItem<>(project, new ImageView(projectImage));
                node.getChildren().add(child);
            }
        }
    }

    Integer getProjectId() {
        return selectProjectId;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        openProjectHelper = new OpenProjectHelper();
        TreeItem<String> root = new TreeItem<>("Primavera");
        try {
            addEPSChild(root, 0);
        } catch (BusinessObjectException e) {
            e.printStackTrace();
        }

        projectTree.setRoot(root);
    }
}
