package pda.objects;

import com.primavera.integration.client.bo.BusinessObject;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Activity;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.client.bo.object.WBS;

import java.util.Date;

public class PdaTreeItem {

    private Integer objectId;
    private Integer parentId;

    private String id;
    private String name;

    private Date startDate;
    private Date finishDate;

    public PdaTreeItem(BusinessObject businessObject) {

        try {
            this.objectId = businessObject.getObjectId().toInteger();

            if (businessObject instanceof WBS) {
                WBS wbs = (WBS) businessObject;

                this.parentId = wbs.getParentObjectId().toInteger();
                this.id = wbs.getCode();
                this.name = wbs.getName();
                this.startDate = wbs.getStartDate();
                this.finishDate = wbs.getFinishDate();

            } else if (businessObject instanceof Activity) {
                Activity activity = (Activity) businessObject;

                this.parentId = activity.getWBSObjectId().toInteger();
                this.id = activity.getId();
                this.name = activity.getName();
                this.startDate = activity.getStartDate();
                this.finishDate = activity.getFinishDate();

            } else if (businessObject instanceof Project) {
                Project project = (Project) businessObject;

                parentId = 0;
                this.id = project.getId();
                this.name = project.getName();
                this.startDate = project.getStartDate();
                this.finishDate = project.getFinishDate();
            }
        } catch (BusinessObjectException e) {
            e.printStackTrace();
        }
    }

    public Integer getObjectId() {
        return objectId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }
}
