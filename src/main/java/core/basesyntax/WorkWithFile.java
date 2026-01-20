package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] statistics = readStatistics(fromFileName);
        List<String> report = createReport(statistics);
        writeReport(toFileName, report);
    }

    private int[] readStatistics(String fileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String value;
            while ((value = reader.readLine()) != null) {
                String[] valueParts = value.split(",");
                int amount = Integer.parseInt(valueParts[AMOUNT_INDEX]);

                if (BUY.equals(valueParts[OPERATION_INDEX])) {
                    totalBuy += amount;
                } else if (SUPPLY.equals(valueParts[OPERATION_INDEX])) {
                    totalSupply += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }

        return new int[] {totalSupply, totalBuy};
    }

    private List<String> createReport(int[] statistics) {
        int totalSupply = statistics[0];
        int totalBuy = statistics[1];
        int result = totalSupply - totalBuy;

        return List.of(
                "supply," + totalSupply,
                "buy," + totalBuy,
                "result," + result
        );
    }

    private void writeReport(String fileName, List<String> report) {
        try {
            Files.write(Path.of(fileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
