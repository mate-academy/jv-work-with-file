package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> infoFromFile = getInfoFromFile(fromFileName);
        String report = getReport(infoFromFile);
        writeDataToFile(toFileName, report);
    }

    private List<String> getInfoFromFile(String fromFileName) {
        File fileToRead = new File(fromFileName);
        List<String> strings;
        try {
            strings = Files.readAllLines(fileToRead.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fromFileName, e);
        }
        return strings;
    }

    private String getReport(List<String> data) {
        StringBuilder stringBuilder = new StringBuilder();
        int totalSupply = 0;
        int totalBuy = 0;
        for (String line : data) {
            int value = Integer.parseInt(line.substring(line.indexOf(SEPARATOR) + 1));
            if (line.contains(SUPPLY)) {
                totalSupply += value;
            } else {
                totalBuy += value;
            }
        }
        return stringBuilder.append(SUPPLY).append(SEPARATOR).append(totalSupply)
                .append(System.lineSeparator()).append(BUY).append(SEPARATOR).append(totalBuy)
                .append(System.lineSeparator()).append(RESULT).append(SEPARATOR)
                .append(totalSupply - totalBuy).toString();
    }

    private void writeDataToFile(String toFileName, String resultLine) {
        File report = new File(toFileName);

        try {
            report.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(report))) {
            bufferedWriter.write(resultLine);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
