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
        String[] data = readFromFile(fromFileName).split(System.lineSeparator());
        String[] sepData;
        int supply = 0;
        int buy = 0;
        for (String datum : data) {
            sepData = datum.split(COMMA);
            if (sepData[0].contains(SUPPLY)) {
                supply += Integer.parseInt(sepData[1]);
            }
            if (sepData[0].contains(BUY)) {
                buy += Integer.parseInt(sepData[1]);
            }
        }
        int result = supply - buy;
        writeToFile(toFileName, supply, buy, result);
    }

    private String readFromFile(String fromFile) {
        File fileName = new File(fromFile);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String file;
            while ((file = reader.readLine()) != null) {
                builder.append(file).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read", e);
        }
        return builder.toString();
    }

    private void writeToFile(String toFile, int supply, int buy, int result) {
        File fileName = new File(toFile);
        StringBuilder builder = new StringBuilder();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            builder.append(SUPPLY).append(COMMA).append(supply).append("\n")
                    .append(BUY).append(COMMA).append(buy)
                    .append("\n").append(RESULT).append(COMMA).append(result);
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("File cannot be write", e);
        }
    }
}
