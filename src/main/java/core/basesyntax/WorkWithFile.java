package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE_POSITION = 0;
    private static final int AMOUNT_POSITION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String reportData = createReportFromFile(fromFileName);
        writeToFile(reportData, toFileName);
    }

    private String createReportFromFile(String fromFileName) {
        File fromFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        int supply = 0;
        int buy = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(",");
                if (split[OPERATION_TYPE_POSITION].equals("supply")) {
                    supply += Integer.parseInt(split[AMOUNT_POSITION]);
                } else {
                    buy += Integer.parseInt(split[AMOUNT_POSITION]);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file " + fromFileName, e);
        }

        stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);

        return stringBuilder.toString();
    }

    private void writeToFile(String reportData, String toFileName) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write(reportData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
