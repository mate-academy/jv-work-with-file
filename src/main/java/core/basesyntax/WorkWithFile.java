package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String SUPPLY = "supply,";
    private static final String BUY = "buy,";
    private static final String RESULT = "result,";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int buy = 0;
        int supply = 0;

        for (String s : readFromFile(file)) {
            String[] data = s.split(",");
            buy += data[0].equals("buy") ? Integer.parseInt(data[1]) : 0;
            supply += data[0].equals("supply") ? Integer.parseInt(data[1]) : 0;
        }
        writeToFile(toFileName, buy, supply);
    }

    public String [] readFromFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value;
            while ((value = reader.readLine()) != null) {
                builder.append(value);
                builder.append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("file read error " + e);
        }
        return builder.toString().split(LINE_SEPARATOR);
    }

    public void writeToFile(String toFileName, int buy, int supply) {
        StringBuilder builder = new StringBuilder();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            builder.append(SUPPLY)
                    .append(supply)
                    .append(LINE_SEPARATOR)
                    .append(BUY)
                    .append(buy)
                    .append(LINE_SEPARATOR)
                    .append(RESULT)
                    .append(supply - buy);
            writer.write(builder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to \\'" + toFileName + "\\' file.", e);
        }
    }
}
