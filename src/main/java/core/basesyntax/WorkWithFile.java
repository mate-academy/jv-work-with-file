package core.basesyntax;

import java.io.*;
import java.nio.file.*;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            int supply = 0;
            int buy = 0;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(COMMA);
                if (split[0].equals(SUPPLY)) {
                    supply += Integer.valueOf(split[1]);
                }
                if (split[0].equals(BUY)) {
                    buy += Integer.valueOf(split[1]);
                }
            }
            writeResult(toFileName, getResult(supply, buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
    }

    private String getResult(int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA)
                .append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA)
                .append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA)
                .append(supply - buy);
        return builder.toString();
    }

    private void writeResult(String pathName, String result) {
        try {
            Files.write(Path.of(pathName), result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + pathName, e);
        }
    }
}
