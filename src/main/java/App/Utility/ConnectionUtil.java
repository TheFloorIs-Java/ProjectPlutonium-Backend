package App.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection conn;
    public static Connection getConnection(){
        if (conn == null)
            try{
                String url = "jdbc:sqlserver://projecttwoserver.database.windows.net:1433;database=p2db;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
                String username = "jacob367";
                String password = "Plut0nium";

                conn = DriverManager.getConnection(url,username,password);
            }catch(SQLException e){
                e.printStackTrace();
            }
        return conn;
    }
}