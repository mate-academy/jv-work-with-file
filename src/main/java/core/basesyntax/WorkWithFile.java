package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final int LINE_NAME = 0;
    private static final int LINE_VALUE = 1;
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFile(fromFileName);
        String report = createReport(data);
        write(report, toFileName);
    }

    private List<String> readFile(String fromFileName) {
        File fileFrom = new File(fromFileName);
        List<String> linesFromFile;
        try {
            linesFromFile = Files.readAllLines(fileFrom.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fromFileName, e);
        }
        return linesFromFile;
    }

    private String createReport(List<String> linesFromFile) {
        int summSupply = 0;
        int summBuy = 0;
        for (String line : linesFromFile) {
            String[] lineArray = line.split(SEPARATOR);
            if (lineArray[LINE_NAME].equals(SUPPLY)) {
                summSupply += Integer.parseInt(lineArray[LINE_VALUE]);
            } else if (lineArray[LINE_NAME].equals(BUY)) {
                summBuy += Integer.parseInt(lineArray[LINE_VALUE]);
            }
        }
        int result = summSupply - summBuy;
        return createResultString(result, summSupply, summBuy);
    }

    private void write(String report, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file " + toFileName, e);
        }
    }

    private String createResultString(int result, int summSupply, int summBuy) {
        StringBuilder report = new StringBuilder();
        report.append(SUPPLY).append(SEPARATOR).append(summSupply)
            .append(System.lineSeparator())
            .append(BUY).append(SEPARATOR).append(summBuy)
            .append(System.lineSeparator())
            .append(RESULT).append(SEPARATOR).append(result);
        return report.toString();
    }
}
