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

        List<String> strings = readFromFileToStringList(file);
        int[] totalBuyAndSupply = calculateReport(strings);
        int totalSupply = totalBuyAndSupply[ARRAY_FIRST_ELEMENT];
        int totalBuy = totalBuyAndSupply[ARRAY_SECOND_ELEMENT];

        String report = createReport(totalSupply, totalBuy);
        writeReportToFile(report, toFileName);
    }

    private String createReport(int totalSupply, int totalBuy) {
        return String.format("%s,%d%n%s,%d%n%s,%d",
                SUPPLY_STRING, totalSupply,
                BUY_STRING, totalBuy,
                RESULT_STRING, (totalSupply - totalBuy));
    }

    private void writeReportToFile(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFileName, e);
        }
    }

    private List<String> readFromFileToStringList(File file) {
        List<String> strings = null;
        try {
            strings = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file" + file.getName(), e);
        }

        return strings;
    }

    private int[] calculateReport(List<String> strings) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String pair : strings) {
            String[] csvArray = pair.split(COMMA);
            String stringFromFile = csvArray[ARRAY_FIRST_ELEMENT];
            int valueFromFile = Integer.parseInt(csvArray[ARRAY_SECOND_ELEMENT]);

            if (stringFromFile.equals(SUPPLY_STRING)) {
                totalSupply += valueFromFile;
            } else if (stringFromFile.equals(BUY_STRING)) {
                totalBuy += valueFromFile;
            }
        }

        return new int[]{totalSupply, totalBuy};
    }
}
