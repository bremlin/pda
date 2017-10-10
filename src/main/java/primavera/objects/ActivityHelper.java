package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Activity;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.network.NetworkException;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityHelper extends HashMap<Integer, ArrayList<Activity>> {

    private HashMap<Integer, Activity> activityMap = new HashMap<>();

    public ActivityHelper(Project project) {
        try {
            BOIterator<Activity> iterator = project.loadAllActivities(new String[] {
                    "ActualFinishDate", "ActualStartDate", "BaselineStartDate", "FinishDate", "StartDate", "Id", "Name",
                    "ObjectId", "PercentComplete", "PlannedFinishDate", "PlannedStartDate", "Status", "Type", "WBSObjectId"
            }, null, null);
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                activityMap.put(activity.getObjectId().toInteger(), activity);
                if (this.containsKey(activity.getWBSObjectId().toInteger())) {
                    this.get(activity.getWBSObjectId().toInteger()).add(activity);
                } else {
                    ArrayList<Activity> tempList = new ArrayList<>();
                    tempList.add(activity);
                    this.put(activity.getWBSObjectId().toInteger(), tempList);
                }
            }
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }
}
