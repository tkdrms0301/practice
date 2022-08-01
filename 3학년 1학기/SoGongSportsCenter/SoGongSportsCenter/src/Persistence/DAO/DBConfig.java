package Persistence.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {
    private Connection conn;
    private String dbURL = "jdbc:mysql://localhost:3306/sportcenter?useUnicode=true&characterEncoding=utf8";
    private String dbId = "root";
    private String dbPassword = "db042000@";

    public DBConfig(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, dbId, dbPassword);
        }catch (Exception e){

        }
    }

    public Connection getConnection(){
        return conn;
    }
}
