package eu.ase.test;

/* 
 *  2 puncte (Nota 5 este obligatorie pentru punctajele incrementale de la 2 la 5):
 *  Dezvoltati clasa publica MyR care implementeaza interfata Runnable
 *  Clasa contine urmatoarele campuri private:
 *  v de tip Customer[] // vector de clienti
 *  sum de tip float // suma valorilor
 *  
 *  Implementati constructorul cu 2 parameteri:
 *  "m" de tip Matrix, "line" de tip int
 *  Fiecare element din campul v este referinta catre un obiect din pozitia coresunzatoare din linia matricei primite ca parmetru
 *  
 *  Supra-scrieti metoda run() pentru a fi apelata in mod multi-fir pentru calcularea sumei cheltuielilor clientilor
 *  si furnizati metoda publica getSum() ce returneaza o valoare de tip float ce reprezinta suma cheltuielilor clientilor.
 */

public class MyR implements Runnable {

    private Customer[] v;
    private float suma;

    public MyR(Matrix m, int line) {
        v = (Customer[])m.getMatrix()[line];
    }

    @Override
    public void run() {
        suma = 0;
        for(Customer element : v)
            suma += element.getTotalSpent();
    }

    public float getSuma()
    {
        return suma;
    }

}
