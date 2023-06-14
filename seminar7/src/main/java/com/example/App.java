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

public class App 
{
    public static void main( String[] args )
    {
        MathOpClass<Double> testObj = new MathOpClass<>();

        MathOperation<Double> addLambda = (Double x, Double y) -> {
            // @SuppressWarnings("removal")
            Double res = new Double(x + y);
            return res;
        };
    }
}
