package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int OPERATION_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String[] readFromFile(String fromFileName) {
        File inputFile = new File(fromFileName);
        Path inputFilePath = Path.of(inputFile.toURI());
        List<String> lines;

        try {
            lines = Files.readAllLines(inputFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file named: " + fromFileName, e);
        }
        return lines.toArray(new String[]{});
    }

    private void writeToFile(String toFileName, String reportToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a file named: " + toFileName, e);
        }
    }

    private String createReport(String[] dataFromFile) {
        int supplySum = 0;
        int buySum = 0;

        for (String currentData : dataFromFile) {
            if (currentData.split(SEPARATOR)[OPERATION_TYPE_INDEX].equals("buy")) {
                buySum += Integer.parseInt(currentData.split(SEPARATOR)[OPERATION_VALUE_INDEX]);
            } else {
                supplySum += Integer.parseInt(currentData.split(SEPARATOR)[OPERATION_VALUE_INDEX]);
            }
        }
        return "supply," + supplySum + System.lineSeparator()
                + "buy," + buySum + System.lineSeparator()
                + "result," + (supplySum - buySum);
    }
}
