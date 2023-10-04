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
    private static final String COMMA = ",";
    private static final int ARRAY_FIRST_ELEMENT = 0;
    private static final int ARRAY_SECOND_ELEMENT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int totalSupply = calculateReport(file, fromFileName)[ARRAY_FIRST_ELEMENT];
        int totalBuy = calculateReport(file, fromFileName)[ARRAY_SECOND_ELEMENT];

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

    private int[] calculateReport(File file, String fromFileName) {
        int totalSupply = 0;
        int totalBuy = 0;

        try {
            List<String> strings = Files.readAllLines(file.toPath());

            for (String str : strings) {
                String[] arr = str.split(COMMA);
                String stringFromFile = arr[ARRAY_FIRST_ELEMENT];
                int valueFromFile = Integer.parseInt(arr[ARRAY_SECOND_ELEMENT]);

                if (stringFromFile.equals(SUPPLY_STRING)) {
                    totalSupply += valueFromFile;
                } else if (stringFromFile.equals(BUY_STRING)) {
                    totalBuy += valueFromFile;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + fromFileName, e);
        }

        return new int[]{totalSupply, totalBuy};
    }
}
