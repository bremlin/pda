package pda;

import com.primavera.PrimaveraException;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Activity;
import com.primavera.integration.client.bo.object.WBS;
import javafx.scene.control.TreeItem;
import pda.objects.PdaTreeItem;
import primavera.connect.PrimConnect;
import primavera.objects.ActivityHelper;
import primavera.objects.ProjectHelper;
import primavera.objects.WBSHelper;

public class DataProjectHelper {

    ProjectHelper projectHelper;
    WBSHelper wbsHelper;
    ActivityHelper activityHelper;

    public DataProjectHelper(Integer projectObjectId) {
        System.out.println("DataProjectHelper - Start");
        PrimConnect primConnect = new PrimConnect();
        try {
            primConnect.login();

            projectHelper = new ProjectHelper(PrimConnect.sessionAdmin, projectObjectId);
            wbsHelper = new WBSHelper(projectHelper.getProject());
            activityHelper = new ActivityHelper(projectHelper.getProject());

        } catch (PrimaveraException e) {
            e.printStackTrace();
        }
    }

    public void setStructureData(TreeItem root, Integer rootId) {
        try {
            setWBSData(root, rootId);
        } catch (BusinessObjectException e) {
            e.printStackTrace();
        }
    }

    private void setWBSData(TreeItem parent, Integer parentId) throws BusinessObjectException {
        if (wbsHelper.containsKey(parentId)) {
            for (WBS wbs : wbsHelper.get(parentId)) {
                TreeItem<PdaTreeItem> node = new TreeItem<>(new PdaTreeItem(wbs));
                parent.getChildren().add(node);
                setActivityData(node, wbs.getObjectId().toInteger());
                setWBSData(node, wbs.getObjectId().toInteger());
            }
        }
    }

    private void setActivityData(TreeItem parent, Integer parentId) throws BusinessObjectException {
        if (activityHelper.containsKey(parentId)) {
            for (Activity activity : activityHelper.get(parentId)) {
                TreeItem<PdaTreeItem> node = new TreeItem<>(new PdaTreeItem(activity));
                parent.getChildren().add(node);
            }
        }
    }

    public ProjectHelper getProjectHelper() {
        return projectHelper;
    }

    public WBSHelper getWbsHelper() {
        return wbsHelper;
    }

    public ActivityHelper getActivityHelper() {
        return activityHelper;
    }
}
