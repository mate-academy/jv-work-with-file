package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder inputData = readFromFile(fromFileName);
        String report = createReport(inputData.toString());
        writeToFile(toFileName, report);
    }

    private StringBuilder readFromFile(String fileName) {
        StringBuilder inputData = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null) {
                throw new RuntimeException("File is empty" + fileName);
            }
            while (line != null) {
                inputData.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }
            return inputData;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
    }

    private String createReport(String inputData) {
        String[] row = inputData.toString().split(System.lineSeparator());
        int supplyCount = 0;
        int buyCount = 0;
        for (String r : row) {
            String[] columns = r.split(COMMA);
            if (SUPPLY.equals(columns[0])) {
                supplyCount += Integer.parseInt(columns[1]);
            }
            if (BUY.equals(columns[0])) {
                buyCount += Integer.parseInt(columns[1]);
            }
        }
        int result = supplyCount - buyCount;
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY).append(COMMA).append(supplyCount)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString();
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("can't write to the file" + toFileName, e);
        }
    }
}
