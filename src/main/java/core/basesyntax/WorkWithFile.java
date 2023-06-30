package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    private static final String SEPARATOR = ",";
    private static final String CSV_OPERATION_TYPE_SUPPLY = "supply";
    private static final String CSV_OPERATION_TYPE_BUY = "buy";
    private static final String CSV_OPERATION_TYPE_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupply = 0;
        int totalBuy = 0;
        for (String data : readFromFile(fromFileName)) {
            int amount = Integer.parseInt(data.substring(data
                    .indexOf(SEPARATOR) + 1));
            if (data.contains(CSV_OPERATION_TYPE_SUPPLY)) {
                totalSupply += amount;
            } else if (data.contains(CSV_OPERATION_TYPE_BUY)) {
                totalBuy += amount;
            }
        }
        writeToFile(toFileName, totalSupply, totalBuy);
    }

    private List<String> readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        List<String> dataFromFile;
        try {
            dataFromFile = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
        return dataFromFile;
    }

    private void writeToFile(String toFileName, int totalSupply, int totalBuy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(CSV_OPERATION_TYPE_SUPPLY + SEPARATOR + totalSupply
                    + System.lineSeparator());
            writer.write(CSV_OPERATION_TYPE_BUY + SEPARATOR + totalBuy
                    + System.lineSeparator());
            writer.write(CSV_OPERATION_TYPE_RESULT + SEPARATOR
                    + (totalSupply - totalBuy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a file", e);
        }
    }
}
