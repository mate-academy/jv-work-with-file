package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        String report = generateReport(data);
        writeToFile(toFileName, report);
    }

    private String generateReport(String data) {
        StringBuilder report = new StringBuilder();
        String[] lines = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        int result;
        for (String line : lines) {
            String [] splitLine = line.split(",");
            if (splitLine[OPERATION_TYPE_INDEX].equals(BUY)) {
                buy += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            } else {
                supply += Integer.parseInt(splitLine[AMOUNT_INDEX]);
            }
        }
        result = supply - buy;
        report.append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result)
                .append(System.lineSeparator());
        return report.toString();
    }

    private String readFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            stringBuilder.append(line);
            line = reader.readLine();
            while (line != null) {
                stringBuilder.append(System.lineSeparator()).append(line);
                line = reader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("There was an error reading data from the file" + fileName);
        }
    }

    private void writeToFile(String fileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("There was an error writing data to the file" + fileName);
        }
    }
}
