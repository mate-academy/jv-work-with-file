package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final String SUPPLY_OPERATION = "supply";
    static final String BUY_OPERATION = "buy";
    static final String DELIMITER = ",";
    static final String RESULT_OPERATION = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = createReport(dataFromFile);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder data = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file with name " + fromFileName, e);
        }

        return data.toString();
    }

    private String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;

        String[] lines = dataFromFile.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(DELIMITER);
            String operation = parts[0];
            int amount = Integer.parseInt(parts[1]);

            if (operation.equals(SUPPLY_OPERATION)) {
                supply += amount;
            } else if (operation.equals(BUY_OPERATION)) {
                buy += amount;
            }
        }

        int result = supply - buy;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY_OPERATION)
                .append(DELIMITER).append(supply).append(System.lineSeparator());
        report.append(BUY_OPERATION)
                .append(DELIMITER).append(buy).append(System.lineSeparator());
        report.append(RESULT_OPERATION)
                .append(DELIMITER).append(result).append(System.lineSeparator());

        return report.toString();
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to the file with name " + toFileName, e);
        }
    }
}
