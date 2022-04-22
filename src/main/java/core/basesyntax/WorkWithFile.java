package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        String file = readFromFile(fromFileName);
        String[] split = file.split(" ");
        for (int i = 0; i < split.length; i++) {
            String[] divided = split[i].split(",");
            if (divided[0].equals("supply")) {
                supply = supply + Integer.valueOf(divided[1]);
            } else {
                buy = buy + Integer.valueOf(divided[1]);
            }
        }
        int result = supply - buy;
        StringBuilder builder1 = new StringBuilder();
        builder1.append("supply,").append(Integer.toString(supply))
                .append(System.lineSeparator()).append("buy,")
                .append(Integer.toString(buy)).append(System.lineSeparator())
                .append("result,").append(Integer.toString(result));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(builder1.toString());
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFileName, e);
        }
    }

    public String readFromFile(String fromFileName) {
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
        String file = builder.toString();
        return file;
    }
}
