package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR_DATA = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final String RESULT = "result";
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supplyAmount = 0;
        int buyAmount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String oneLine = reader.readLine();
            while (oneLine != null) {
                String[] line = oneLine.split(SEPARATOR_DATA);
                if (line[OPERATION_INDEX].equals(SUPPLY_OPERATION)) {
                    supplyAmount = supplyAmount + Integer.parseInt(line[AMOUNT_INDEX]);
                } else {
                    buyAmount = buyAmount + Integer.parseInt(line[AMOUNT_INDEX]);
                }
                oneLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Reading file was failed ", e);
        }
        String resultBuilder = SUPPLY_OPERATION + SEPARATOR_DATA + supplyAmount +
                System.lineSeparator() +
                BUY_OPERATION + SEPARATOR_DATA + buyAmount +
                System.lineSeparator() +
                RESULT + SEPARATOR_DATA + (supplyAmount - buyAmount);
        writeToFile(toFileName, resultBuilder);
    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Write to file was canceled " + e);
        }
    }
}
