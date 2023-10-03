package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY_STRING = "supply";
    private static final String BUY_STRING = "buy";
    private static final String RESULT_STRING = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int totalSupply = 0;
        int totalBuy = 0;

        try {
            List<String> strings = Files.readAllLines(file.toPath());

            for (String str : strings) {
                String[] arr = str.split(",");
                if (arr[0].equals(SUPPLY_STRING)) {
                    totalSupply += Integer.valueOf(arr[1]);
                } else if (arr[0].equals(BUY_STRING)) {
                    totalBuy += Integer.valueOf(arr[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }

        String report = createReport(totalSupply, totalBuy);
        writeToFile(toFileName, report);
    }

    private String createReport(int totalSupply, int totalBuy) {
        return String.format("%s,%d%n%s,%d%n%s,%d",
                SUPPLY_STRING, totalSupply,
                BUY_STRING, totalBuy,
                RESULT_STRING, (totalSupply - totalBuy));

    }

    private void writeToFile(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }
}
