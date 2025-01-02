package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String[] action;
            String value = reader.readLine();
            while (value != null) {
                value = value.trim();
                if (value.isEmpty() || !value.contains(",")) {
                    value = reader.readLine();
                    continue;
                }
                action = value.split(",\\s*");

                supply += action[0].equals("supply") ? Integer.parseInt(action[1]) : 0;
                buy += action[0].equals("buy") ? Integer.parseInt(action[1]) : 0;
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder sb = new StringBuilder("");

            sb.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);
            writer.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
