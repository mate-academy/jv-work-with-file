package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String CSV_REGEX = ",";
    private static final String ITEM_SUPPLY = "supply";
    private static final String ITEM_BUY = "buy";
    private static final String ITEM_RESULT = "result";
    private static final int ACTION_INDEX = 0;
    private static final int AMOUNT_ACTION_INDEX = 1;

    private String createReport(List<String> allLines) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : allLines) {
            String[] itemToArray = line.split(CSV_REGEX);
            if (itemToArray[ACTION_INDEX].equals(ITEM_SUPPLY)) {
                totalSupply += Integer.parseInt(itemToArray[AMOUNT_ACTION_INDEX]);
            } else {
                totalBuy += Integer.parseInt(itemToArray[AMOUNT_ACTION_INDEX]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(ITEM_SUPPLY).append(CSV_REGEX).append(totalSupply)
                .append(System.lineSeparator())
                .append(ITEM_BUY).append(CSV_REGEX).append(totalBuy)
                .append(System.lineSeparator())
                .append(ITEM_RESULT).append(CSV_REGEX).append(totalSupply - totalBuy)
                .append(System.lineSeparator());
        return reportBuilder.toString();
    }

    private List<String> readDataFromFile(String fromFileName) {
        try {
            return (Files.readAllLines(Paths.get(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from input file." + fromFileName, e);
        }
    }

    private void saveReportToFile(String report, String toFileName) {
        try {
            Files.write(Paths.get(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write output file " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> inputData = readDataFromFile(fromFileName);
        String report = createReport(inputData);
        saveReportToFile(report, toFileName);
    }
}
