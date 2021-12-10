package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SPLIT_REGEX = "\\W+";
    private static final String NAME_SUPPLY = "supply";
    private static final String NAME_BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        setDataToFile(toFileName, createStatisticFromData(getDataFromFile(fromFileName)));
    }

    private String[] getDataFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        return builder.toString().split(SPLIT_REGEX);
    }

    private String createStatisticFromData(String[] dataFromFile) {
        StringBuilder reportBuilder = new StringBuilder();
        int supplyValue = 0;
        int buyValue = 0;

        for (int j = 0; j < dataFromFile.length; j++) {
            if (dataFromFile[j].equals(NAME_SUPPLY)) {
                supplyValue += Integer.parseInt(dataFromFile[j + 1]);
            }
            if (dataFromFile[j].equals(NAME_BUY)) {
                buyValue += Integer.parseInt(dataFromFile[j + 1]);
            }
        }
        return reportBuilder.append("supply,").append(supplyValue).append(System.lineSeparator())
                .append("buy,").append(buyValue).append(System.lineSeparator())
                .append("result,").append(supplyValue - buyValue).toString();
    }

    private void setDataToFile(String toFileName, String data) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
