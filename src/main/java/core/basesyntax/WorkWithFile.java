package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String STRING_SUPPLY = "supply";
    private static final String STRING_BUY = "buy";
    private static final String STRING_RESULT = "result";
    private static final int SUPPLY_START_NUMBER = 7;
    private static final int BUY_START_NUMBER = 4;
    private static final int SUPPLY_INDEX = 0;
    private static final int BUY_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFile(fromFileName);
        int[] supplyAndBuy = parseStatistic(data);
        String report = createReport(supplyAndBuy[SUPPLY_INDEX], supplyAndBuy[BUY_INDEX]);
        writeToFile(report, toFileName);
    }

    private String readFile(String fromFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
    }

    private int[] parseStatistic(String table) {
        String[] fileTable = table.split(System.lineSeparator());
        int[] supplyAndBuy = new int[] {0, 0};
        for (String line : fileTable) {
            if (line.contains(STRING_SUPPLY)) {
                supplyAndBuy[SUPPLY_INDEX] += Integer.parseInt(line.substring(SUPPLY_START_NUMBER));
            } else if (line.contains(STRING_BUY)) {
                supplyAndBuy[BUY_INDEX] += Integer.parseInt(line.substring(BUY_START_NUMBER));
            }
        }
        return supplyAndBuy;
    }

    private String createReport(int supply, int buy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(STRING_SUPPLY).append(",")
                .append(supply)
                .append(System.lineSeparator())
                .append(STRING_BUY).append(",")
                .append(buy)
                .append(System.lineSeparator())
                .append(STRING_RESULT).append(",")
                .append(supply - buy);
        return stringBuilder.toString();
    }

    private void writeToFile(String text, String toFileName) {
        try {
            File file = new File(toFileName);
            Files.write(file.toPath(), text.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
