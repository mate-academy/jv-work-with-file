package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int ACTION_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> listOfData = readFromFile(fromFileName);
        String report = createReport(listOfData);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file.", e);
        }
    }

    private void writeToFile(String fileName, String report) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
                bufferedWriter.write(report);
            } catch (IOException e) {
                throw new RuntimeException("Can't write file.", e);
            }
    }

    private String createReport(List<String> listOfData) {
        int supply = 0;
        int buy = 0;
        for (String data : listOfData) {
            String[] splitData = data.split(",");
            int value = Integer.parseInt(splitData[VALUE_INDEX]);
            if (splitData[ACTION_INDEX].equals("buy")) {
                buy += value;
            } else {
                supply += value;
            }
        }
        return new StringBuilder("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy)
                .toString();
    }
}