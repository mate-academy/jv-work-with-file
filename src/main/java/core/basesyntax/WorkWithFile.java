package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {

    private static final String SUPPLY_MARKER = "supply";
    private static final String BUY_MARKER = "buy";
    private static final String RESULT_MARKER = "result";
    private static final String TOKEN_SEPARATOR = ",";
    private static final int MARKER_INDEX = 0;
    private static final int MARKER_VALUE_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        String report = generateReport(fileContent);
        writeFile(toFileName, report);
    }

    private String readFile(String fileName) {
        try {
            return Files.readString(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }
    }

    private String generateReport(String fileContent) {
        long totalSupply = 0;
        long totalBuy = 0;
        for (String line : fileContent.split(System.lineSeparator())) {
            String[] splittedLine = line.split(TOKEN_SEPARATOR);
            if (SUPPLY_MARKER.equals(splittedLine[MARKER_INDEX])) {
                totalSupply += Long.parseLong(splittedLine[MARKER_VALUE_INDEX]);
            } else if (BUY_MARKER.equals(splittedLine[MARKER_INDEX])) {
                totalBuy += Long.parseLong(splittedLine[MARKER_VALUE_INDEX]);
            } else {
                throw new RuntimeException("Unexpected marker");
            }
        }
        long result = totalSupply - totalBuy;
        return new StringBuilder(SUPPLY_MARKER).append(TOKEN_SEPARATOR).append(totalSupply)
                .append(System.lineSeparator())
                .append(BUY_MARKER).append(TOKEN_SEPARATOR).append(totalBuy)
                .append(System.lineSeparator())
                .append(RESULT_MARKER).append(TOKEN_SEPARATOR).append(result)
                .toString();
    }

    private void writeFile(String fileName, String fileContent) {
        try {
            Files.writeString(Path.of(fileName), fileContent);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }
    }
}
