package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> stringList = readFromFile(fromFileName);
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String line : stringList) {
            String[] operations = line.split(SEPARATOR);
            if (operations[0].equals(SUPPLY)) {
                supplyCounter += Integer.parseInt(operations[1]);
            } else if (operations[0].equals(BUY)) {
                buyCounter += Integer.parseInt(operations[1]);
            } else {
                throw new RuntimeException("Invalid data to read.");
            }
        }
        String reportText = getReport(supplyCounter, buyCounter);
        writeReportToFile(toFileName, reportText);
    }

    private List<String> readFromFile(String fromFileName) {
        List<String> stringList;
        try {
            stringList = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }
        return stringList;
    }

    private String getReport(int supplyCounter, int buyCounter) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(SUPPLY).append(SEPARATOR)
                .append(supplyCounter).append(System.lineSeparator())
                .append(BUY).append(SEPARATOR).append(buyCounter).append(System.lineSeparator())
                .append(RESULT).append(SEPARATOR).append(supplyCounter - buyCounter);
        return reportBuilder.toString();
    }

    private void writeReportToFile(String toFileName, String reportText) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(reportText);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
