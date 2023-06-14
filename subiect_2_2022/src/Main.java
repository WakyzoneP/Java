import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.Executor;

import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        List<Specializare> listaSpecializari = Specializare.citireBazaDate();
        for (var specializare : listaSpecializari) {
            System.out.println("Specializari{" +
                    "cod=" + specializare.getCod() +
                    ", denumire='" + specializare.getDenumire() + '\'' +
                    '}');
        }
        List<Candidat> listaCandidati = Candidat.citireFisier("data/candidati.txt");

        if (!listaSpecializari.isEmpty()) {
            int totalLocuri = 0;
            for (var specializare : listaSpecializari) {
                totalLocuri += specializare.getLocuri();
            }
            System.out.println("Faculatatea are " + totalLocuri + " de locuri disponibile");
        } else {
            System.out.println("Faculatatea nu are locuri disponibile");
        }

        System.out.println("----------------------------");

        if (!listaSpecializari.isEmpty() && !listaCandidati.isEmpty()) {
            Map<Integer, Integer> locuriOcupateCod = new HashMap<>();
            for (var candidat : listaCandidati) {
                int cod = candidat.getCod_specialitate_aleasa();
                if (locuriOcupateCod.get(cod) == null) {
                    locuriOcupateCod.put(cod, 1);
                } else {
                    int value = locuriOcupateCod.get(cod);
                    value += 1;
                    locuriOcupateCod.put(cod, value);
                }
            }

            for (var specializare : listaSpecializari) {
                if (locuriOcupateCod.get(specializare.getCod()) == null) {
                    locuriOcupateCod.put(specializare.getCod(), 0);
                }
                int locuriDisponibile = specializare.getLocuri() - locuriOcupateCod.get(specializare.getCod());
                if (locuriDisponibile >= 100) {
                    System.out.println("Specializari{" +
                            "cod=" + specializare.getCod() +
                            ", denumire='" + specializare.getDenumire() + '\'' +
                            ", locuri=" + locuriDisponibile +
                            '}');
                }
            }

            JSONArray jsonArray = new JSONArray();

            for (var specializare : listaSpecializari) {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("cod_specialitate", specializare.getCod());
                    jsonObject.put("denumire", specializare.getDenumire());
                    jsonObject.put("numar_inscrieri", locuriOcupateCod.get(specializare.getCod()));
                    double medie = listaCandidati.stream()
                            .filter(candidat -> candidat.getCod_specialitate_aleasa() == specializare.getCod())
                            .mapToDouble(Candidat::getNota_bacalaureat).average().orElse(0) * 100;

                    double medieAux = Math.round(medie) / 100.0;
                    jsonObject.put("medie", medieAux);
                    jsonArray.put(jsonObject);

                    FileWriter fileWriter = new FileWriter("data/specializari.json");
                    fileWriter.write(jsonArray.toString());
                    fileWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

        System.out.println("----------------------------");
    }
}