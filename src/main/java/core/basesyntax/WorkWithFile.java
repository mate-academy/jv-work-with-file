package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String RESULT = "result";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    private static final String NEW_LINE = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String result = calculateStatistic(readDataFromFile(fromFileName));
        writeDataToFile(result, toFileName);
    }

    private String readDataFromFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder readBuilder = new StringBuilder();
            String value = reader.readLine();

            while (value != null) {
                readBuilder.append(value).append(NEW_LINE);
                value = reader.readLine();
            }

            return readBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }
    }

    private void writeDataToFile(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to a file", e);
        }
    }

    private String calculateStatistic(String inputInfo) {
        StringBuilder calculateBuilder = new StringBuilder();
        String[] separateInfo = inputInfo.split("\\W+");
        int supplies = 0;
        int buy = 0;

        for (int i = 0; i < separateInfo.length; i += 2) {
            if (separateInfo[i].startsWith(SUPPLY)) {
                supplies += Integer.parseInt(separateInfo[i + 1]);
            } else if (separateInfo[i].startsWith(BUY)) {
                buy += Integer.parseInt(separateInfo[i + 1]);
            }
        }
        calculateBuilder.append(SUPPLY + ",").append(supplies).append(NEW_LINE)
                .append(BUY + ",").append(buy).append(NEW_LINE).append(RESULT + ",")
                .append(supplies - buy);

        return calculateBuilder.toString();
    }
}
