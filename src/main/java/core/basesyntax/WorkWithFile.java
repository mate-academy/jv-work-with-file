package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder sb1 = new StringBuilder();
        final StringBuilder resultIntoFile = new StringBuilder();
        int sumOfSuppliers = 0;
        int sumOfBuy = 0;
        int result;

        try {
            BufferedReader br = new BufferedReader(new FileReader(fromFile));
            int value = br.read();
            while (value != -1) {
                sb1.append((char) value);
                value = br.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cound't read the file" + e);
        }

        String[] dates1 = sb1.toString().split("\\W+");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < dates1.length; j++) {
                if ((dates1[i].contains("supply")) && dates1[i].equals(dates1[j])) {
                    sumOfSuppliers = sumOfSuppliers + Integer.parseInt((dates1[j + 1]));
                } else if ((dates1[i].contains("buy")) && dates1[i].equals(dates1[j])) {
                    sumOfBuy = sumOfBuy + Integer.parseInt((dates1[j + 1]));
                }
            }

        }
        result = sumOfSuppliers - sumOfBuy;
        resultIntoFile.append(result);

        try (BufferedWriter br1 = new BufferedWriter(new FileWriter(toFile))) {
            br1.write("supply," + sumOfSuppliers);
            br1.newLine();
            br1.write("buy," + sumOfBuy);
            br1.newLine();
            br1.write("result," + resultIntoFile.toString());
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            throw new RuntimeException("Cound't write the file" + e);
        }
    }
}

