package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_OF_BUSINESS_TYPE_PARAMETER = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final int INDEX_OF_SUPPLY_PARAMETER = 0;
    private static final int INDEX_OF_BUY_PARAMETER = 1;
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] receivedData = readDataFromFile(fromFileName);
        int[] resultParameters = dataProcessing(receivedData);
        String report = createReport(resultParameters);
        writeDataToFile(toFileName, report);
    }

    private String[] readDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String lineFromFile;
            while ((lineFromFile = bufferedReader.readLine()) != null) {
                stringBuilder.append(lineFromFile).append(System.lineSeparator());
            }
            return stringBuilder.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private int[] dataProcessing(String[] receivedData) {
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String data : receivedData) {
            String[] dataArray = data.split(COMMA_SEPARATOR);
            if (dataArray[INDEX_OF_BUSINESS_TYPE_PARAMETER].equalsIgnoreCase(SUPPLY)) {
                supplyAmount += Integer.parseInt(dataArray[INDEX_OF_AMOUNT]);
            } else if (dataArray[INDEX_OF_BUSINESS_TYPE_PARAMETER].equalsIgnoreCase(BUY)) {
                buyAmount += Integer.parseInt(dataArray[INDEX_OF_AMOUNT]);
            } else {
                throw new RuntimeException("Invalid data");
            }
        }
        return new int[]{supplyAmount, buyAmount};
    }

    private String createReport(int[] resultParameters) {
        int supplyAmount = resultParameters[INDEX_OF_SUPPLY_PARAMETER];
        int buyAmount = resultParameters[INDEX_OF_BUY_PARAMETER];
        int result = supplyAmount - buyAmount;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA_SEPARATOR).append(supplyAmount)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA_SEPARATOR).append(buyAmount)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA_SEPARATOR).append(result)
                .append(System.lineSeparator());
        return stringBuilder.toString();
    }

    private void writeDataToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
