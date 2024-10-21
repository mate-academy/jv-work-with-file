package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String SCV_SEPARATOR = ",";
    public static final int OPERATION_TYPE_INDEX = 0;
    public static final int AMOUNT_INDEX = 1;
    public static final String SUPPLY_OPERATION_TYPE = "supply";
    public static final String BUY_OPERATION_TYPE = "buy";
    public static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);

        List<String> lines;
        try {
            lines = Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }

        int result = 0;
        int supply = 0;
        int buy = 0;
        for (String line : lines) {
            String[] csvLineData = line.split(SCV_SEPARATOR);
            switch (csvLineData[OPERATION_TYPE_INDEX]) {
                case SUPPLY_OPERATION_TYPE: {
                    supply += Integer.valueOf(csvLineData[AMOUNT_INDEX]);
                    result += Integer.valueOf(csvLineData[AMOUNT_INDEX]);
                    break;
                }
                case BUY_OPERATION_TYPE: {
                    buy += Integer.valueOf(csvLineData[AMOUNT_INDEX]);
                    result -= Integer.valueOf(csvLineData[AMOUNT_INDEX]);
                    break;
                }
                default: {
                    throw new InvalidDataException("Unsupported operation exception");
                }
            }
        }

        writeDataToFile(toFileName, supply, buy, result);
    }

    private void writeDataToFile(String fileName, int supply, int buy, int result) {
        StringBuilder builder = new StringBuilder()
                .append(SUPPLY_OPERATION_TYPE)
                .append(SCV_SEPARATOR)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY_OPERATION_TYPE)
                .append(SCV_SEPARATOR)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT)
                .append(SCV_SEPARATOR)
                .append(result);

        File file = new File(fileName);
        try {
            Files.writeString(file.toPath(), builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
