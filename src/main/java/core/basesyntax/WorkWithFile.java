package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;
    private static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    public String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    public String createReport(String inputString) {
        String[] records = inputString.split(System.lineSeparator());
        int supplySum = 0;
        int buySum = 0;
        for (String record : records) {
            String[] splitted = record.split(CSV_SEPARATOR);
            if ("supply".equals(splitted[OPERATION_INDEX])) {
                supplySum += Integer.parseInt(splitted[QUANTITY_INDEX]);
            } else if ("buy".equals(splitted[OPERATION_INDEX])) {
                buySum += Integer.parseInt(splitted[QUANTITY_INDEX]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("supply").append(CSV_SEPARATOR).append(supplySum).append(System
                .lineSeparator());
        report.append("buy").append(CSV_SEPARATOR).append(buySum).append(System
                .lineSeparator());
        report.append("result").append(CSV_SEPARATOR).append(supplySum - buySum)
                        .append(System.lineSeparator());
        return report.toString();
    }

    public void writeToFile(String report, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.append(report);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
