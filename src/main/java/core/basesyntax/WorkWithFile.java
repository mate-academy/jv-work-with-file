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
    private static final String SPLITTER = "/";
    private static final String OPERATION_SUPPLY = "supply";
    private static final String OPERATION_BUY = "buy";
    private static final String OPERATION_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readAllFile(fromFileName);
        int[] amounts = countTotalAmount(dataFromFile);
        String[] report = createReport(amounts[OPERATION_TYPE_SYPPLY], amounts[OPERATION_TYPE_BUY]);
        writeToFile(toFileName, report);
    }

    private String[] readAllFile(String fromFileName) {
        StringBuilder dataFromFile = new StringBuilder();
        String lineFromFile;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            lineFromFile = bufferedReader.readLine();
            while (lineFromFile != null) {
                dataFromFile.append(lineFromFile).append(SPLITTER);
                lineFromFile = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataFromFile.toString().split(SPLITTER);
    }

    private int[] countTotalAmount(String[] dataFromFile) {
        int[] valuesOfCertainOperation = new int[AMOUNT_OF_OPERATIONS];
        for (String currentDataFromFile: dataFromFile) {
            String[] splitString = currentDataFromFile.split(COMA);
            if (splitString[OPERATION_TYPE_SYPPLY].equals(OPERATION_SUPPLY)) {
                valuesOfCertainOperation[OPERATION_TYPE_SYPPLY] +=
                        Integer.parseInt(splitString[AMOUNT_FROM_STRING]);
            } else {
                valuesOfCertainOperation[OPERATION_TYPE_BUY] +=
                        Integer.parseInt(splitString[AMOUNT_FROM_STRING]);
            }
        }
        return valuesOfCertainOperation;
    }

    private String[] createReport(int amountOfSupply, int amountOfBuy) {
        String[] report = new String[3];
        report[0] = OPERATION_SUPPLY + COMA + amountOfSupply;
        report[1] = OPERATION_BUY + COMA + amountOfBuy;
        report[2] = OPERATION_RESULT + COMA + (amountOfSupply - amountOfBuy);
        return report;
    }

    private void writeToFile(String toFileName, String[] report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String reportForCertainOperation: report) {
                bufferedWriter.write(reportForCertainOperation);
                bufferedWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("File not found", e);
        }
    }
}
