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
        int arraysTheoryMaxSize = 10;
        int realSize = 0;
        String[] type = new String[arraysTheoryMaxSize];
        int[] amount = new int[arraysTheoryMaxSize];

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] values = value.split(importantSymbol);
                for (int i = 0; i < arraysTheoryMaxSize; i++) {
                    if (type[i] == null && values[operationTypeIndex] != null) {
                        type[i] = values[operationTypeIndex];
                        amount[i] += Integer.parseInt(values[amountIndex]);
                        realSize = i + 1;
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
            throw new RuntimeException("canot read from file", e);
        }

        for (int i = 0; i < realSize - 1; i++) {
            if (amount[i] < amount[i + 1]) {
                int amountArg = amount[i];
                amount[i] = amount[i + 1];
                amount[i + 1] = amountArg;
                String typeArg = type[i];
                type[i] = type[i + 1];
                type[i + 1] = typeArg;
            }
        }

        for (int i = 0; i < realSize; i++) {
            results.append(type[i]).append(',').append(amount[i]).append(System.lineSeparator());
        }
        results.append("result,").append(amount[0] - amount[1]);

        String[] result = new String[]{String.valueOf(results)};

        File file = new File(toFileName);
        for (String value : result) {
            try {
                Files.write(file.toPath(), value.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
