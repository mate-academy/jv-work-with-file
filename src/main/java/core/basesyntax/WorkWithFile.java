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
        StringBuilder report = createReport(inputData);
        writeToFile(toFileName, report);
    }

    private StringBuilder readFromFile(String fileName) {
        StringBuilder inputData = new StringBuilder();
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String readerFile = reader.readLine();
            if (readerFile == null) {
                throw new RuntimeException("File is empty");
            }
            while (readerFile != null) {
                inputData.append(readerFile).append(System.lineSeparator());
                readerFile = reader.readLine();
            }
            return inputData;
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private StringBuilder createReport(StringBuilder inputData) {
        String[] splitInfo = inputData.toString().split(System.lineSeparator());
        int supplyCount = 0;
        int buyCount = 0;
        for (int i = 0; i < splitInfo.length; i++) {
            String[] row = splitInfo[i].split(COMMA);
            if (SUPPLY.equals(row[0])) {
                supplyCount += Integer.parseInt(row[1]);
            }
            if (BUY.equals(row[0])) {
                buyCount += Integer.parseInt(row[1]);
            }
        }
        int result = supplyCount - buyCount;
        StringBuilder report = new StringBuilder();
        return report.append(SUPPLY).append(COMMA).append(supplyCount)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buyCount).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result);
    }

    private void writeToFile(String toFileName, StringBuilder report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("can't write to the file", e);
        }
    }
}
