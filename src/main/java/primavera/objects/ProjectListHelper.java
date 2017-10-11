package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.network.NetworkException;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectListHelper extends HashMap<Integer, ArrayList<OpenProjectTreeItem>> {

    public ProjectListHelper(Session session) {
        try {
            BOIterator<Project> iterator = session.getGlobalObjectManager().loadProjects(new String[] {
                    "ObjectId", "Name", "Id", "ParentEPSObjectId"},
                    null, "ObjectId");
            while (iterator.hasNext()) {
                OpenProjectTreeItem project = new OpenProjectTreeItem(iterator.next());
                if (this.containsKey(project.getParentId())) {
                    this.get(project.getParentId()).add(project);
                } else {
                    ArrayList<OpenProjectTreeItem> newList = new ArrayList<>();
                    newList.add(project);
                    this.put(project.getParentId(), newList);
                }
            }
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }

}
