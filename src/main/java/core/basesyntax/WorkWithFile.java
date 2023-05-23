package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int buyCount = OPERATION_INDEX;
        int supplyCount = OPERATION_INDEX;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(SEPARATOR);
                if (data[OPERATION_INDEX].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(data[AMOUNT_INDEX]);
                } else {
                    buyCount += Integer.parseInt(data[AMOUNT_INDEX]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file", e);
        }

        String reportString = buildReportString(supplyCount, buyCount);

        try {
            writeReportToFile(reportString, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }

    private String buildReportString(int supplyCount, int buyCount) {
        String reportString = "";
        reportString += SUPPLY + SEPARATOR + supplyCount + System.lineSeparator();
        reportString += BUY + SEPARATOR + buyCount + System.lineSeparator();
        reportString += RESULT + SEPARATOR + (supplyCount - buyCount) + System.lineSeparator();

        return reportString;
    }

    private void writeReportToFile(String reportString, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(reportString);
        }
    }
}
