package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.EPS;
import com.primavera.integration.network.NetworkException;

import java.util.ArrayList;
import java.util.HashMap;

public class EPSHelper extends HashMap<Integer, ArrayList<EPS>> {

    public EPSHelper(Session session) {
        try {
            BOIterator<EPS> iterator = session.getGlobalObjectManager().loadEPS(new String[] {
                    "ObjectId", "Name", "Id", "ParentObjectId", "SequenceNumber"
            }, null, null);
            while (iterator.hasNext()) {
                EPS eps = iterator.next();
                Integer parentId = 0;
                if (eps.getParentObjectId()!= null) parentId = eps.getParentObjectId().toInteger();
                if (this.containsKey(parentId)) {
                    this.get(parentId).add(eps);
                } else {
                    ArrayList<EPS> newList = new ArrayList<>();
                    newList.add(eps);
                    this.put(parentId, newList);
                }
            }
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }
}
