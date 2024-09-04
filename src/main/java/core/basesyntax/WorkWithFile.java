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
        List<String> readedFile;
        int supplyValue = 0;
        int buyValue = 0;
        int result = 0;
        String[] values;
        File file = new File(fromFileName);
        try {
            readedFile = Files.readAllLines(file.toPath());
            for (String str : readedFile) {
                values = str.split(",");
                if (values[OPERATION_TYPE].equals(SUPPLY_OPERATION)) {
                    supplyValue += Integer.parseInt(values[AMOUNT]);
                } else if (values[OPERATION_TYPE].equals(BUY_OPERATION)) {
                    buyValue += Integer.parseInt(values[AMOUNT]);
                }
            }
            result = supplyValue - buyValue;
        } catch (IOException e) {
            throw new RuntimeException("Cant read file" + e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to convert value to number" + e);
        }
        try (FileWriter fileWriter = new FileWriter(new File(toFileName))) {
            fileWriter.write("supply," + supplyValue + System.lineSeparator());
            fileWriter.write("buy," + buyValue + System.lineSeparator());
            fileWriter.write("result," + result + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Cant write data to file" + e);
        }
    }
}
