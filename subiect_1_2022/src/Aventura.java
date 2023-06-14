import java.io.*;
import java.util.*;

import org.json.*;

public class Aventura {
    private int cod_aventura;
    private String denumire;
    private float tarif;
    private int locuri_disponibile;

    public Aventura(int cod_aventura, String denumire, float tarif, int locuri_disponibile) {
        this.cod_aventura = cod_aventura;
        this.denumire = denumire;
        this.tarif = tarif;
        this.locuri_disponibile = locuri_disponibile;
    }

    public static List<Aventura> readAventuraListFromJSON(String filename)
    {
        List<Aventura> aventuraList = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            StringBuilder sb = new StringBuilder();
            String ls = System.getProperty("line.separator");
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line);
                // sb.append(ls);
            }
            String jsonText = sb.toString();
            JSONArray jsonArray = new JSONArray(jsonText);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int cod_aventura = jsonObject.getInt("cod_aventura");
                String denumire = jsonObject.getString("denumire");
                float tarif = (float) jsonObject.getDouble("tarif");
                int locuri_disponibile = jsonObject.getInt("locuri_disponibile");
                Aventura aventura = new Aventura(cod_aventura, denumire, tarif, locuri_disponibile);
                aventuraList.add(aventura);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aventuraList;
    }



    public int getCod_aventura() {
        return cod_aventura;
    }



    public void setCod_aventura(int cod_aventura) {
        this.cod_aventura = cod_aventura;
    }



    public String getDenumire() {
        return denumire;
    }



    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }



    public float getTarif() {
        return tarif;
    }



    public void setTarif(float tarif) {
        this.tarif = tarif;
    }



    public int getLocuri_disponibile() {
        return locuri_disponibile;
    }



    public void setLocuri_disponibile(int locuri_disponibile) {
        this.locuri_disponibile = locuri_disponibile;
    }
}