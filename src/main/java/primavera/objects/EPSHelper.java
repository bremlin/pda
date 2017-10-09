package primavera.objects;

import com.primavera.ServerException;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.EPS;
import com.primavera.integration.network.NetworkException;

import java.util.HashMap;

public class EPSHelper extends HashMap<Integer, EPS> {

    public EPSHelper(Session session) {
        try {
            BOIterator<EPS> iterator = session.getGlobalObjectManager().loadEPS(new String[] {
                    "ObjectId", "Name", "Id", "ParentObjectId", "SequenceNumber"
            }, null, null);
            while (iterator.hasNext()) {
                EPS eps = iterator.next();
                this.put(eps.getObjectId().toInteger(), eps);
            }
        } catch (ServerException | NetworkException | BusinessObjectException e) {
            e.printStackTrace();
        }
    }
}
