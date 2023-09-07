package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    static final int ZERO = 0;
    static final int ONE = 1;

    public String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        String text;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            while ((text = reader.readLine()) != null) {
                builder.append(text).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }

    public String createReport(String data) {
        int supplyCount = 0;
        int buyCount = 0;
        int result = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] part = line.split(",");
            if (part[ZERO].startsWith("supply")) {
                supplyCount += Integer.parseInt(part[ONE]);
            } else if (part[ZERO].startsWith("buy")) {
                buyCount += Integer.parseInt(part[ONE]);
            }
            result = supplyCount - buyCount;
        }

        StringBuilder builder = new StringBuilder();
        builder.append("supply," + supplyCount + System.lineSeparator()
                + "buy," + buyCount + System.lineSeparator()
                + "result," + result + System.lineSeparator());
        return builder.toString();
    }

    public void writeReportToFile(String fileData, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(fileData);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String info = readFile(fromFileName);
        String report = createReport(info);
        writeReportToFile(report, toFileName);
    }

}
