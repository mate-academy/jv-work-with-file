package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        final String operationType1 = "supply";
        final String operationType2 = "buy";
        final String operationType3 = "result";
        File importedFile = new File(fromFileName);
        File exportedFile = new File(toFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(importedFile))) {
            String data = bufferedReader.readLine();
            while (data != null) {
                stringBuilder.append(data).append(System.lineSeparator());
                data = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read from this file", e);
        }
        String[] filteredData = stringBuilder.toString().split(System.lineSeparator());
        int operationType1Amount = 0;
        int operationType2Amount = 0;
        for (String filter : filteredData) {
            String[] parts = filter.split(",");
            if (parts[0].equals(operationType1)) {
                operationType1Amount += Integer.parseInt(parts[1]);
            } else if (parts[0].equals(operationType2)) {
                operationType2Amount += Integer.parseInt(parts[1]);
            }
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(exportedFile))) {
            bufferedWriter.write(operationType1 + "," + operationType1Amount
                    + System.lineSeparator());
            bufferedWriter.write(operationType2 + "," + operationType2Amount
                    + System.lineSeparator());
            bufferedWriter.write(operationType3 + ","
                    + (operationType1Amount - operationType2Amount)
                    + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can`t export the data", e);
        }
    }
}
