import java.io.Serializable;

public final class Programare implements Comparable<Programare>, Serializable {

    // private static final long serialVersionUID = -689547391845626872L;

    private String disciplina;
    private Integer zi;
    private Integer interval;
    private Tip tip;
    private String formatie;

    public Programare(String disciplina, Integer zi, Integer interval, Tip tip, String formatie) {
        this.disciplina = new String(disciplina);
        this.zi = zi;
        this.interval = interval;
        this.tip = tip;
        this.formatie = new String(formatie);
    }

    public String getDisciplina() {
        String temp = new String(disciplina);
        return temp;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = new String(disciplina);
    }

    public Integer getZi() {
        return zi;
    }

    public void setZi(Integer zi) {
        this.zi = zi;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getFormatie() {
        String temp = new String(formatie);
        return temp;
    }

    public void setFormatie(String formatie) {
        this.formatie = new String(formatie);
    }

    @Override
    public String toString() {
        String tipString;
        switch (tip){
            case Curs:
                tipString = "CURS";
            break;
            case Seminar:
                tipString = "SEMINAR";
            break;
            default:
                tipString = "CURS";
        }
        return tipString + "," + zi.toString() + "," + interval.toString();
    }

    @Override
    public int compareTo(Programare o) {
        if(this.zi < o.zi)
            return -1;
        else if(this.zi > o.zi)
            return 1;
        else if(this.interval < o.interval)
            return -1;
        else if(this.interval > o.interval)
            return 1;
        else
            return 0;
    }

    
}
