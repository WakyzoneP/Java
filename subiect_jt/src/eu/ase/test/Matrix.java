package eu.ase.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

/*
 * Nota 5: Creati clasa Matrix ce implementeaza interfata Cloneable.
 * 
 * The clasa are urmatoarele campuri private:
 * noLines : int pentru numarul de linii ale matricei
 * noColumns: int pentru numarul de coloane ale matricei
 * matrix: Object[][] pentru containerul ce stocheaza referinte catre obiectele matricei
 * 
 * Aditional, urmatoarele campuri private ale clase sunt utilizate pentru rezultate intermediare:
 *  listCustomers de tip List<Customer>;
 *  streamCustomer de tip Stream<Customer>;
 *  predicate de tip Predicate<Customer>;
 *  threadsArrayWorkerTasks de tip MyR[];
 * 
 * Creati constructorul fara parametrii (default)
 * 
 * Creati constructorul cu 3 parametri: 
 * "noLines" de tip int, "noCols" de tip int, "matrix" de tip Object[][]
 * Constructorul produce o exceptie din clasa Exception daca numarul de linii sau coloane este mai mic de 0
 * Pentru campurile aditionale utilizate la rezultate intermediare (listCustomers, streamCustomer, predicate, threadsArrayWorkerTasks) in acest constructor sunt initializate cu valoarea null
 * 
 * Implementati corespunzator metodele get/set pentru toate campurile si supra-scrieti metodele in mod public:
 * equals(Object o) -> boolean si hashCode() -> int
 * Metodele equals() si hashCode() IGNORA 
 * campurile private aditionale (listCustomers, streamCustomer, predicate, threadsArrayWorkerTasks)
 * 
 * Metodele set pentru linii si coloane pot produce exceptie din clasa Exception daca primesc parametri mai mici de 0
 * 
 * Implementati consistent metoda clone pentru a crea clone din obiectul curent:
 * public clone() -> Object si poate produce exceptie de tip CloneNotSupportedException
 * Metoda clone() IGNORA campurile aditionale si intermediare private (listCustomers, streamCustomer, predicate, threadsArrayWorkerTasks)
 * 
 * Implementati metoda publica getNotNullsNumber() ce returneaza un intreg (int) ce reprezinta numarul de elemente diferite de null 
 * 
 * Nota 6: Creati metodele publice si statice:
 *  writeBinary(String file, List<Customer> listC) -> void ce serializeaza obiectele dintr-o lista de clienti intr-un fisier; fiecare obiect este din clasa PrimeCustomer
 *  
 *  readBinary(String file) -> List<Customer> ce restaureaza/deserializeaza obiectele dintr-un fisier intr-o lista de clienti; fiecare obiect este din clasa PrimeCustomer
 *  
 *  
 * 1 punct (Nota 5 este obligatorie pentru punctajele incrementale de la 2 la 5):
 *  Implementati metoda publica transformMatrix2VectorAndLambdaSortCustomers() -> List<Customer>
 *  care transforma matricea intr-un obiect de tip ArrayList<Customer> si apoi, 
 *  sorteaza clientii folosind fluxuri functionale de procesare (functional processing stream),
 *  dupa ce i-a filtrat (nr de transactii mai mare sau egal ca 5) in prealabil utilizand o expresie lambda in predicatul metodei filter.
 *  Aceasta metoda trebuie sa seteze consistent campurile intermediare ale clasei:
 *  this.streamClient, this.listClient si this.predicate
 *  
 * 1 punct (Nota 5 este obligatorie pentru punctajele incrementale de la 2 la 5)::
 *  Implementati metoda publica calculateEarnings() ce returneaza suma cheltuielilor clientilor (totalSpent)
 *  si metoda publica calculateEarningsMultiThreading() care utilizeaza mecanismul multi-threading Executor-Service 
 *  pentru crearea de sarcini de lucru (worker tasks) utilizand campul: this.threadsArrayWorkerTasks din clasa MyR (ce implementeaza interfata Runnable + 2 puncte)  
 *  si calculeaza suma valorilor facturate printr-un numar de fire de execurie egal cu numarul de linii din matrice.
 *  Aceasta metoda utilizeaza campul this.threadsArrayWorkerTasks
 */

public class Matrix implements Cloneable {
    private int noLines;
    private int noColumns;
    private Object[][] matrix;

