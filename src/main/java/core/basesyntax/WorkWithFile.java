package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT_OPERATION = "result";
    private static final String DELIMITER = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String report = createReportFromFile(fromFileName);
        writeReport(report, toFileName);
    }

    private String createReportFromFile(String fromFileName) {
        try {
            return calculateReport(Files.readAllLines(Path.of(fromFileName)));
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file", e);
        }
    }

    private void writeReport(String report, String toFileName) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file", e);
        }
    }

    private String calculateReport(List<String> lines) {
        int totalSupply = 0;
        int totalBuy = 0;
        int amount;
        for ( String line : lines) {
            String[] columns = line.split(DELIMITER);
            if (columns.length == 2) {
                if (columns[OPERATION_INDEX].equals(SUPPLY_OPERATION)) {
                    totalSupply += Integer.parseInt(columns[AMOUNT_INDEX]);
                }
                else if (columns[OPERATION_INDEX].equals(BUY_OPERATION)) {
                    totalBuy += Integer.parseInt(columns[AMOUNT_INDEX]);
                }
            }
        }
        amount = totalSupply - totalBuy;
        return formatReport(totalSupply,totalBuy,amount);
    }

    private String formatReport(int supply, int buy, int result) {
        return SUPPLY_OPERATION + DELIMITER + supply + System.lineSeparator() +
                BUY_OPERATION + DELIMITER + buy + System.lineSeparator() +
                RESULT_OPERATION + DELIMITER + result;
    }
}
