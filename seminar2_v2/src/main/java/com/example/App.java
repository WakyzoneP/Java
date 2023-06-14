package com.example;

@FunctionalInterface
interface MathOperation<T> {
    T mf(T a, T b);
}

class MathOpClass<T> {
    public MathOpClass()
    {

    }
    public T doCalc(T a, T b, MathOperation<T> mathOp)
    {
        T res = mathOp.mf(a, b);
        return res;
    }
}

@FunctionalInterface
interface GreetingService {
    void sayMessage(String message);
}

public class App 
{
    public static void main( String[] args )
    {

        GreetingService greetServiceLambda1 = (String message) ->
        {
            StringBuffer res = new StringBuffer("Hello ");
            res.append(message);
            System.out.println(res);
        };

        greetServiceLambda1.sayMessage("Jake");

        GreetingService greetServiceLambda2 = message ->
        {
            StringBuffer res = new StringBuffer("Bonjour ");
            res.append(message);
            System.out.println(res);
        };

        greetServiceLambda2.sayMessage("Jake");

        MathOpClass<Double> testObj = new MathOpClass<>();

        MathOperation<Double> addLambda = (Double x, Double y) -> {
            @SuppressWarnings("removal")
            Double res = new Double(x + y);
            return res;
        };

        MathOperation<Double> subLambda = (x, y) -> x - y;

        MathOperation<Double> mulLambda = (x, y) -> x * y;

        System.out.println("23 + 5 = " + testObj.doCalc(23.0, 5.0, addLambda));
        System.out.println("23 - 5 = " + testObj.doCalc(23.0, 5.0, subLambda));
        System.out.println("23 * 5 = " + testObj.doCalc(23.0, 5.0, mulLambda));
    }
}
