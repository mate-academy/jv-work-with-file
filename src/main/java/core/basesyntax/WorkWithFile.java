package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fromFileContent = readFile(fromFileName);
        String reportData = generateReport(fromFileContent);
        writeToFile(toFileName, reportData);
    }

    private List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read " + fileName, e);
        }
        return lines;
    }

    private void writeToFile(String fileName, String reportData) {
        File file = new File(fileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to " + fileName, e);
        }
    }

    private String generateReport(List<String> input) {
        int supplyTotal = 0;
        int buyTotal = 0;

        for (String record : input) { // Use enhanced for loop to iterate through List
            String[] parts = record.split(",");
            String key = parts[OPERATION_TYPE_INDEX];
            int value = Integer.parseInt(parts[AMOUNT_INDEX]);

            if (key.equals("supply")) {
                supplyTotal += value;
            } else if (key.equals("buy")) {
                buyTotal += value;
            }
        }

        int result = supplyTotal - buyTotal;

        return String.format("supply,%d%nbuy,%d%nresult,%d%n", supplyTotal, buyTotal, result);
    }
}
