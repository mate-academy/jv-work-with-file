package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String OP_SUPPLY = "supply";
    private static final String OP_BUY = "buy";
    private static final String RESULT = "result";
    private static final char RESULT_ENTRY_DELIMITER = ',';


    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readDataFromFile(fromFileName);
        int[] data = calculateData(fileContent);

        writeDataToFile(toFileName, prepareReport(data));
    }

    private static String readDataFromFile(String path) throws RuntimeException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.lineSeparator());
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file:" + path, e);
        }
    }

    private static int[] calculateData(String data) throws RuntimeException {
        String[] items = data.split(System.lineSeparator());
        int supplyCounter = 0;
        int buyCounter = 0;

        for (String item: items) {
            String[] itemValues = item.split(",");

            switch (itemValues[0]) {
                case OP_SUPPLY:
                    supplyCounter += Integer.parseInt(itemValues[1]);
                    break;
                case OP_BUY:
                    buyCounter += Integer.parseInt(itemValues[1]);
                    break;
                default:
                    throw new RuntimeException("Wrong type");
            }
        }

        return new int[] {
                supplyCounter,
                buyCounter
        };
    }

    private static String prepareReport(int[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplyType = data[0];
        int buyType = data[1];

        stringBuilder.append(OP_SUPPLY).append(RESULT_ENTRY_DELIMITER).append(supplyType).append(System.lineSeparator())
                .append(OP_BUY).append(RESULT_ENTRY_DELIMITER).append(buyType).append(System.lineSeparator())
                .append(RESULT).append(RESULT_ENTRY_DELIMITER).append(supplyType - buyType);

        return stringBuilder.toString();
    }

    private static void writeDataToFile(String path, String content) throws RuntimeException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to the file:" + path, e);
        }
    }
}
