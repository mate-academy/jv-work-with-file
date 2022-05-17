package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String VERIFICATION = "supply";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final int ARRAY_SIZE = 3;
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final int THIRD_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readData(fromFileName);
        int[] sortedData = countData(dataFromFile);
        String editedData = editData(sortedData);
        writeData(editedData, toFileName);
    }

    private List<String> readData(String fromFileName) {
        try {
            return Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private int[] countData(List<String> lines) {
        int[] data = new int[ARRAY_SIZE];
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[FIRST_INDEX].equals(VERIFICATION)) {
                data[FIRST_INDEX] += Integer.parseInt(parts[SECOND_INDEX]);
            } else {
                data[SECOND_INDEX] += Integer.parseInt(parts[SECOND_INDEX]);
            }
        }
        data[THIRD_INDEX] = data[FIRST_INDEX] - data[SECOND_INDEX];
        return data;
    }

    private String editData(int[] countedData) {
        return "supply," + countedData[FIRST_INDEX] + LINE_SEPARATOR
                + "buy," + countedData[SECOND_INDEX] + LINE_SEPARATOR
                + "result," + countedData[THIRD_INDEX];
    }

    private void writeData(String editedData, String toWriteFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toWriteFile))) {
            bufferedWriter.write(editedData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toWriteFile, e);
        }
    }
}
