package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.client.bo.object.WBS;
import com.primavera.integration.network.NetworkException;

import java.util.ArrayList;
import java.util.HashMap;

public class WBSHelper extends HashMap<Integer, ArrayList<WBS>> {

    private HashMap<Integer, WBS> wbsList = new HashMap<>();

    public WBSHelper(Project project) {
        try {
            BOIterator<WBS> iterator = project.loadAllWBS(new String[] {
                    "Code", "ObjectId", "ParentObjectId", "Name"
            }, null, "SequenceNumber");
            while (iterator.hasNext()) {
                WBS wbs = iterator.next();
                wbsList.put(wbs.getObjectId().toInteger(), wbs);
                if (this.containsKey(wbs.getParentObjectId().toInteger())) {
                    this.get(wbs.getParentObjectId().toInteger()).add(wbs);
                } else {
                    ArrayList<WBS> tempList = new ArrayList<>();
                    tempList.add(wbs);
                    this.put(wbs.getParentObjectId().toInteger(), tempList);
                }
            }
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }
}
