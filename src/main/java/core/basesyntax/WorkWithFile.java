package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String resultString = readSourceFile(fromFileName);
        writeResultToFile(resultString, toFileName);
    }

    private String readSourceFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supplyAmount = 0;
            int buyAmount = 0;
            String row = reader.readLine();
            String[] rowData;
            StringBuilder resultBuilder = new StringBuilder();

            while (row != null) {
                rowData = row.split(",");
                if (rowData[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                    supplyAmount += Integer.parseInt(rowData[AMOUNT_INDEX]);
                }
                if (rowData[OPERATION_TYPE_INDEX].equals(BUY)) {
                    buyAmount += Integer.parseInt(rowData[AMOUNT_INDEX]);
                }
                row = reader.readLine();
            }

            resultBuilder.append(SUPPLY + ",").append(supplyAmount)
                    .append(System.lineSeparator())
                    .append(BUY + ",").append(buyAmount)
                    .append(System.lineSeparator())
                    .append(RESULT + ",").append(supplyAmount - buyAmount)
                    .append(System.lineSeparator());
            return resultBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
    }

    private void writeResultToFile(String lineToWrite, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWriter.write(lineToWrite);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
