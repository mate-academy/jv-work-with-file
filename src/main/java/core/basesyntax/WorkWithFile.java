package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final int VALUE_INDEX = 1;
    private static final int OPERATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String builderFrom = readToFile(fromFileName);
        String builderTo = createReport(builderFrom);
        writeToFile(toFileName, builderTo);
    }

    private String readToFile(String fromFileName) {
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
        return builderFrom.toString();
    }

    private String createReport(String builderFrom) {
        int sumSupply = 0;
        int sumBuy = 0;
        String[] arrayLines = builderFrom.split(System.lineSeparator());
        for (int i = 0; i < arrayLines.length; i++) {
            String[] valueSeparated = arrayLines[i].split(",");
            int value = 0;
            try {
                value = Integer.parseInt(valueSeparated[VALUE_INDEX]);
            } catch (NumberFormatException ex) {
                throw new RuntimeException("Can't parse value " + valueSeparated[VALUE_INDEX], ex);
            }
            if (valueSeparated[OPERATION_INDEX].equals("buy")) {
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
        return builderTo.toString();
    }

    private void writeToFile(String toFileName, String builderTo) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
            Files.write(file.toPath(), builderTo.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            String message = file.isFile() ? ("Can't write data to file") : ("Can't create file");
            throw new RuntimeException(message, e);
        }
    }
}
