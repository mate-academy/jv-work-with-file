package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String[] OPERATION_NAME = {"supply", "buy"};
    private static final String DELIMITER = ",";
    private static final String RESULT_NAME = "result";
    private StringBuilder report;
    private int firstTotal;
    private int secondTotal;

    public void getStatistic(String fromFileName, String toFileName) {
        report = new StringBuilder();
        firstTotal = 0;
        secondTotal = 0;
        readFile(fromFileName);
        createReport();
        writeToFile(toFileName);
    }

    private void readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            line = reader.readLine();
            while (line != null) {
                calculateTotal(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + e);
        }
    }

    private void calculateTotal(String line) {
        String amount;
        if (line.contains(OPERATION_NAME[0])) {
            amount = line.split(DELIMITER)[1];
            firstTotal += Integer.parseInt(amount);
        } else {
            amount = line.split(DELIMITER)[1];
            secondTotal += Integer.parseInt(amount);
        }
    }

    private void createReport() {
        report.append(OPERATION_NAME[0]).append(DELIMITER)
                .append(firstTotal).append(System.lineSeparator())
                .append(OPERATION_NAME[1]).append(DELIMITER)
                .append(secondTotal).append(System.lineSeparator())
                .append(RESULT_NAME).append(DELIMITER)
                .append(firstTotal - secondTotal);
    }

    private void writeToFile(String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName + e);
        }
    }
}
