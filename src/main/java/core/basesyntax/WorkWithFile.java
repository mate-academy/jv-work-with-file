package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    public static final String DATA_LINE_SEPARATOR = ",";
    public static final String DATA_BUILDER_SEPARATOR = " ";
    public static final int OPERATION_POSITION = 0;
    public static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeFile(report, toFileName);
    }

    private String readFromFile(String fromFileName) {
        try {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String value = reader.readLine();
            while (value != null) {
                builder.append(value)
                        .append(" ");
                value = reader.readLine();
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
    }

    private String createReport(String dataFromFile) {
        int supply = 0;
        int buy = 0;
        String[] array = dataFromFile.split(DATA_BUILDER_SEPARATOR);
        for (String line : array) {
            String[] splitLine = line.split(DATA_LINE_SEPARATOR);
            if (splitLine[OPERATION_POSITION].equals("supply")) {
                supply += Integer.parseInt(splitLine[AMOUNT_POSITION]);
            }
            if (splitLine[OPERATION_POSITION].equals("buy")) {
                buy += Integer.parseInt(splitLine[AMOUNT_POSITION]);
            }
        }
        return "supply," + supply
                + System.lineSeparator()
                + "buy," + buy
                + System.lineSeparator()
                + "result," + (supply - buy);
    }

    private void writeFile(String report, String toFileName) {
        try {
            Files.write(Path.of(toFileName), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
