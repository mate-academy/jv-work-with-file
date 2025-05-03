package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final int NAME = 0;
    private static final int NUMBER = 1;
    private static final String COMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileContent = readFile(fromFileName);
        String report = getResult(fileContent);
        writeToFile(toFileName, report);
    }

    private List<String> readFile(String fromFileName) {
        try {
            List<String> lines = Files.readAllLines(Path.of(fromFileName));
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private String getResult(List<String> lines) {
        int supplySum = 0;
        int buySum = 0;
        for (String line : lines) {
            String[] parts = line.split(COMA);
            if (parts[NAME].equals(SUPPLY)) {
                supplySum += Integer.parseInt(parts[NUMBER]);
            }
            if (parts[NAME].equals(BUY)) {
                buySum += Integer.parseInt(parts[NUMBER]);
            }
        }
        int result = supplySum - buySum;
        StringBuilder resultReport = new StringBuilder();
        resultReport.append(SUPPLY).append(COMA).append(supplySum).append(System.lineSeparator());
        resultReport.append(BUY).append(COMA).append(buySum).append(System.lineSeparator());
        resultReport.append(RESULT).append(COMA).append(result);
        return resultReport.toString();
    }

    private void writeToFile(String toFileName, String resultReport) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(resultReport);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
