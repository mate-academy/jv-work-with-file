package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    public static final String TITTLE_BUY = "buy";
    public static final String TITTLE_SUPPLY = "supply";
    public static final String TITTLE_RESULT = "result";
    public static final int POSITION_AMOUNT = 1;
    public static final int POSITION_OPERATION_TYPE = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> linesFromFile = readFromFile(fromFileName);
        String report = getReport(linesFromFile);
        writeToFile(report, toFileName);
    }

    private List<String> readFromFile(String fromFile) {
        File file = new File(fromFile);
        List<String> lines;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file " + fromFile, e);
        }
        return lines;
    }

    private String getReport(List<String> linesFromFile) {
        int totalBuy = 0;
        int totalSupply = 0;
        for (String line : linesFromFile) {
            String[] arrayOfLine = line.split(",");
            int amount = Integer.parseInt(arrayOfLine[POSITION_AMOUNT]);
            if (arrayOfLine[POSITION_OPERATION_TYPE].equals(TITTLE_BUY)) {
                totalBuy += amount;
            }
            if (arrayOfLine[POSITION_OPERATION_TYPE].equals(TITTLE_SUPPLY)) {
                totalSupply += amount;
            }
        }
        int result = totalSupply - totalBuy;
        StringBuilder writeToFileBuilder = new StringBuilder();
        writeToFileBuilder.append(TITTLE_SUPPLY).append(",").append(totalSupply)
                .append(System.lineSeparator()).append(TITTLE_BUY).append(",").append(totalBuy)
                .append(System.lineSeparator()).append(TITTLE_RESULT).append(",").append(result);
        return writeToFileBuilder.toString();
    }

    private void writeToFile(String result, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}

