package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

public class WorkWithFile {
    private static final int COLUMN_AMOUNT = 2;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;
    private static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String stringData = stringData(fromFileName);
        String[] devidedData = stringData.split("[^A-Za-z0-9,]+");
        int[] supplyBuyData = new int[COLUMN_AMOUNT];
        for (int i = 0; i < devidedData.length; i++) {
            if (devidedData[i].matches("(s)[A-Za-z0-9,]+")) {
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

    private String stringData(String fromFileName) {
        File file = new File(fromFileName);
        String informationFromFileName = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = reader.readLine();
            }
            informationFromFileName = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return informationFromFileName;
    }

    private void writeFile(String toFileName, String data) {
        try {
            Files.write(Path.of(toFileName), Collections.singleton(data));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}

