package privacyManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {

    //ToDo IP von aussen übernhemen
    //String ip = null;
    public static Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwd", "root", "root");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return conn;
    }                

    public static void connectionClose(Connection connection) throws SQLException {
        connection.close();
    }
}
