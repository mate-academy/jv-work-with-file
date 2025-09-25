package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String SPLIT_REGEX = "\\r?\\n";
    private static final String SEP = ",";
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        String content;
        try {
            content = Files.readString(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file", e);
        }

        String[] lines = content.split(SPLIT_REGEX);
        int sumOfBuy = 0;
        int sumOfSupply = 0;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            if (line.isEmpty()) {
                continue;
            }

            String[] separateLine = line.split(SEP);

            String activity = separateLine[0].trim().toLowerCase();
            String amountOfActivity = separateLine[1];
            int amount = Integer.parseInt(amountOfActivity);

            if (!activity.equals(SUPPLY) && !activity.equals(BUY)) {
                continue;
            }

            if (activity.equals(SUPPLY)) {
                sumOfSupply += amount;
            } else {
                sumOfBuy += amount;
            }
        }
        int result = sumOfSupply - sumOfBuy;

        String newLine = System.lineSeparator();
        String report = SUPPLY + "," + sumOfSupply + newLine
                + BUY + "," + sumOfBuy + newLine
                + "result," +result;

        try {
            Files.writeString(Path.of(toFileName), report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
