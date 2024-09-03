package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String CSV_DATA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;

        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);

        List<String> fileData = this.readFile(fromFile.toPath());

        for (String data : fileData) {
            String[] values = data.split(CSV_DATA_SEPARATOR);

            String operation = values[0];
            String amount = values[1];

            if (operation.toUpperCase().equals(Operation.BUY.toString())) {
                buyAmount += Integer.parseInt(amount);
            }

            if (operation.toUpperCase().equals(Operation.SUPPLY.toString())) {
                supplyAmount += Integer.parseInt(amount);
            }
        }

        String report = this.createReport(supplyAmount, buyAmount);

        this.writeToFile(toFile.toPath(), report.getBytes());
    }

    private String createReport(int supplyAmount, int buyAmount) {
        StringBuilder stringBuilder = new StringBuilder()
                .append("supply,")
                .append(supplyAmount)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buyAmount)
                .append(System.lineSeparator())
                .append("result,")
                .append(supplyAmount - buyAmount)
                .append(System.lineSeparator());

        return stringBuilder.toString();
    }

    private List<String> readFile(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading file", e);
        }
    }

    private void writeToFile(Path path, byte[] content) {
        try {
            Files.write(path, content);
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to file", e);
        }
    }
}
