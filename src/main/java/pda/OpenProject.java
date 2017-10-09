package pda;

import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.EPS;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import primavera.objects.OpenProjectHelper;

public class OpenProject {

    private OpenProjectHelper openProjectHelper;

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
        openProjectHelper = new OpenProjectHelper();
        TreeItem<String> root = new TreeItem<>("Primavera");
        try {
            addEPSChild(root, 0);
        } catch (BusinessObjectException e) {
            e.printStackTrace();
        }

        projectTree.setRoot(root);


        System.out.println("testInit");
    }

    private void addEPSChild(TreeItem node, Integer id) throws BusinessObjectException {
        if (openProjectHelper.getEpsHelper().containsKey(id)) {
            for (EPS eps : openProjectHelper.getEpsHelper().get(id)) {
                TreeItem<String> child = new TreeItem<String>(eps.getName());
                node.getChildren().add(child);
                addEPSChild(child, eps.getObjectId().toInteger());
            }
        }
    }

    public Integer getProjectId() {
        return 0;
    }
}
