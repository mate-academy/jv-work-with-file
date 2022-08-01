package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int INDEX_OF_OPERATION_TYPE = 0;
    private static final int INDEX_OF_AMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String DELIMITER = ",";
    
    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readFromFile(fromFileName);
        int supplyAmount = 0;
        int buyAmount = 0;
        for (String currentLine : dataFromFile) {
            String[] reportArray = currentLine.split(DELIMITER);
            if (reportArray[INDEX_OF_OPERATION_TYPE].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(reportArray[INDEX_OF_AMOUNT]);
            } else {
                buyAmount += Integer.parseInt(reportArray[INDEX_OF_AMOUNT]);
            }
        }
        int result = supplyAmount - buyAmount;
        String resultStatistic = SUPPLY + DELIMITER + supplyAmount
                + System.lineSeparator() + BUY + DELIMITER + buyAmount
                + System.lineSeparator() + "result," + result;
        writeToFile(resultStatistic, toFileName);
    }

    private String[] readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(fileName))) {
            String stringFromFile = readerFromFile.readLine();
            while (stringFromFile != null) {
                builder.append(stringFromFile).append(System.lineSeparator());
                stringFromFile = readerFromFile.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fileName, e);
        }
        return builder.toString().split(System.lineSeparator());
    }

    private void writeToFile(String data, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }
}
