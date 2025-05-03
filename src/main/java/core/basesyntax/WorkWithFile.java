package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = System.lineSeparator();
    private static final String DELIMITER = ",";
    private static final String ROW_RESULT = "result";
    private static final String ROW_SUPPLY = "supply";
    private static final String ROW_BUY = "buy";
    private static final int VALUE_INDEX = 1;
    private static final int LABEL_INDEX = 0;

    public String getStatistic(String fromFileName, String toFileName) {
        String fromFileData = readFromFile(fromFileName);
        String report = generateReport(fromFileData);
        writeToFile(toFileName, report);
        return fromFileData;
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    private String generateReport(String fromFileData) {
        StringBuilder report = new StringBuilder();
        String[] data = fromFileData.split(SEPARATOR);
        int supply = 0;
        int buy = 0;
        int result = 0;

        for (String line : data) {
            String[] splitLine = line.split(DELIMITER);
            switch (splitLine[LABEL_INDEX]) {
                case ROW_SUPPLY:
                    supply += Integer.parseInt(splitLine[VALUE_INDEX]);
                    break;
                case ROW_BUY:
                    buy += Integer.parseInt(splitLine[VALUE_INDEX]);
                    break;
                default:
                    System.out.println("There is no acceptable option");
            }
        }

        result = supply - buy;
        report.append(ROW_SUPPLY).append(DELIMITER).append(supply).append(SEPARATOR)
                .append(ROW_BUY).append(DELIMITER).append(buy).append(SEPARATOR)
                .append(ROW_RESULT).append(DELIMITER).append(result).append(SEPARATOR);
        return report.toString();
    }
}
