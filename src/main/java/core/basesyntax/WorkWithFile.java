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
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(report,toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return stringBuilder.toString().split("\\W");
    }

    private String createReport(String[] dataFromFile) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataFromFile.length; i++) {
            if (dataFromFile[i].equals(SUPPLY)) {
                supply += Integer.parseInt(dataFromFile[i + 1]);
            } else if (dataFromFile[i].equals(BUY)) {
                buy += Integer.parseInt(dataFromFile[i + 1]);
            }
        }
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY).append(COMMA)
                .append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString();
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException ex) {
            throw new RuntimeException("Can't write file" + toFileName, ex);
        }
    }
}
