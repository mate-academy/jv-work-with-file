package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int OPERATION_INDEX = 0;
    static final int AMOUNT_INDEX = 1;
    static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String info = readFile(fromFileName);
        String report = createReport(info);
        writeReportToFile(report, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        String text;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((text = reader.readLine()) != null) {
                builder.append(text).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file" + fromFileName, e);
        }
        return builder.toString();
    }

    private String createReport(String data) {
        int supplyCount = 0;
        int buyCount = 0;
        int result = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] part = line.split(DELIMITER);
            if (part[OPERATION_INDEX].startsWith("supply")) {
                supplyCount += Integer.parseInt(part[AMOUNT_INDEX]);
            } else if (part[OPERATION_INDEX].startsWith("buy")) {
                buyCount += Integer.parseInt(part[AMOUNT_INDEX]);
            }
            result = supplyCount - buyCount;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("supply," + supplyCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator()
                + "result," + result + System.lineSeparator());
        return builder.toString();
    }

    private void writeReportToFile(String fileData, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(fileData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + toFileName, e);
        }
    }
}
