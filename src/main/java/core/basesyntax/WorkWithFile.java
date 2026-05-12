package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final char COMA = ',';

    public void getStatistic(String fromFileName, String toFileName) {
        String[] linesArray = readFromFile(fromFileName);
        String result = calculateReport(linesArray);
        writeToFile(result, toFileName);

    }

    public String[] readFromFile(String fromFileName) {
        try {
            List<String> lines = Files.readAllLines(new File(fromFileName).toPath());
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            throw new RuntimeException("Can not read file", e);
        }
    }

    public String calculateReport(String[] linesArray) {
        int supply = 0;
        int buy = 0;
        StringBuilder result = new StringBuilder();

        String[] temp = new String[2];
        for (String line : linesArray) {
            temp = line.split(String.valueOf(COMA));
            switch (temp[0]) {
                case SUPPLY:
                    supply += Integer.parseInt(temp[1]);
                    break;
                case BUY:
                    buy += Integer.parseInt(temp[1]);
                    break;
                default:
                    break;
            }
        }
        result.append("supply,").append(supply).append(System.lineSeparator())
                .append("buy,").append(buy).append(System.lineSeparator())
                .append("result,").append(supply - buy);
        return result.toString();
    }

    public void writeToFile(String data, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(toFileName)))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can not write to file", e);
        }
    }
}
