package primavera.objects;

import com.primavera.PrimaveraException;
import primavera.connect.PrimConnect;

public class OpenProjectHelper {

    private EPSHelper epsHelper;
    private ProjectListHelper projectListHelper;

    public OpenProjectHelper() {

        System.out.println("OpenProjectHelper - Start");
        PrimConnect primConnect = new PrimConnect();
        try {
            primConnect.login();
            epsHelper = new EPSHelper(PrimConnect.sessionAdmin);
            projectListHelper = new ProjectListHelper(PrimConnect.sessionAdmin);
            primConnect.logout();
            System.out.println("OpenProjectHelper - END");
        } catch (PrimaveraException e) {
            e.printStackTrace();
        }
    }

    public EPSHelper getEpsHelper() {
        return epsHelper;
    }

    public ProjectListHelper getProjectListHelper() {
        return projectListHelper;
    }
}
