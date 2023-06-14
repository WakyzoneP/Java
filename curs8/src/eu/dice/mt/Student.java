package eu.dice.mt;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

final public class Student implements Comparable<Student>, Serializable, Cloneable {

    private String name;
    private String surname;

    private Map<String, String> metaData;

    public Student(String name, String surname, Map<String, String> metaData) {
        this.name = new String(name);
        this.surname = new String(surname);

        this.metaData = new HashMap<>(metaData);
    }

    @Override
    public int compareTo(Student o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}
