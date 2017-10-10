package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.network.NetworkException;

public class ProjectHelper {

    private Project project;

    public ProjectHelper(Session session, Integer objectId) {
        try {
            BOIterator<Project> iterator = session.getGlobalObjectManager().loadProjects(new String[] {
                    "WBSObjectId", "Name", "Id"}, "ObjectId = " + objectId, null);
            while (iterator.hasNext()) project = iterator.next();
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }

    public Project getProject() {
        return project;
    }
}
