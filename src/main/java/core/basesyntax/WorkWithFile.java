package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        int supplyAmount = calculateTotal(dataFromFile, SUPPLY);
        int buyAmount = calculateTotal(dataFromFile, BUY);
        int result = supplyAmount - buyAmount;
        List<String> report = createReport(supplyAmount, buyAmount, result);
        writeToFile(toFileName, report);
    }

    private List<String> readFromFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fileName, e);
        }
    }

    private int calculateTotal(List<String> data, String operationType) {
        int total = 0;
        for (String line : data) {
            String[] eachElement = line.split(",");
            if (eachElement[0].equals(operationType)) {
                total += Integer.parseInt(eachElement[1]);
            }
        }
        return total;
    }

    private List<String> createReport(int supplyAmount, int buyAmount, int result) {
        return Arrays.asList(
                SUPPLY + "," + supplyAmount,
                BUY + "," + buyAmount,
                "result," + result
        );
    }

    private void writeToFile(String fileName, List<String> report) {
        try {
            Files.write(Paths.get(fileName), report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file " + fileName, e);
        }
    }
}
