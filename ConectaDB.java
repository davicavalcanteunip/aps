import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB {

    public Connection conectaDB() {
        Connection conn = null;

        try {
            String url = "jdbc:mysql://localhost:3306/aps?user=root&password=";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}