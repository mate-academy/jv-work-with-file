package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OP_SUPPLY = "supply";
    private static final String OP_BUY = "buy";
    private static final String REP_RESULT = "result";
    private static final String CSV_DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeReportToFile(toFileName, readDataFromFile(fromFileName));
    }

    private Summary readDataFromFile(String fromFileName) {
        try (BufferedReader input = new BufferedReader(new FileReader(fromFileName))) {
            return parseOperations(input);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + fromFileName, e);
        }
    }

    private Summary parseOperations(BufferedReader input) throws IOException {
        int supplyAmount = 0;
        int buyAmount = 0;
        String line;

        while ((line = input.readLine()) != null) {
            String[] operationData = line.split(CSV_DELIMITER);

            if (operationData.length != 2) {
                continue;
            }

            try {
                int amount = Integer.parseInt(operationData[1]);
                switch (operationData[0]) {
                    case OP_SUPPLY -> supplyAmount += amount;
                    case OP_BUY -> buyAmount += amount;
                    default -> throw new IllegalArgumentException("Unknown operation: "
                            + operationData[0]);
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in line: " + line, e);
            }
        }

        return new Summary(supplyAmount, buyAmount, supplyAmount - buyAmount);
    }

    private void writeReportToFile(String toFileName, Summary summary) {
        try (BufferedWriter output = new BufferedWriter(new FileWriter(toFileName))) {
            output.write(OP_SUPPLY + CSV_DELIMITER + summary.supply());
            output.newLine();
            output.write(OP_BUY + CSV_DELIMITER + summary.buy());
            output.newLine();
            output.write(REP_RESULT + CSV_DELIMITER + summary.result());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + toFileName, e);
        }
    }
}
