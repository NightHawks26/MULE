package Java;

import java.sql.*;

public class SQLiteJDBC {
    private Connection conn;
//    public static void main( String args[] )
//    {
//        Connection c = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/src/resources/MuleGame.db");
//        } catch ( Exception e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }
//        System.out.println("Opened database successfully");
//    }

    public SQLiteJDBC() {
        this.conn = connect();
    }

    public Connection connect() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/src/resources/MuleGame.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    public Connection getConn() {
        return conn;
    }
}