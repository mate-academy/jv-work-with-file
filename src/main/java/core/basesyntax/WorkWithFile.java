package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int ZERO_INDEX = 0;
    static final int FIRST_INDEX = 1;
    static final String CSV_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String readString = readFromFile(fromFileName);
        String reportString = createReport(readString);
        writeToFile(reportString, toFileName);
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
            if ("supply".equals(splitted[ZERO_INDEX])) {
                supplySum += Integer.parseInt(splitted[FIRST_INDEX]);
            } else if ("buy".equals(splitted[ZERO_INDEX])) {
                buySum += Integer.parseInt(splitted[FIRST_INDEX]);
            }
        }
        StringBuilder tempStr = new StringBuilder();
        tempStr.append("supply").append(CSV_SEPARATOR).append(supplySum).append(System
                .lineSeparator());
        tempStr.append("buy").append(CSV_SEPARATOR).append(buySum).append(System
                .lineSeparator());
        tempStr.append("result").append(CSV_SEPARATOR).append(supplySum - buySum)
                        .append(System.lineSeparator());
        return tempStr.toString();
    }

    public void writeToFile(String report, String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.append(report);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
