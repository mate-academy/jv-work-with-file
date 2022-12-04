package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    public static final String DATA_LINE_SEPARATOR = ",";
    public static final String DATA_BUILDER_SEPARATOR = " ";
    public static final int OPERATION_POSITION = 0;
    public static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        readFromFile(file, toFileName);
    }

    private void readFromFile(File file, String toFileName) {
        try {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value)
                        .append(" ");
                value = reader.readLine();
            }
            writeToFile(calculateData(builder), toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private StringBuilder calculateData(StringBuilder builder) {
        int supply = 0;
        int buy = 0;
        String[] array = builder.toString().split(DATA_BUILDER_SEPARATOR);
        for (String line : array) {
            String[] splitLine = line.split(DATA_LINE_SEPARATOR);
            if (splitLine[OPERATION_POSITION].equals("supply")) {
                supply += Integer.parseInt(splitLine[AMOUNT_POSITION]);
            }
            if (splitLine[OPERATION_POSITION].equals("buy")) {
                buy += Integer.parseInt(splitLine[AMOUNT_POSITION]);
            }
        }
        builder.setLength(0);
        builder.append("supply,")
                .append(supply)
                .append(System.lineSeparator())
                .append("buy,")
                .append(buy)
                .append(System.lineSeparator())
                .append("result,")
                .append(supply - buy);
        return builder;
    }

    private void writeToFile(StringBuilder calculatedData, String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try {
            Files.write(file.toPath(), calculatedData.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
