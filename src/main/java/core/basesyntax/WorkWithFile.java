package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
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
            throw new RuntimeException("Cannot read the file", e);
        }
    }

    private static int[] calculateData(String data) throws RuntimeException {
        String[] items = data.split(System.lineSeparator());
        int supplyCounter = 0;
        int buyCounter = 0;

        for (String item: items) {
            String[] itemValues = item.split(",");

            switch (itemValues[0]) {
                case "supply":
                    supplyCounter += Integer.parseInt(itemValues[1]);
                    break;
                case "buy":
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

        stringBuilder.append("supply,").append(supplyType).append(System.lineSeparator())
                .append("buy,").append(buyType).append(System.lineSeparator())
                .append("result,").append(supplyType - buyType);

        return stringBuilder.toString();
    }

    private static void writeDataToFile(String path, String content) throws RuntimeException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write into the file!", e);
        }
    }
}
