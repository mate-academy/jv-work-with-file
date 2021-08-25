package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;
    private static final String SUPPLY = "supply";
    private static final String COMMA = ",";
    private static final String BUY = "buy";

    public void getStatistic(String fromFileName, String toFileName) {
        String result = readFile(fromFileName);
        writeFile(result, toFileName);
    }

    public String readFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line = reader.readLine();
            String[] lines;
            StringBuilder builder = new StringBuilder();
            int buy = 0;
            int supply = 0;
            do {
                lines = line.split(COMMA);
                line = reader.readLine();
                if (lines[OPERATION_TYPE].equals(SUPPLY)) {
                    supply += Integer.parseInt(lines[AMOUNT]);
                }
                if (lines[OPERATION_TYPE].equals(BUY)) {
                    buy += Integer.parseInt(lines[AMOUNT]);
                }
            } while (line != null);
            builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                    .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                    .append("result").append(COMMA).append(supply - buy);
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeFile(String toWrite, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(toWrite);
        } catch (Exception e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }

}

