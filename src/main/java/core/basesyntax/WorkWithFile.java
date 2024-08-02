package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";
    private int sumOfSupply = 0;
    private int sumOfBuy = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readDataFromFile(fromFileName);
        String report = createReport(sumOfSupply, sumOfBuy);
        writeDataToFile(toFileName, report);
        resetCalculations();
    }

    private void readDataFromFile(String fromFileName) {
        try {
            List<String> data = Files.readAllLines(Path.of(fromFileName));
            for (String line : data) {
                String[] splitLine = line.split(COMMA);
                if (splitLine[0].equals(SUPPLY)) {
                    sumOfSupply += Integer.parseInt(splitLine[1]);
                }
                if (splitLine[0].equals(BUY)) {
                    sumOfBuy += Integer.parseInt(splitLine[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }
    }

    private String createReport(int sumOfSupply, int sumOfBuy) {
        int result = sumOfSupply - sumOfBuy;
        StringBuilder builder = new StringBuilder();
        return builder.append(SUPPLY).append(COMMA).append(sumOfSupply)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(sumOfBuy).append(System.lineSeparator())
                .append(RESULT).append(',').append(result).toString();
    }

    private void writeDataToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file", e);
        }
    }

    private void resetCalculations() {
        sumOfSupply = 0;
        sumOfBuy = 0;
    }
}
