package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String report = getCreatedReport(readFromFile(fromFileName));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFileName, e);
        }
    }

    private String getCreatedReport(String readFromFile) {
        int supply = 0;
        int buy = 0;
        String[] split = readFromFile.split(" ");
        for (int i = 0; i < split.length; i++) {
            String[] divided = split[i].split(",");
            if (divided[0].equals("supply")) {
                supply = supply + Integer.valueOf(divided[1]);
            } else {
                buy = buy + Integer.valueOf(divided[1]);
            }
        }
        int result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append("supply,").append(Integer.toString(supply))
                .append(System.lineSeparator()).append("buy,")
                .append(Integer.toString(buy)).append(System.lineSeparator())
                .append("result,").append(Integer.toString(result));
        return builder.toString();
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read the file", e);
        }
        return builder.toString();
    }
}
