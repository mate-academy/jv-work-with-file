package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA_SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            writeDataToFile(calculateData(readDataFromFile(fromFileName)),toFileName);
        } catch (IOException e) {
            throw new RuntimeException("Error while working with files");
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
            if (strings[0].equals("buy")) {
                totalBuy += Integer.parseInt(strings[1]);
            } else {
                totalSupply += Integer.parseInt(strings[1]);
            }
        }

        List<String> result = new ArrayList<>(3);
        result.add("supply" + COMMA_SEPARATOR + totalSupply);
        result.add("buy" + COMMA_SEPARATOR + totalBuy);
        result.add("result" + COMMA_SEPARATOR + (totalSupply - totalBuy));
        return result;
    }

    private void writeDataToFile(List<String> listData, String fileName) throws IOException {
        Files.write(Path.of(fileName), listData);
    }
}
