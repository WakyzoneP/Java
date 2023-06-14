import java.sql.*;

public class UtilsDAO {
    private static Connection c;

    public static void setConnection() {
        
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if(c != null)
                c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String selectData() throws SQLException {
        StringBuilder sb = new StringBuilder();

        String sql = "SELECT * FROM Cars";
        Statement statement = c.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next())
        {
            Integer id = resultSet.getInt("cod");
            Float weight = resultSet.getFloat("weight");
            Double price = resultSet.getDouble("price");
            String producer = resultSet.getString("producer");

            sb.append(id.toString() + ":" + weight.toString() + ":" + price.toString() + ":" + producer + "\r\n");
        }

        return sb.toString();
    }
}
