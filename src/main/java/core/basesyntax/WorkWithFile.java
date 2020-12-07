package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String COMA = ",";
    public static final int ZERO = 0;
    public static final int ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String value = reader.readLine();
            while (value != null) {
                String[] info = value.split(COMA);
                if (info[ZERO].equals("supply")) {
                    supply += Integer.parseInt(info[ONE]);
                }
                if (info[ZERO].equals("buy")) {
                    buy += Integer.parseInt(info[ONE]);
                }
                value = reader.readLine();
            }
            writeToFile(toFileName, supply, buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeToFile(String toFileName, int supply, int buy) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator()).append("result,")
                    .append((supply - buy));
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write write data to file", e);
        }
    }
}
