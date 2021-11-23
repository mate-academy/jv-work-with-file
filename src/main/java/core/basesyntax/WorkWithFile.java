package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private String[] readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder builder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = reader.readLine();
            }
            return builder.toString().split(" ");
        } catch (IOException e) {
            throw new RuntimeException("This file can't be read", e);
        }
    }

    private String createReport(String[] inputReadFile) {
        int buy = 0;
        int supply = 0;
        StringBuilder builder = new StringBuilder();
        for (String oneData : inputReadFile) {
            if (oneData.contains("buy")) {
                buy += Integer.parseInt(oneData.substring(oneData.indexOf(",") + 1));
            } else {
                supply += Integer.parseInt(oneData.substring(oneData.indexOf(",") + 1));
            }
        }
        builder.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return builder.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String dataToFile = createReport(readFile(fromFileName));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(dataToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
