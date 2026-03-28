package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] sums = readData(fromFileName);

        String report = buildReport(sums[0], sums[1]);

        writeReport(toFileName, report);
    }

    private int[] readData(String fromFileName) {
        int supplyInt = 0;
        int buyInt = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String type = parts[0];
                int amount = Integer.parseInt(parts[1]);

                if (SUPPLY.equals(type)) {
                    supplyInt += amount;
                } else if (BUY.equals(type)) {
                    buyInt += amount;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: "
                    + fromFileName, e);
        }

        return new int[] { supplyInt, buyInt };
    }

    private String buildReport(int supplyInt, int buyInt) {
        int resultInt = supplyInt - buyInt;
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(",").append(supplyInt).append("\n");
        report.append(BUY).append(",").append(buyInt).append("\n");
        report.append(RESULT).append(",").append(resultInt).append("\n");
        return report.toString();
    }

    private void writeReport(String toFileName, String report) {
        try {
            Files.write(Paths.get(toFileName),
                    report.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file: "
                    + toFileName, e);
        }
    }
}

