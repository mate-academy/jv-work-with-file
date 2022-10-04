package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String separator = " ";
    private static final String lineSeparator = System.lineSeparator();
    private static final String csvSeparator = ",";
    private static final String supplyName = "supply";
    private static final String buyName = "buy";
    private static final String resultName = "result";
    private static final int nameIndex = 0;
    private static final int numberIndex = 1;
    private static final int supplyResultIndex = 0;
    private static final int buyResultIndex = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String information = getInformationFromFile(fromFileName);
        int[] results = getResults(information);
        String report = createReport(results);
        writeReportToFile(toFileName, report);
    }

    private String getInformationFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(separator);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return stringBuilder.toString();
    }

    private void writeReportToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

    private int[] getResults(String information) {
        int supply = 0;
        int buy = 0;
        String[] textSplit = information.split(separator);
        for (String string : textSplit) {
            String[] stringForCount = string.split(csvSeparator);
            if (stringForCount[nameIndex].equals(supplyName)) {
                supply += Integer.parseInt(stringForCount[numberIndex]);
            } else {
                buy += Integer.parseInt(stringForCount[numberIndex]);
            }
        }
        return new int[]{supply, buy};
    }

    private String createReport(int[] results) {
        return supplyName
                + ","
                + results[supplyResultIndex]
                + lineSeparator
                + buyName
                + ","
                + results[buyResultIndex]
                + lineSeparator
                + resultName
                + ","
                + (results[supplyResultIndex] - results[buyResultIndex]);
    }
}
