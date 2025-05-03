package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String[]> result = readDataFromFile(fromFileName);
        int[] statistics = calculate(result);
        writeDataToFile(toFileName, statistics);

    }

    private List<String[]> readDataFromFile(String fromFileName) {
        List<String[]> result = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fromFileName));
            for (String line : lines) {
                String[] values = line.split(",");
                result.add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return result;
    }

    private int[] calculate(List<String[]> result) {
        int buy = 0;
        int supply = 0;
        for (String[] values : result) {
            String operation = values[0];
            if (isNumeric(values[1])) {
                int amount = Integer.parseInt(values[1]);
                if (operation.equals("supply")) {
                    supply += amount;
                } else if (operation.equals("buy")) {
                    buy += amount;
                }
            }
        }
        return new int[]{supply, buy, supply - buy};
    }

    private void writeDataToFile(String toFileName, int[] statistic) {
        try {
            Files.write(Paths.get(toFileName), List.of(
                    "supply," + statistic[0],
                    "buy," + statistic[1],
                    "result," + statistic[2]
            ));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
