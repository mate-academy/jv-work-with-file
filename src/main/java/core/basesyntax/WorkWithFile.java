package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    private static final String CSV_COMMON_REGEX = ",";
    private static final String CSV_ITEM_SUPPLY = "supply";
    private static final String CSV_ITEM_BUY = "buy";
    private static final String CSV_ITEM_RESULT = "result";

    private String createReport(List<String> allLines) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : allLines) {
            String[] itemToArray = line.split(CSV_COMMON_REGEX);
            if (itemToArray[0].equals(CSV_ITEM_SUPPLY)) {
                totalSupply += Integer.parseInt(itemToArray[1]);
            } else {
                totalBuy += Integer.parseInt(itemToArray[1]);
            }
        }
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(CSV_ITEM_SUPPLY).append(CSV_COMMON_REGEX).append(totalSupply)
                .append(System.lineSeparator());
        reportBuilder.append(CSV_ITEM_BUY).append(CSV_COMMON_REGEX).append(totalBuy)
                .append(System.lineSeparator());
        reportBuilder.append(CSV_ITEM_RESULT).append(CSV_COMMON_REGEX).append(totalSupply - totalBuy)
                .append(System.lineSeparator());
        return reportBuilder.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> inputData;
        try {
            inputData = Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from input file." + fromFileName, e);
        }
        String Report = createReport(inputData);
        try {
            Files.write(Paths.get(toFileName), Report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write output file " + toFileName, e);
        }
    }
}