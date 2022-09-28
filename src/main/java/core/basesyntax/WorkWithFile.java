package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            writeDataToFile(calculateData(readDataFromFile(fromFileName)),toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Error while working with files", e);
        }
    }

    private List<String> readDataFromFile(String fileName) throws IOException {
        return Files.readAllLines(Path.of(fileName));
    }

    private List<String> calculateData(List<String> listData) {
        int totalBuy = 0;
        int totalSupply = 0;

        for (String line : listData) {
            String[] strings = line.split(COMMA_SEPARATOR);
            if (strings[0].equals(BUY)) {
                totalBuy += Integer.parseInt(strings[1]);
            } else {
                totalSupply += Integer.parseInt(strings[1]);
            }
        }

        List<String> result = new ArrayList<>(3);
        result.add(SUPPLY + COMMA_SEPARATOR + totalSupply);
        result.add(BUY + COMMA_SEPARATOR + totalBuy);
        result.add(RESULT + COMMA_SEPARATOR + (totalSupply - totalBuy));
        return result;
    }

    private void writeDataToFile(List<String> listData, String fileName) throws IOException {
        Files.write(Path.of(fileName), listData);
    }
}
