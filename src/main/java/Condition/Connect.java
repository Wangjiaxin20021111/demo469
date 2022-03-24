package Condition;

import java.sql.*;

/**
 * @author 25043
 */
public class Connect {
    public static Connection co() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String aa = "jdbc:mysql://localhost:3306/Test";
        String root = "root";
        String password = "20021111aA#";
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(aa, root, password);
    }
    public static void close(ResultSet rr, Statement stmt, Connection coon)  {
        if(stmt!=null)
        {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(coon!=null)
        {
            try {
                coon.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rr!=null)
        {
            try {
                rr.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