    private List<Customer> listCustomers;
    private Stream<Customer> streamCustomer;
    private Predicate<Customer> predicate;
    private MyR[] threadsArrayWorkerTasks;

    public Matrix() {
    }

    public Matrix(int noLines, int noColumns, Object[][] matrix) throws Exception {

        if (noLines < 0 || noColumns < 0)
            throw new Exception("The number of lines and columns must be positiva");

        this.noLines = noLines;
        this.noColumns = noColumns;
        this.matrix = matrix;

        listCustomers = null;
        streamCustomer = null;
        predicate = null;
        threadsArrayWorkerTasks = null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + noLines;
        result = prime * result + noColumns;
        result = prime * result + Arrays.deepHashCode(matrix);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Matrix other = (Matrix) obj;
        if (noLines != other.noLines)
            return false;
        if (noColumns != other.noColumns)
            return false;
        if (!Arrays.deepEquals(matrix, other.matrix))
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Matrix newMatrix = (Matrix) super.clone();
        newMatrix.noLines = noLines;
        newMatrix.noColumns = noColumns;
        newMatrix.matrix = matrix.clone();
        return newMatrix;
    }

    public static void writeBinary(String file, List<Customer> listC) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));

            for (var element : listC) {
                PrimeCustomer p = (PrimeCustomer) element;
                out.writeObject(p);
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> readBinary(String file) {
        List<Customer> list = new ArrayList<>();
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));

            while (true) {
                PrimeCustomer p = (PrimeCustomer) in.readObject();
                list.add(p);
            }
        } catch (EOFException eof) {
            try {
                if (in != null)
                    in.close();
            } catch (Exception e) {
            }
            // System.out.println("End of file");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Customer> transformMatrix2VectorAndLambdaSortCustomers() {
        listCustomers = new ArrayList<>();
        for (var element : matrix) {
            for (var element2 : element) {
                if (element2 != null)
                    listCustomers.add((Customer) element2);
            }
        }

        streamCustomer = listCustomers.stream();
        predicate = (Customer c) -> c.getNrTransactions() >= 5;
        streamCustomer = streamCustomer.filter(predicate);
        listCustomers = streamCustomer.sorted().toList();

        return listCustomers;
    }

    public float calculateEarnings() {
        float total = 0;
        for (var element : matrix)
            for (var element2 : element)
                total += ((Customer) element2).getTotalSpent();

        return total;
    }

    public float calculateEarningsMultiThreading() {
        float total = 0;
        ExecutorService executor = Executors.newFixedThreadPool(noLines);
        threadsArrayWorkerTasks = new MyR[noLines];

        for (int i = 0; i < noLines; ++i) {
            threadsArrayWorkerTasks[i] = new MyR(this, i);
            executor.execute(threadsArrayWorkerTasks[i]);
        }

        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < noLines; ++i)
            total += threadsArrayWorkerTasks[i].getSuma();

        return total;
    }

    public int getNoLines() {
        return noLines;
    }

    public void setNoLines(int noLines) throws Exception {
        if (noLines < 0)
            throw new Exception("The number of lines must be positive");
        this.noLines = noLines;
    }

    public int getNoColumns() {
        return noColumns;
    }

    public void setNoColumns(int noColumns) throws Exception {
        if (noColumns < 0)
            throw new Exception("The number of columns must be positive");
        this.noColumns = noColumns;
    }

    public Object[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Object[][] matrix) {
        this.matrix = matrix;
    }

    public List<Customer> getListCustomers() {
        return listCustomers;
    }

    public void setListCustomers(List<Customer> listCustomers) {
        this.listCustomers = listCustomers;
    }

    public Stream<Customer> getStreamCustomer() {
        return streamCustomer;
    }

    public void setStreamCustomer(Stream<Customer> streamCustomer) {
        this.streamCustomer = streamCustomer;
    }

    public Predicate<Customer> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<Customer> predicate) {
        this.predicate = predicate;
    }

    public MyR[] getThreadsArrayWorkerTasks() {
        return threadsArrayWorkerTasks;
    }

    public void setThreadsArrayWorkerTasks(MyR[] threadsArrayWorkerTasks) {
        this.threadsArrayWorkerTasks = threadsArrayWorkerTasks;
    }

    public int getNotNullsNumber() {
        int numberOfNotNull = 0;

        for (var element : matrix) {
            for (var element2 : element)
                if (element2 != null)
                    numberOfNotNull += 1;
        }

        return numberOfNotNull;
    }
}
