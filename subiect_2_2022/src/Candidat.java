import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Candidat {
    private long cnp_candidat;
    private String nume_candidat;
    private double nota_bacalaureat;
    private int cod_specialitate_aleasa;

    public Candidat(long cnp_candidat, String nume_candidat, double nota_bacalaureat, int cod_specialitate_aleasa) {
        this.cnp_candidat = cnp_candidat;
        this.nume_candidat = nume_candidat;
        this.nota_bacalaureat = nota_bacalaureat;
        this.cod_specialitate_aleasa = cod_specialitate_aleasa;
    }

    public long getCnp_candidat() {
        return cnp_candidat;
    }

    public void setCnp_candidat(long cnp_candidat) {
        this.cnp_candidat = cnp_candidat;
    }

    public String getNume_candidat() {
        return nume_candidat;
    }

    public void setNume_candidat(String nume_candidat) {
        this.nume_candidat = nume_candidat;
    }

    public double getNota_bacalaureat() {
        return nota_bacalaureat;
    }

    public void setNota_bacalaureat(double nota_bacalaureat) {
        this.nota_bacalaureat = nota_bacalaureat;
    }

    public int getCod_specialitate_aleasa() {
        return cod_specialitate_aleasa;
    }

    public void setCod_specialitate_aleasa(int cod_specialitate_aleasa) {
        this.cod_specialitate_aleasa = cod_specialitate_aleasa;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Candidat other = (Candidat) obj;
        if(cnp_candidat != other.cnp_candidat)
            return false;
        if(nume_candidat == null)
        {
            if(other.nume_candidat != null)
                return false;
        }
        else if(nume_candidat.equals(other.nume_candidat) == false)
            return false;
        if(nota_bacalaureat != other.nota_bacalaureat)
            return false;
        if(cod_specialitate_aleasa != other.cod_specialitate_aleasa)
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static List<Candidat> citireFisier(String fileName)
    {
        List<Candidat> listaCandidati = new ArrayList<>();

        File file = new File(fileName);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                long cnp_candidat = Long.parseLong(values[0]);
                String nume_candidat = values[1];
                double nota_bacalaureat = Double.parseDouble(values[2]);
                int cod_specialitate_aleasa = Integer.parseInt(values[3]);
                listaCandidati
                        .add(new Candidat(cnp_candidat, nume_candidat, nota_bacalaureat, cod_specialitate_aleasa));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaCandidati;
    }
}
