package core.basesyntax;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int INDEX_OF_WORD = 0;
    private static final int INDEX_OF_NUMBER = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fromFileName));

            int supplySum = 0;
            int buySum = 0;

            for (String line : lines) {
                String[] parts = line.split(",");
                int extractedNumber = Integer.parseInt(parts[INDEX_OF_NUMBER]);

                if (parts[INDEX_OF_WORD].equals("supply")) {
                    supplySum += extractedNumber;
                } else if (parts[INDEX_OF_WORD].equals("buy")) {
                    buySum += extractedNumber;
                }
            }

            int result = supplySum - buySum;

            List<String> report = createReport(supplySum, buySum, result);
            writeReport(toFileName, report);

        } catch (IOException e) {
            throw new RuntimeException("Error while processing file: "
                    + e.getMessage(), e);
        }
    }

    private List<String> createReport(int supplySum, int buySum, int result) {
        List<String> report = new ArrayList<>();
        report.add("supply," + supplySum);
        report.add("buy," + buySum);
        report.add("result," + result);
        return report;
    }

    private void writeReport(String toFileName, List<String> report) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(toFileName))) {
            for (String line : report) {
                bufferedWriter.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while writing report to file: "
                    + e.getMessage(), e);
        }
    }
}
