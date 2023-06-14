import java.sql.*;

public class UtilsDAO {
    private static Connection c;

    public static void setConnection() {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (c != null)
                c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String selectData() throws SQLException {
        StringBuilder resultStringBuilder = new StringBuilder();

        String sql = "select * from PHONES";
        Statement statement = c.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while(result.next())
        {
            int id = result.getInt("cod");
            String producer = result.getString("PRODUCER");
            Double diagonal = result.getDouble("DIAGONAL");
            Float weight = result.getFloat("WEIGHT");

            String line = Integer.toString(id) + ":" + producer + ":" + Double.toString(diagonal) + ":" + Float.toString(weight) + "\r\n";
            resultStringBuilder.append(line);
        }

        return resultStringBuilder.toString();
    }
}
