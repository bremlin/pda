package primavera.objects;

import com.primavera.integration.client.bo.BusinessObject;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.EPS;
import com.primavera.integration.client.bo.object.Project;

public class OpenProjectTreeItem {

    private Integer parentId;
    private Integer objectId;

    private String name;
    private String id;

    private int type;

    public OpenProjectTreeItem(BusinessObject businessObject) throws BusinessObjectException {
        if (businessObject instanceof EPS) {
            EPS eps = (EPS) businessObject;
            if (eps.getParentObjectId()!= null) {
                parentId = eps.getParentObjectId().toInteger();
            } else {
                parentId = 0;
            }
            this.name = eps.getName();
            this.id = eps.getId();
            this.type = 0;
        } else if (businessObject instanceof Project) {
            Project project = (Project) businessObject;
            if (project.getParentEPSObjectId()!= null) {
                parentId = project.getParentEPSObjectId().toInteger();
            } else {
                parentId = 0;
            }
            this.name = project.getName();
            this.id = project.getId();
            this.type = 1;
        }

        this.objectId = businessObject.getObjectId().toInteger();
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return this.id + " - " + this.name;
    }

    public int getType() {
        return type;
    }
}
