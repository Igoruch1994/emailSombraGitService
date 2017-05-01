import dao.UserDAOImpl;
import model.User;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Igoruch on 23.04.2017.
 */
public class Test {

    static String query = "insert into user(email,login,enabled,password) value('Igxoruch','Igocxxran',1,'qwerty');";
    static String query1 = "SELECT * FROM  user";
    static Connection connection;
    static Statement statement;

    public static void main(String[] args) {
        /*try {
            connection = ConnectionJDBC.getMySQLConnection();
            statement = connection.createStatement();
            //statement.execute(query);
            ResultSet result = statement.executeQuery(query1);
            while (result.next()){
                System.out.println(result.getInt("id"));
                System.out.println(result.getString("login"));
                System.out.println(result.getString("password"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
*/      try {
            UserDAOImpl userDAO = new UserDAOImpl();
            User user = userDAO.getUserById(4);
            System.out.println(user);
            }
            catch (Exception e){
                 e.printStackTrace();
            }


    }
}
