package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.EPS;
import com.primavera.integration.network.NetworkException;

import java.util.ArrayList;
import java.util.HashMap;

public class EPSHelper extends HashMap<Integer, ArrayList<OpenProjectTreeItem>> {

    public EPSHelper(Session session) {
        try {
            BOIterator<EPS> iterator = session.getGlobalObjectManager().loadEPS(new String[] {
                    "ObjectId", "Name", "Id", "ParentObjectId"}, null, "SequenceNumber");
            while (iterator.hasNext()) {
                OpenProjectTreeItem eps = new OpenProjectTreeItem(iterator.next());
                if (this.containsKey(eps.getParentId())) {
                    this.get(eps.getParentId()).add(eps);
                } else {
                    ArrayList<OpenProjectTreeItem> newList = new ArrayList<>();
                    newList.add(eps);
                    this.put(eps.getParentId(), newList);
                }
            }
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }
}
