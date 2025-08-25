package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY_NAME = "supply";
    private static final String BUY_NAME = "buy";
    private static final String RESULT_NAME = "result";

    public static void getStatistic(String fromFileName, String toFileName) {
        int[] values = readFiles(fromFileName);
        int supply = values[0];
        int buy = values[1];
        int result = supply - buy;

        writeToFile(toFileName, supply, buy, result);
    }

    public static int[] readFiles(String fromFileName) {
        int supply = 0;
        int buy = 0;
        String line;
        String[] lineSplit;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = reader.readLine()) != null) {
                lineSplit = line.split(",");
                int num;
                if (lineSplit[0].equals(SUPPLY_NAME)) {
                    try {
                        num = Integer.parseInt(lineSplit[1]);
                        supply += num;
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Invalid number format in line:" + line, e);
                    }
                } else if (lineSplit[0].equals(BUY_NAME)) {
                    try {
                        num = Integer.parseInt(lineSplit[1]);
                        buy += num;
                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Invalid number format in line:" + line, e);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from a file.", e);
        }
        return new int[]{supply,buy};
    }

    public static void writeToFile(String toFileName, int supply, int buy, int result) {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder builder = new StringBuilder();
            builder.append(SUPPLY_NAME).append(",").append(supply).append(System.lineSeparator())
                    .append(BUY_NAME).append(",").append(buy).append(System.lineSeparator())
                    .append(RESULT_NAME).append(",").append(result);

            write.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a file.", e);
        }
    }
}
