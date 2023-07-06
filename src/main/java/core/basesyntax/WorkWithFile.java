package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String READING_FROM_FILE_ERROR = "I can't read from this file any "
                                                          + "information.";
    private static final String SPLIT_FOR_COLUMNS = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String BUY_TYPE = "buy";
    private static final String SUPPLY_TYPE = "supply";
    private static final String WRITE_REPORT_ERROR = "Sorry, but I can't work with this data.";
    private static final String RESULT_TYPE = "result";
    private static final String WRITING_TO_FILE_ERROR = "Sorry, but i can't write in file";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFileToString(fromFileName);
        int[] supplyBuyInfo = calculateSupplyBuy(dataFromFile);
        String report = writeReport(supplyBuyInfo);
        writeToFile(report, toFileName);
    }

    private static String[] readFileToString(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line = reader.readLine();
            StringBuilder result = new StringBuilder();
            while (line != null) {
                result.append(line).append(".");
                line = reader.readLine();
            }
            return result.toString().split("\\.");
        } catch (IOException e) {
            throw new RuntimeException(READING_FROM_FILE_ERROR);
        } catch (RuntimeException e) {
            throw new RuntimeException("There were some problems during the execution of the program " + e);
        }
    }

    private static int[] calculateSupplyBuy(String[] informationFromFile) {
        int supplyAmount = 0;
        int buyAmount = 0;

        for (String line : informationFromFile) {
            String[] splitLine = line.split(SPLIT_FOR_COLUMNS);
            String operation = splitLine[OPERATION_INDEX];
            int amount = Integer.parseInt(splitLine[VALUE_INDEX]);

            buyAmount += operation.equals(BUY_TYPE) ? amount : 0;
            supplyAmount += operation.equals(SUPPLY_TYPE) ? amount : 0;
        }

        return new int[] {supplyAmount, buyAmount};
    }

    private static String writeReport(int[] supplyAndBuy) {
        if (supplyAndBuy.length != 2) {
            throw new RuntimeException(WRITE_REPORT_ERROR);
        }
        int supply = supplyAndBuy[0];
        int buy = supplyAndBuy[1];

        StringBuilder result = new StringBuilder();
        result.append(SUPPLY_TYPE).append(SPLIT_FOR_COLUMNS).append(supply).append(System.lineSeparator());
        result.append(BUY_TYPE).append(SPLIT_FOR_COLUMNS).append(buy).append(System.lineSeparator());
        result.append(RESULT_TYPE).append(SPLIT_FOR_COLUMNS).append(supply - buy).append(System.lineSeparator());

        return String.valueOf(result);
    }

    private void writeToFile(String message, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(message);
        } catch (IOException e) {
            throw new RuntimeException(WRITING_TO_FILE_ERROR + fileName);
        }
    }
}
