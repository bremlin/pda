package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.network.NetworkException;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectHelper extends HashMap<Integer, ArrayList<Project>> {

    public ProjectHelper(Session session) {
        try {
            BOIterator<Project> iterator = session.getGlobalObjectManager().loadProjects(new String[] {
                    "ObjectId", "Name", "Id", "ParentEPSObjectId"},
                    null, "ObjectId");
            while (iterator.hasNext()) {
                Project project = iterator.next();
                if (this.containsKey(project.getParentEPSObjectId().toInteger())) {
                    this.get(project.getParentEPSObjectId().toInteger()).add(project);
                } else {
                    ArrayList<Project> newList = new ArrayList<>();
                    newList.add(project);
                    this.put(project.getParentEPSObjectId().toInteger(), newList);
                }
            }
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Project> getProjectsByEPS(Integer epsId) {
        return this.get(epsId);
    }
}
