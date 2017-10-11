package pda;

import com.primavera.integration.client.bo.BusinessObjectException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.stage.Stage;
import pda.objects.ItemType;
import pda.objects.PdaTreeItem;

import java.io.IOException;

public class Controller {
    @FXML
    public TreeTableView structureTable;
    @FXML
    public TreeTableView factMoreThenPlanTable;
    @FXML
    public TreeTableView factLessThenPlanCompleteTable;
    @FXML
    public TreeTableView notEqualsStartDateTable;
    @FXML
    TreeTableColumn<PdaTreeItem, String> idColumn;
    @FXML
    TreeTableColumn<PdaTreeItem, String> nameColumn;
    @FXML
    TreeTableColumn<PdaTreeItem, String> startColumn;
    @FXML
    TreeTableColumn<PdaTreeItem, String> finishColumn;
    @FXML
    TreeTableColumn<PdaTreeItem, String> planStartColumn;
    @FXML
    TreeTableColumn<PdaTreeItem, String> planFinishColumn;

    DataProjectHelper dataProjectHelper;

    @FXML
    public Tab projectStructure;
    @FXML
    public Tab factMoreThenPlan;
    @FXML
    public Tab factLessThenPlanComplete;
    @FXML
    public Tab notEqualsStartDate;

    public void fix(ActionEvent actionEvent) {

    }

    public void fixAll(ActionEvent actionEvent) {

    }

    public void openProject(ActionEvent actionEvent) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/openProject.fxml"));
            loader.load();
            OpenProject openProject = loader.getController();

            root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Выберите проект");
            stage.setScene(new Scene(root, 900, 800));
            stage.showAndWait();

            if (openProject.getProjectId() > 0) fillDataProject(openProject.getProjectId());
            columnSettings();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillDataProject(Integer projectObjectId) {
        dataProjectHelper = new DataProjectHelper(projectObjectId);
        TreeItem<PdaTreeItem> root = new TreeItem<>(new PdaTreeItem(dataProjectHelper.getProjectHelper().getProject()));
        try {
            dataProjectHelper.setStructureData(root, dataProjectHelper.getProjectHelper().getProject().getWBSObjectId().toInteger());
        } catch (BusinessObjectException e) {
            e.printStackTrace();
        }
        structureTable.setRoot(root);
    }

    public void openProjectStructure(Event event) {

    }

    public void openFactMoreThenPlan(Event event) {

    }

    public void openFactLessThenPlanComplete(Event event) {

    }

    public void openNotEqualsStartDate(Event event) {

    }

    private void columnSettings() {
        idColumn.setCellValueFactory(param -> param.getValue().getValue().getSimpleId());
        nameColumn.setCellValueFactory(param -> param.getValue().getValue().getSimpleName());
        startColumn.setCellValueFactory(param -> param.getValue().getValue().getSimpleStartDate());
        finishColumn.setCellValueFactory(param -> param.getValue().getValue().getSimpleFinishDate());
//        startColumn.setCellValueFactory(param -> param.getValue().getValue().getStartDate());
//        finishColumn.setCellValueFactory(param -> param.getValue().getValue().getFinishDate());

//        percentComplete.setOnEditCommit(event -> {
//            TreeItem<TreeTableElement> currentEditStep = treeTableView.getTreeItem(event.getTreeTablePosition().getRow());
//            if (currentEditStep.getValue().getElementType() == ItemType.Step) {
//                currentEditStep.getValue().setPercent(event.getNewValue());
//                sqlController.insertPercentComplete(currentEditStep.getValue().getObjectId(), event.getNewValue());
//            }
//        });

        structureTable.setRowFactory(tv -> new TreeTableRow<PdaTreeItem>() {
            @Override
            public void updateItem(PdaTreeItem item, boolean empty) {
                super.updateItem(item, empty) ;
                if (item == null) {
                    addStyles(null, getStyleClass());
                } else {
                    addStyles(item.getType(), getStyleClass());
                }
            }
        });
    }

    private void addStyles(ItemType type, ObservableList<String> styleClass) {
        styleClass.clear();
        styleClass.add("cell");
        styleClass.add("indexed-cell");
        styleClass.add("tree-table-row-cell");
        if (type!= null) styleClass.add(type.getStyle());
    }
}
