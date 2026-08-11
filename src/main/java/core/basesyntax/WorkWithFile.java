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
        StringBuilder fileContents = new StringBuilder();
        final StringBuilder resultIntoFile = new StringBuilder();
        int sumOfSuppliers = 0;
        int sumOfBuy = 0;
        int result;

        try (BufferedReader br = new BufferedReader(new FileReader(fromFile))) {
            int value = br.read();
            while (value != -1) {
                fileContents.append((char) value);
                value = br.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }

        String[] operationsData = fileContents.toString().split("\\W+");

        for (int i = 0; i < operationsData.length; i = i + 2) {
            if (operationsData[i].equals("supply")) {
                sumOfSuppliers = sumOfSuppliers + Integer.parseInt((operationsData[i + 1]));
            } else {
                sumOfBuy = sumOfBuy + Integer.parseInt((operationsData[i + 1]));
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
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}

