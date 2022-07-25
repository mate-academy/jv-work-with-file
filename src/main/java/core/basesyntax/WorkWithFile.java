package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_INDEX = 0;
    private static final int AMMOUNT_INDEX = 1;
    private static final int TOTAL_SUPPLY_INDEX = 0;
    private static final int TOTAL_BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFromFile(fromFileName);
        int totalResult = calculateTotalSupplyAndBuy(data)[TOTAL_SUPPLY_INDEX]
                - calculateTotalSupplyAndBuy(data)[TOTAL_BUY_INDEX];
        String reportStatistic =
                "supply" + "," + calculateTotalSupplyAndBuy(data)[TOTAL_SUPPLY_INDEX]
                        + System.lineSeparator()
                + "buy" + "," + calculateTotalSupplyAndBuy(data)[TOTAL_BUY_INDEX]
                        + System.lineSeparator()
                + "result" + "," + totalResult;
        writeToFile(reportStatistic, toFileName);
    }

    private int[] calculateTotalSupplyAndBuy(String[] data) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : data) {
            String[] splittedLine = line.split(",");
            if (splittedLine[OPERATION_TYPE_INDEX].equals("supply")) {
                totalSupply += Integer.parseInt(splittedLine[AMMOUNT_INDEX]);
            } else {
                totalBuy += Integer.parseInt(splittedLine[AMMOUNT_INDEX]);
            }
        }
        return new int[]{totalSupply, totalBuy};
    }

    private String[] readFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        String[] readData = {};
        if (!stringBuilder.toString().isEmpty()) {
            readData = stringBuilder.toString().split(System.lineSeparator());
        }
        return readData;
    }

    private void writeToFile(String writeData, String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(writeData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data from the file " + fileName, e);
        }
    }
}
