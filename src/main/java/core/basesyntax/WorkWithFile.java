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
        String[] fileContent = readFromFile(fromFileName);
        String report = writeReport(fileContent);
        writeToFile(report, toFileName);
    }

    private String[] readFromFile(String fromFileName) {
        Path fromFilePath = Paths.get(fromFileName);
        try {
            List<String> lines = Files.readAllLines(fromFilePath);

            StringBuilder stringBuilderContent = new StringBuilder();
            for (String line : lines) {
                stringBuilderContent.append(line).append("\n");
            }
            String stringBuilder = stringBuilderContent.toString();
            return stringBuilder.split(COMMA_CHAR + "|\\n");
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + fromFileName, e);
        }
    }

    private String writeReport(String[] fileContent) {
        int totalSupply = 0;
        int totalBuy = 0;

        for (int i = 0; i < fileContent.length; i += 2) {
            String typeOfOperation = fileContent[i];
            int amount = Integer.parseInt(fileContent[i + AMOUNT]);
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
