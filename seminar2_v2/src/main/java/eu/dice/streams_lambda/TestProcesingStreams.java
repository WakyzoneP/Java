package eu.dice.streams_lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TestProcesingStreams {
    public static int numberOfEmptyStrings = 0;
    public static void main( String[] args )
    {
        List<String> myList = Arrays.asList("Ana", "Bianca", "", "Diana", "Elena", "", "Horia", "Iulian", "Iulia", "Gheorghe");

        System.out.println(myList.stream());
        
        // myList.forEach(s -> {
        //     if (s.isEmpty()) {
        //         numberOfEmptyStrings++;
        //     }
        // });

        Predicate<String> predIsEmpty = s -> s.isEmpty();

        numberOfEmptyStrings = (int) myList.stream().filter(predIsEmpty).count();

        int numberOf6CharsStrings = (int) myList.stream().filter(s -> s.length() == 6).count();
        System.out.println(numberOfEmptyStrings);
        System.out.println(numberOf6CharsStrings);
        System.out.println(myList.stream().filter(s -> s.length() == 6).isParallel());
    }
}
