import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        List<Aventura> aventuraList = Aventura.readAventuraListFromJSON("data/aventuri.json");

        for (Aventura aventura : aventuraList) {
            System.out.println(aventura.getCod_aventura());
        }
    }
}