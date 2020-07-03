import message.MessageUser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import server.java.com.linglett.mapper.UserMapper;
import server.java.com.linglett.mapper.UserMapperImpl;
import server.java.com.linglett.pojo.User;
import server.java.com.linglett.utils.GenerateAccount;
import server.java.com.linglett.utils.ImageConversion;

import java.sql.*;

        import static java.sql.DriverManager.getConnection;
public class Test1 {
    public static void main(String[] args) throws SQLException {
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = GetPreparedStatement("insert into friend_list values(?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, GenerateAccount.CreateAccount());
            preparedStatement.setString(2,"1234567891");
            preparedStatement.setString(3,"Hello"+i);
            preparedStatement.execute();
        }

    }
    private static Connection DriverAndConnect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = getConnection("jdbc:mysql://localhost:3306/userlogin1.0", "root", "20001228");
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static Statement GetStatement() throws SQLException {

        return DriverAndConnect().createStatement();
    }
    public static PreparedStatement GetPreparedStatement(String Sql) throws SQLException {
        return DriverAndConnect().prepareStatement(Sql);
    }
}