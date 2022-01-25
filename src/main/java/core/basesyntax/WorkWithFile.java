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

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(createReport(countData(readFromFile(fromFileName))), toFileName);
    }

    private String countData(String data) {
        StringBuilder countBuilder = new StringBuilder();
        String[] sepData = data.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        for (String datum : sepData) {
            sepData = datum.split(COMMA);
            if (sepData[0].contains(SUPPLY)) {
                supply += Integer.parseInt(sepData[1]);
            }
            if (sepData[0].contains(BUY)) {
                buy += Integer.parseInt(sepData[1]);
            }
        }
        int result = supply - buy;
        return countBuilder.append(supply).append(COMMA).append(buy)
                .append(COMMA).append(result).toString();
    }

    private String readFromFile(String fromFile) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String file;
            while ((file = reader.readLine()) != null) {
                builder.append(file).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read", e);
        }
        return builder.toString();
    }

    private void writeToFile(String report, String toFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("File cannot be write", e);
        }
    }

    private String createReport(String data) {
        StringBuilder reportBuilder = new StringBuilder();
        String[] elements = data.split(COMMA);
        reportBuilder.append(SUPPLY).append(COMMA).append(elements[0]).append("\n")
                .append(BUY).append(COMMA).append(elements[1])
                .append("\n").append(RESULT).append(COMMA).append(elements[2]);
        return reportBuilder.toString();
    }
}
