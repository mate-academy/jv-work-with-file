package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int INDEX_VALUE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builderFrom = readToFile(fromFileName);
        StringBuilder builderTo = createReport(builderFrom);
        writeToFile(toFileName, builderTo);
    }

    public StringBuilder readToFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builderFrom = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int value = reader.read();
            while (value != -1) {
                builderFrom.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return builderFrom;
    }

    public StringBuilder createReport(StringBuilder builderFrom) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] arrayLines = builderFrom.toString().split("\r\n");
        for (int i = 0; i < arrayLines.length; i++) {
            String[] valueSeparated = arrayLines[i].split(",");
            int value = 0;
            try {
                value = Integer.parseInt(valueSeparated[INDEX_VALUE]);
            } catch (NumberFormatException ex) {
                throw new RuntimeException("Can't parse value " + valueSeparated[INDEX_VALUE], ex);
            }
            if (valueSeparated[0].equals("buy")) {
                sumBuy = sumBuy + value;
            } else {
                sumSupply = sumSupply + value;
            }
        }
        int result = sumSupply - sumBuy;
        StringBuilder builderTo = new StringBuilder("supply,");
        builderTo.append(sumSupply).append(System.lineSeparator())
                .append("buy,").append(sumBuy).append(System.lineSeparator())
                .append("result,").append(result);
        return builderTo;
    }

    public void writeToFile(String toFileName, StringBuilder builderTo) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create new file", e);
        }
        try {
            Files.write(file.toPath(), builderTo.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
