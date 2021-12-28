package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int COLUMN_AMOUNT = 2;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = getDataFromFile(fromFileName);
        String[] devidedData = data.split(System.lineSeparator());
        int[] supplyBuyData = new int[COLUMN_AMOUNT];
        for (int i = 0; i < devidedData.length; i++) {
            if (devidedData[i].contains("supply")) {
                supplyBuyData[SUPPLY_INDEX] += Integer.parseInt(devidedData[i]
                        .split(",")[AMOUNT_INDEX]);
            } else {
                supplyBuyData[BUY_INDEX] += Integer.parseInt(devidedData[i]
                        .split(",")[AMOUNT_INDEX]);
            }
        }
        StringBuilder report = new StringBuilder();
        report.append("supply,").append(supplyBuyData[SUPPLY_INDEX]).append(System.lineSeparator())
                .append("buy,").append(supplyBuyData[BUY_INDEX]).append(System.lineSeparator())
                .append("result,").append(supplyBuyData[SUPPLY_INDEX] - supplyBuyData[BUY_INDEX]);

        writeFile(toFileName, report.toString());
    }

    private String getDataFromFile(String fileName) {
        File file = new File(fileName);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
        return stringBuilder.toString();
    }

    private void writeFile(String toFileName, String data) {
        try {
            Files.write(Path.of(toFileName), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}

