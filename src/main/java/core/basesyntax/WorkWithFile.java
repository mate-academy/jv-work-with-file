package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_SYPPLY = 0;
    private static final int OPERATION_TYPE_BUY = 1;
    private static final int AMOUNT_OF_OPERATIONS = 2;
    private static final int AMOUNT_FROM_STRING = 1;
    private static final String COMA = ",";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] amounts = countTotalAmount(fromFileName);
        String[] report = createReport(amounts[OPERATION_TYPE_SYPPLY], amounts[OPERATION_TYPE_BUY]);
        writeToFile(toFileName, report);
    }

    private void writeToFile(String toFileName, String[] report) {
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            for (String s : report) {
                bufferedWriter.write(s);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferWriter", e);
                }
            }
        }
    }

    private int[] countTotalAmount(String fromFileName) {
        int[] amounts = new int[AMOUNT_OF_OPERATIONS];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            String lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                String[] splitString = lineFromFile.split(COMA);
                if (checkOperationType(splitString[OPERATION_TYPE_SYPPLY])) {
                    amounts[OPERATION_TYPE_SYPPLY] +=
                            Integer.parseInt(splitString[AMOUNT_FROM_STRING]);
                } else {
                    amounts[OPERATION_TYPE_BUY] +=
                            Integer.parseInt(splitString[AMOUNT_FROM_STRING]);
                }
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Empty line", e);
        }
        return amounts;
    }

    private String[] createReport(int amountOfSupply, int amountOfBuy) {
        String[] report = new String[3];
        report[0] = OPERATION_SUPPLY + COMA + amountOfSupply;
        report[1] = OPERATION_BUY + COMA + amountOfBuy;
        report[2] = OPERATION_RESULT + COMA + (amountOfSupply - amountOfBuy);
        return report;
    }

    private boolean checkOperationType(String operation) {
        return operation.equals(OPERATION_SUPPLY);
    }
}
