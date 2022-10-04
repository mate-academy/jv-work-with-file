package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final int SEPARATOR_INDEX = 1;
    private static final String SEPARATOR = System.lineSeparator();
    private static final String WORD_SUPPLY = "supply";
    private static final String WORD_BUY = "buy";
    private static final String WORD_RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> lines = readFile(fromFileName);
        writeFile(toFileName, getFilesFrom(lines));
    }

    private List<String> readFile(String fromFileName) {
        try {
            return Files.readAllLines(new File(fromFileName).toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fromFileName, e);
        }
    }

    private String getFilesFrom(List<String> lines) {
        int totalBuy = 0;
        int totalSupply = 0;
        int amount;
        for (String line : lines) {
            amount = Integer.parseInt(line.split(COMMA)[SEPARATOR_INDEX]);
            if (line.contains(WORD_BUY)) {
                totalBuy += amount;
            }
            if (line.contains(WORD_SUPPLY)) {
                totalSupply += amount;
            }
        }
        int result = totalSupply - totalBuy;
        return returnData(totalBuy, totalSupply, result);
    }

    private String returnData(int totalBuy, int totalSupply, int result) {
        return new StringBuilder()
                .append(WORD_SUPPLY).append(COMMA).append(totalSupply)
                .append(SEPARATOR)
                .append(WORD_BUY).append(COMMA).append(totalBuy).append(SEPARATOR)
                .append(WORD_RESULT).append(COMMA).append(result).toString();
    }

    private void writeFile(String toFileName, String getReportFrom) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName, true))) {
            writer.write(getReportFrom);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + getReportFrom, e);
        }
    }
}
