package primavera.connect;

import com.primavera.PrimaveraException;
import com.primavera.integration.client.Session;

public class PrimConnect {

    public static Session sessionAdmin;
    public String sHost;              //~ Адрес хоста подключения
    public int                  iPort;              //~ Порт хоста подключения

    public String sUserName;          //~ Логин для подключения
    public String sPassword;          //~ Пароль для подключения
    public String sDatabaseId = "1";        //~ Идентификатор инстанса БД

    public void login() throws PrimaveraException {
        System.setProperty("primavera.bootstrap.home", "xml\\");
        sessionAdmin = Session.login(null, sDatabaseId, "iapi", "bfgb#1");
    }

    public void logout() {
        sessionAdmin.logout();
    }

}
