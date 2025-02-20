package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DATA_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    private int[] results = new int[2];

    public void getStatistic(String fromFileName, String toFileName) {
        results[0] = 0;
        results[1] = 0;
        readDataFromFile(fromFileName);
        writeResults(toFileName);
    }

    private void readDataFromFile(String fileNameForReading) {
        try (FileReader fileReader = new FileReader(fileNameForReading);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                readDataFromLine(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private void writeResults(String fileNameForWriting) {
        try (FileWriter fileWriter = new FileWriter(fileNameForWriting);
                BufferedWriter writer = new BufferedWriter(fileWriter)) {
            writer.write(createResult());
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }

    private String createResult() {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(DATA_SEPARATOR).append(results[0])
                .append(System.lineSeparator()).append(BUY).append(DATA_SEPARATOR)
                .append(results[1]).append(System.lineSeparator()).append(RESULT)
                .append(DATA_SEPARATOR).append(results[0] - results[1]);
        return builder.toString();
    }

    private void readDataFromLine(String line) {
        String[] dataFromLine = line.split(DATA_SEPARATOR);
        int cellNumber = dataFromLine[0].equals(SUPPLY) ? 0 : 1;
        results[cellNumber] += Integer.parseInt(dataFromLine[1]);
    }
}
