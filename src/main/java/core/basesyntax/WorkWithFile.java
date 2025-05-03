package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_FOR_COLUMNS = ",";
    private static final int OPERATION_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final String BUY_TYPE = "buy";
    private static final String SUPPLY_TYPE = "supply";
    private static final String RESULT_TYPE = "result";
    private static final String WRITING_TO_FILE_ERROR = "Sorry, but i can't write in file";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFileToString(fromFileName);
        String supplyBuyInfo = calculateSupplyBuy(dataFromFile);
        writeToFile(supplyBuyInfo, toFileName);
    }

    private String readFileToString(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fileName, e);
        }
        return stringBuilder.toString();
    }

    private String calculateSupplyBuy(String informationFromFile) {
        String[] report = informationFromFile.split(System.lineSeparator());
        int supplyAmount = 0;
        int buyAmount = 0;

        for (String line : report) {
            String[] splitLine = line.split(SPLIT_FOR_COLUMNS);
            String operation = splitLine[OPERATION_INDEX];
            int amount = Integer.parseInt(splitLine[VALUE_INDEX]);

            buyAmount += operation.equals(BUY_TYPE) ? amount : 0;
            supplyAmount += operation.equals(SUPPLY_TYPE) ? amount : 0;
        }

        return createReport(supplyAmount, buyAmount);
    }

    private String createReport(int supply, int buy) {
        StringBuilder result = new StringBuilder();
        result.append(SUPPLY_TYPE).append(SPLIT_FOR_COLUMNS).append(supply)
                .append(System.lineSeparator());
        result.append(BUY_TYPE).append(SPLIT_FOR_COLUMNS).append(buy)
                .append(System.lineSeparator());
        result.append(RESULT_TYPE).append(SPLIT_FOR_COLUMNS).append(supply - buy)
                .append(System.lineSeparator());

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
