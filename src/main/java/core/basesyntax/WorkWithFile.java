package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String REGEX_FOR_SPLITTING_DATA = "\\W+";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName).split(REGEX_FOR_SPLITTING_DATA);
        String report = createReport(dataFromFile);
        writeToFile(report, toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
        return stringBuilder.toString();
    }

    private String createReport(String[] dataFromFile) {
        int supply = 0;
        int buy = 0;
        for (int i = 0; i < dataFromFile.length; i++) {
            if (dataFromFile[i].equals(SUPPLY)) {
                supply += Integer.parseInt(dataFromFile[++i]);
            } else if (dataFromFile[i].equals(BUY)) {
                buy += Integer.parseInt(dataFromFile[++i]);
            }
        }
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeToFile(String report, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
