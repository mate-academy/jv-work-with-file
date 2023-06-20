package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String reportData = readFromFile(fromFileName);
        String report = createReport(reportData);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        int supply = 0;
        int buy = 0;
        File file = new File(fromFileName);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String readeValue = reader.readLine();
                while (readeValue != null) {
                    String[] bufferString = readeValue.split(COMMA);
                    if (bufferString[0].equals(SUPPLY)) {
                        supply += Integer.parseInt(bufferString[1]);
                    }
                    if (bufferString[0].equals(BUY)) {
                        buy += Integer.parseInt(bufferString[1]);
                    }
                    readeValue = reader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't read file" + fromFileName, e);
            }
        }
        return supply + " " + buy;
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private String createReport(String reportData) {
        String[] report = reportData.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(report[0])
                .append(System.lineSeparator());
        stringBuilder.append(BUY).append(COMMA).append(report[1])
                .append(System.lineSeparator());
        stringBuilder.append(RESULT).append(COMMA)
                .append(Integer.parseInt(report[0]) - Integer.parseInt(report[1]));
        return stringBuilder.toString();
    }
}
