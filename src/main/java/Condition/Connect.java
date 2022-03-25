package Condition;

import java.sql.*;

/**
 * @author 25043
 */
public class Connect {
    public static Connection co() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url ="jdbc:mysql://localhost:3306/Test";
        String root = "root";
        String password = "20021111aA#";
        return DriverManager.getConnection(url, root, password);
    }
    public static void close(ResultSet rr, Statement stmt, Connection coon)  {
        /*以下代码，关闭的规则为，哪个曾经被使用过，不为null，就关闭哪个*/
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
