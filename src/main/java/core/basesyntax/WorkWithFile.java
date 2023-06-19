package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WorkWithFile {

    public static final String SUPPLY_CODE = "supply";
    public static final String BUY_CODE = "buy";
    public static final String RESULT = "result";
    public static final int CODE_POSITION = 0;
    public static final int VALUE_POSITION = 1;
    public static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> inputLines = getFileContents(fromFileName);

        String report = createReport(inputLines);

        writeToFile(toFileName, report);
    }

    private String createReport(List<String> inputLines) {
        int buyBalance = 0;
        int supplyBalance = 0;
        for (String line: inputLines) {
            String[] columns = line.split(DELIMITER);
            if (BUY_CODE.equalsIgnoreCase(columns[CODE_POSITION])) {
                buyBalance += Integer.parseInt(columns[VALUE_POSITION]);
            } else if (SUPPLY_CODE.equalsIgnoreCase(columns[CODE_POSITION])) {
                supplyBalance += Integer.parseInt(columns[VALUE_POSITION]);
            }
        }

        return new StringBuilder()
                .append(SUPPLY_CODE).append(",").append(supplyBalance)
                .append(System.lineSeparator())
                .append(BUY_CODE).append(",").append(buyBalance)
                .append(System.lineSeparator())
                .append(RESULT).append(",").append(supplyBalance - buyBalance)
                .append(System.lineSeparator())
                .toString();
    }

    private void writeToFile(String toFileName, String contents) {
        File toFile = new File(toFileName);
        try {
            Files.writeString(toFile.toPath(), contents);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file: " + toFileName, e);
        }

    }

    private static List<String> getFileContents(String fromFileName) {
        File fromFile = new File(fromFileName);
        try {
            return Files.readAllLines(fromFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file: " + fromFileName, e);
        }
    }
}
