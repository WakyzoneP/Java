import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        List<Programare> listaProgramari = new ArrayList<Programare>();

        File file = new File("date/orar.txt");

        // try {
        // BufferedReader buffer = new BufferedReader(new FileReader(file));
        // if (buffer != null) {
        // String str = buffer.readLine();
        // while (str != null) {

        // System.out.println(str);

        // str = buffer.readLine();
        // }
        // buffer.close();
        // }

        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        try (Scanner scanner = new Scanner(file);) {

            while (scanner.hasNext()) {
                String str = scanner.nextLine();
                String[] values = str.split(",");
                String disciplina = values[0];
                Integer zi = Integer.parseInt(values[1]);
                Integer interval = Integer.parseInt(values[2]);
                Tip tip;
                switch (values[3]) {
                    case "CURS":
                        tip = Tip.Curs;
                        break;
                    case "SEMINAR":
                        tip = Tip.Seminar;
                        break;
                    default:
                        tip = Tip.Curs;
                }
                String formatie = values[4];
                Programare programare = new Programare(disciplina, zi, interval, tip, formatie);

                listaProgramari.add(programare);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (!listaProgramari.isEmpty()) {
            Integer nrCursuri = 0, nrSeminarii = 0;
            for (var progamare : listaProgramari) {
                if (progamare.getTip() == Tip.Curs)
                    nrCursuri += 1;
                if (progamare.getTip() == Tip.Seminar)
                    nrSeminarii += 1;
            }
            System.out.println("Nr de Cursuri este " + nrCursuri + ", Nr de seminarii este " + nrSeminarii);
        }

        HashMap<String, ArrayList<Programare>> hashmap = new HashMap<String, ArrayList<Programare>>();
        if (!listaProgramari.isEmpty()) {
            for (var progamare : listaProgramari) {
                if (hashmap.get(progamare.getDisciplina()) == null) {
                    ArrayList<Programare> temp = new ArrayList<Programare>();
                    hashmap.put(progamare.getDisciplina(), temp);
                }

                hashmap.get(progamare.getDisciplina()).add(progamare);
            }
        }
        if (!hashmap.isEmpty()) {
            for (var key : hashmap.keySet()) {
                ArrayList<Programare> temp = hashmap.get(key);
                Integer nrCursuri = 0, nrSeminarii = 0;
                for (var progamare : temp) {
                    if (progamare.getTip() == Tip.Curs)
                        nrCursuri += 1;
                    if (progamare.getTip() == Tip.Seminar)
                        nrSeminarii += 1;
                }
                if (nrCursuri == 1 && nrSeminarii == 2) {
                    System.out.println(key);
                    for (var progamare : temp) {
                        System.out.println(progamare.toString());
                    }
                }
            }
        }

        Collections.sort(listaProgramari);

        // try {
        //     ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("date/programari.dat"));
        //     // for(var programare : listaProgramari){
        //     // Programare obj = new Programare(programare.getDisciplina(),
        //     // programare.getZi(), programare.getInterval(), programare.getTip(),
        //     // programare.getFormatie());
        //     // out.writeObject(obj);
        //     // }
        //     out.writeObject(listaProgramari);
        //     out.flush();
        //     out.close();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        // try {
        //     ObjectInputStream in = new ObjectInputStream(new FileInputStream("date/programari.dat"));
        //     ArrayList<Programare> obj = (ArrayList<Programare>) in.readObject();
        //     for(var a : obj){
        //         System.out.println(a.toString());
        //     }
        //     in.close();;
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        try {
            ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream("date/programari.dat"));
            for(var programare : listaProgramari){
                out.writeObject(programare);
            }
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        // try {
        //     ObjectInputStream in = new ObjectInputStream( new FileInputStream("date/programari.dat"));

        // }
    }
}
