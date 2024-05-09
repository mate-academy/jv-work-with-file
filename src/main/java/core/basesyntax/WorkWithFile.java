package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    public static final String COMMA_CHAR = ",";
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> fileContent = readFromFile(fromFileName);
        String report = createReport(fileContent);
        writeToFile(report, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        Path fromFilePath = Paths.get(fromFileName);
        try {
            return Files.readAllLines(fromFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + fromFileName, e);
        }
    }

    private String createReport(List<String> fileContent) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (String line : fileContent) {
            String[] parts = line.split(COMMA_CHAR);
            String typeOfOperation = parts[0];
            int amount = Integer.parseInt(parts[1]);
            if (typeOfOperation.equals(SUPPLY)) {
                totalSupply += amount;
            } else if (typeOfOperation.equals(BUY)) {
                totalBuy += amount;
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA_CHAR).append(totalSupply)
                .append(System.lineSeparator());
        stringBuilder.append(BUY).append(COMMA_CHAR).append(totalBuy)
                .append(System.lineSeparator());
        stringBuilder.append(RESULT).append(COMMA_CHAR).append(totalSupply - totalBuy);
        return stringBuilder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        Path toFilePath = Paths.get(toFileName);
        try {
            Files.write(toFilePath, report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
