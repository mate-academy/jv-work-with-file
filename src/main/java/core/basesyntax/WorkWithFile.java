package core.basesyntax;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_OPERATION = "supply";
    private static final String BUY_OPERATION = "buy";
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] values;
        StringBuilder stringBuilder = new StringBuilder();
        int supplyValue = 0;
        int buyValue = 0;
        int resultValue = 0;
        for (String str : readFromFile(fromFileName)) {
            values = str.split(",");
            try {
                if (values[OPERATION_TYPE].equals(SUPPLY_OPERATION)) {
                    supplyValue += Integer.parseInt(values[AMOUNT]);
                } else if (values[OPERATION_TYPE].equals(BUY_OPERATION)) {
                    buyValue += Integer.parseInt(values[AMOUNT]);
                }
            } catch (NumberFormatException e) {
                throw new RuntimeException("Failed to convert value to number " + e);
            }
        }
        resultValue = supplyValue - buyValue;
        stringBuilder.append("supply," + supplyValue + System.lineSeparator())
                        .append("buy," + buyValue + System.lineSeparator())
                                .append("result," + resultValue + System.lineSeparator());
        writeToFile(toFileName, stringBuilder.toString());
    }

    public List<String> readFromFile(String fromFileName) {
        List<String> linesFromFile;
        File file = new File(fromFileName);
        try {
            linesFromFile = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cant read file - " + fromFileName + " " + e);
        }
        return linesFromFile;
    }

    public void writeToFile(String toFileName, String content) {
        try (FileWriter fileWriter = new FileWriter(new File(toFileName))) {
            fileWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file " + toFileName + " " + e);
        }
    }
}
