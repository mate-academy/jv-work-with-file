package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = " ";
    private static final String DELIMITER = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName,report);
    }

    private String readFile(String filePath) {
        StringBuilder content = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String value = reader.readLine();

            while (value != null) {
                content.append(value).append(SEPARATOR);
                value = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("File " + filePath + "is not found ",e);
        }
        return content.toString();
    }

    public String generateReport(String data) {
        int supply = 0;
        int buy = 0;
        String[] rows = data.split("\\s+");

        for (String splitSaveData : rows) {
            String[] splitValue = splitSaveData.split(DELIMITER);
            String operationType = splitValue[OPERATION_TYPE_INDEX];
            int amount = Integer.parseInt(splitValue[AMOUNT_INDEX]);

            if (operationType.equals("supply")) {
                supply += amount;
            } else if (operationType.equals("buy")) {
                buy += amount;
            }
        }

        StringBuilder reportBuilder = new StringBuilder();

        reportBuilder
                .append("supply")
                .append(DELIMITER)
                .append(supply)
                .append(System.lineSeparator());
        reportBuilder
                .append("buy")
                .append(DELIMITER)
                .append(buy)
                .append(System.lineSeparator());
        reportBuilder
                .append("result")
                .append(DELIMITER)
                .append(supply - buy)
                .append(System.lineSeparator());

        return reportBuilder.toString();
    }

    public void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
