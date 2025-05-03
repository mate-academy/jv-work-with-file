package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        int buyTotal = 0;
        int supplyTotal = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String operationType = value.split(",")[0];
                int operationValue = Integer.valueOf(value.split(",")[1]);
                if (operationType.equals("supply")) {
                    supplyTotal += operationValue;
                } else {
                    buyTotal += operationValue;
                }
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
        writeToFile(toFileName, createReport(buyTotal, supplyTotal));
    }

    private String createReport(int buyTotal, int supplyTotal) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("supply," + supplyTotal + System.lineSeparator()
                + "buy," + buyTotal + System.lineSeparator()
                + "result," + (supplyTotal - buyTotal)).toString();
    }

    private void writeToFile(String newFile, String data) {
        File file = new File(newFile);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(data);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
