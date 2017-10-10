package pda;

import com.primavera.PrimaveraException;
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
