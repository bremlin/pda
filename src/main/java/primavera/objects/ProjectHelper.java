package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.network.NetworkException;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectHelper extends HashMap<Integer, ArrayList<PdaTreeItem>> {

    public ProjectHelper(Session session) {
        try {
            BOIterator<Project> iterator = session.getGlobalObjectManager().loadProjects(new String[] {
                    "ObjectId", "Name", "Id", "ParentEPSObjectId"},
                    null, "ObjectId");
            while (iterator.hasNext()) {
                PdaTreeItem project = new PdaTreeItem(iterator.next());
                if (this.containsKey(project.getParentId())) {
                    this.get(project.getParentId()).add(project);
                } else {
                    ArrayList<PdaTreeItem> newList = new ArrayList<>();
                    newList.add(project);
                    this.put(project.getParentId(), newList);
                }
            }
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }

}
