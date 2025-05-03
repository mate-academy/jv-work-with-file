package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder results = new StringBuilder();
        String importantSymbol = ",";
        int operationTypeIndex = 0;
        int amountIndex = 1;
        int arraysMaxSize = 3;
        int resultIndex = 2;
        String[] type = new String[arraysMaxSize];
        int[] amount = new int[arraysMaxSize];

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] values = value.split(importantSymbol);
                for (int i = 0; i < arraysMaxSize; i++) {
                    if (type[i] == null && values[operationTypeIndex] != null) {
                        type[i] = values[operationTypeIndex];
                        amount[i] += Integer.parseInt(values[amountIndex]);
                        break;
                    }
                    if (type[i] != null && type[i].equals(values[operationTypeIndex])) {
                        amount[i] += Integer.parseInt(values[amountIndex]);
                        break;
                    }
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("cannot read from file", e);
        }

        type[resultIndex] = "result";
        amount[resultIndex] = Math.abs(amount[1] - amount[0]);

        for (int i = 0; i < arraysMaxSize - 1; i++) {
            if (amount[i] < amount[i + 1]) {
                int amountArg = amount[i];
                amount[i] = amount[i + 1];
                amount[i + 1] = amountArg;
                String typeArg = type[i];
                type[i] = type[i + 1];
                type[i + 1] = typeArg;
            }
        }

        for (int i = 0; i < arraysMaxSize; i++) {
            results.append(type[i]).append(',').append(amount[i]).append(System.lineSeparator());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(results.toString());
        } catch (IOException e) {
            throw new RuntimeException("cannot write to file", e);
        }
    }
}
