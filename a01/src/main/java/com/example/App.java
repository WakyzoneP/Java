package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class App {
    public static void main(String[] args) throws IOException {
        Hashtable<String, Tax[]> taxTable = new Hashtable<String, Tax[]>();
        taxTable.put("Single", new Tax[6]);
        taxTable.put("Married Filing Jointly or Qualified Widow(er)", new Tax[6]);
        taxTable.put("Married Filing Separately", new Tax[6]);
        taxTable.put("Head of Household", new Tax[6]);
        
        taxTable.get("Single")[0] = new Tax(10, 0, 8350);
        taxTable.get("Single")[1] = new Tax(15, 8351, 33950);
        taxTable.get("Single")[2] = new Tax(25, 33951, 82250);
        taxTable.get("Single")[3] = new Tax(28, 82251, 171550);
        taxTable.get("Single")[4] = new Tax(33, 171551, 372950);
        taxTable.get("Single")[5] = new Tax(35, 372951, Integer.MAX_VALUE);

        taxTable.get("Married Filing Jointly or Qualified Widow(er)")[0] = new Tax(10, 0, 16700);
        taxTable.get("Married Filing Jointly or Qualified Widow(er)")[1] = new Tax(15, 16701, 67900);
        taxTable.get("Married Filing Jointly or Qualified Widow(er)")[2] = new Tax(25, 67901, 137050);
        taxTable.get("Married Filing Jointly or Qualified Widow(er)")[3] = new Tax(28, 137051, 208850);
        taxTable.get("Married Filing Jointly or Qualified Widow(er)")[4] = new Tax(33, 208851, 372950);
        taxTable.get("Married Filing Jointly or Qualified Widow(er)")[5] = new Tax(35, 372951, Integer.MAX_VALUE);

        taxTable.get("Married Filing Separately")[0] = new Tax(10, 0, 8350);
        taxTable.get("Married Filing Separately")[1] = new Tax(15, 8351, 33950);
        taxTable.get("Married Filing Separately")[2] = new Tax(25, 33951, 68525);
        taxTable.get("Married Filing Separately")[3] = new Tax(28, 68526, 104425);
        taxTable.get("Married Filing Separately")[4] = new Tax(33, 104426, 186475);
        taxTable.get("Married Filing Separately")[5] = new Tax(35, 186476, Integer.MAX_VALUE);

        taxTable.get("Head of Household")[0] = new Tax(10, 0, 11950);
        taxTable.get("Head of Household")[1] = new Tax(15, 11951, 45500);
        taxTable.get("Head of Household")[2] = new Tax(25, 45501, 117450);
        taxTable.get("Head of Household")[3] = new Tax(28, 117451, 190200);
        taxTable.get("Head of Household")[4] = new Tax(33, 190201, 372950);
        taxTable.get("Head of Household")[5] = new Tax(35, 372951, Integer.MAX_VALUE);

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
        
        System.out.print("Enter the filing status: ");
        String status = reader.readLine();

        System.out.print("Enter the taxable income: ");
        double income = Double.parseDouble(reader.readLine());
 
        System.out.println("Tax is " + Tax.computeTax(taxTable.get(status), income));
    }
}
