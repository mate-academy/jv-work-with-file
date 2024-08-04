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

    public void getStatistic(String fromFileName, String toFileName) {
        int[] dataFromFile = readDataFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeDataToFile(toFileName, report);
    }

    private int[] readDataFromFile(String fromFileName) {
        int sumOfSupply = 0;
        int sumOfBuy = 0;
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
            throw new RuntimeException("Can't read data from " + fromFileName, e);
        }
        return new int[]{sumOfSupply, sumOfBuy};
    }

    private String createReport(int[] dataFromFile) {
        StringBuilder builder = new StringBuilder();
        int sumOfSupply = dataFromFile[0];
        int sumOfBuy = dataFromFile[1];
        int result = sumOfSupply - sumOfBuy;
        return builder.append(SUPPLY).append(COMMA).append(sumOfSupply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(sumOfBuy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(result).toString();
    }

    private void writeDataToFile(String toFileName, String report) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to " + toFileName, e);
        }
    }
}
