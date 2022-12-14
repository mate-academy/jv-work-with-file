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

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName).split(" ");
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException ex) {
            throw new RuntimeException("Cant read file" + fromFileName, ex);
        }
        return builder.toString();
    }

    private String createReport(String[] dataFromFile) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        for (String line : dataFromFile) {
            if (line.substring(0, line.indexOf(',')).equals(SUPPLY)) {
                supply += Integer.parseInt(line.substring(line.indexOf(',') + 1));
            }
            if (line.substring(0, line.indexOf(',')).equals(BUY)) {
                buy += Integer.parseInt(line.substring(line.indexOf(',') + 1));
            }
        }
        result = supply - buy;
        StringBuilder reportBuilder = new StringBuilder();
        return reportBuilder.append(SUPPLY).append(",")
                .append(supply).append(System.lineSeparator())
                .append(BUY).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT).append(",").append(result).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file:" + toFileName, e);
        }
    }
}
