package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String [] getResult = readFromFile(new File(fromFileName)).split(System.lineSeparator());
        writeToFile(toFileName, generateStatistic(getResult));
    }

    private String generateStatistic(String [] str) {
        StringBuilder builder = new StringBuilder();
        int buy = 0;
        int supply = 0;

        for (String s : str) {
            String[] data = s.split(COMMA);
            buy += data[0].equals("buy") ? Integer.parseInt(data[1]) : 0;
            supply += data[0].equals("supply") ? Integer.parseInt(data[1]) : 0;
        }
        return builder.append(SUPPLY).append(COMMA)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA)
                .append(supply - buy).toString();
    }

    private String readFromFile(File file) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String value;
            while ((value = reader.readLine()) != null) {
                builder.append(value);
                builder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from \\'" + file.getName() + "\\'" + e);
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName, String statistic) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(statistic);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to \\'" + toFileName + "\\' file.", e);
        }
    }
}
