package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String INFORMATION_SEPARATOR = ",";
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUYING_OPERATION = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int buy = 0;
        int supply = 0;
        int resultOperation = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
            String[] operationsWithGoods = stringBuilder.toString().split(System.lineSeparator());
            for (String operation: operationsWithGoods) {
                String[] splitOperation = operation.split(INFORMATION_SEPARATOR);
                if (isSupply(splitOperation)) {
                    supply += Integer.parseInt(splitOperation[1].replaceAll("[^0-9]", ""));
                }
                if (isBuy(splitOperation)) {
                    buy += Integer.parseInt(splitOperation[1].replaceAll("[^0-9]", ""));
                }
            }
            fileBuilder(statisticBuilder(supply, buy),toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file.", e);
        }
    }

    private boolean isSupply(String[] operation) {
        return operation[0].equals(SUPPLY_OPERATION);
    }

    private boolean isBuy(String[] operation) {
        return operation[0].equals(BUYING_OPERATION);
    }

    private void fileBuilder(String info,String toFileName) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), info.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write the file", e);
        }
    }

    private String statisticBuilder (int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_OPERATION)
                .append(INFORMATION_SEPARATOR)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUYING_OPERATION)
                .append(INFORMATION_SEPARATOR)
                .append(buy)
                .append(System.lineSeparator())
                .append("result").append(INFORMATION_SEPARATOR)
                .append(supply-buy);
        return builder.toString();
    }
}
