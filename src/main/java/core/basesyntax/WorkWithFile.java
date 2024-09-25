package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder results = new StringBuilder();
        String importantSymbol = ",";
        int operationTypeIndex = 0;
        int amountIndex = 1;
        int arraysSize = 3;
        int resultIndex = 2;
        String[] type = new String[arraysSize];
        int[] amount = new int[arraysSize];

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] values = value.split(importantSymbol);
                for (int i = 0; i < arraysSize; i++) {
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
                type[resultIndex] = "result";
                amount[resultIndex] = Math.abs(amount[resultIndex - 1] - amount[resultIndex - 2]);
            }
        } catch (IOException e) {
            throw new RuntimeException("cannot read from file", e);
        }

        for (int i = 0; i < arraysSize - 1; i++) {
            if (amount[i] < amount[i + 1]) {
                int amountArg = amount[i];
                amount[i] = amount[i + 1];
                amount[i + 1] = amountArg;
                String typeArg = type[i];
                type[i] = type[i + 1];
                type[i + 1] = typeArg;
            }
        }

        for (int i = 0; i < arraysSize; i++) {
            results.append(type[i]).append(',').append(amount[i]).append(System.lineSeparator());
        }

        File file = new File(toFileName);
        try {
            Files.writeString(file.toPath(), results);
        } catch (IOException e) {
            throw new RuntimeException("cannot write to file", e);
        }
    }
}
