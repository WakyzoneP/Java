import java.sql.*;
import java.util.*;

public class Specializare {

    private int cod;
    private String denumire;
    private int locuri;

    public Specializare(int cod, String denumire, int locuri) {
        this.cod = cod;
        this.denumire = denumire;
        this.locuri = locuri;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getLocuri() {
        return locuri;
    }

    public void setLocuri(int locuri) {
        this.locuri = locuri;
    }

    @Override
    public String toString() {
        return "Specializari{" +
                "cod=" + cod +
                ", denumire='" + denumire + '\'' +
                ", locuri=" + locuri +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Specializare other = (Specializare) obj;
        if(cod != other.cod)
            return false;
        if(denumire == null)
        {
            if(other.denumire != null)
                return false;
        }
        else if(denumire.equals(other.cod) == false)
            return false;
        if(locuri != other.locuri)
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Specializare nou = (Specializare)super.clone();
        nou.cod = cod;
        nou.denumire = denumire;
        nou.locuri = locuri;

        return nou;
    }

    public static List<Specializare> citireBazaDate()
    {
        List<Specializare> listaSpecializari = new ArrayList<>();
        String url = "jdbc:sqlite:/Users/vlad/user Documents/Java/subiect_2_2022/data/specializari.db";

        try {
            Connection connection = DriverManager.getConnection(url);
            String sq1 = "SELECT * FROM Specializari";

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sq1);

            while (result.next()) {
                int cod = result.getInt("cod");
                String denumiere = result.getString("denumire");
                int locuri = result.getInt("locuri");
                listaSpecializari.add(new Specializare(cod, denumiere, locuri));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaSpecializari;
    }
}
