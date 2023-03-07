package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final int FIRST_INDEX = 0;
    private static final int SECOND_INDEX = 1;
    private static final String SUPPLY_KEY_WORD = "supply";
    private static final String BUY_KEY_WORD = "buy";
    private static final String COMMA_SEPARATOR = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFile(fromFileName);
        String report = generateReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String readFile(String fromFileName) {
        File file = new File(fromFileName);
        String fileData;
        try {
            fileData = Files.readString(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file: " + fromFileName, e);
        }
        return fileData;
    }

    private String generateReport(String fileData) {
        StringBuilder dataLines = new StringBuilder();
        int supply = 0;
        int buy = 0;

        String[] separator = fileData.split(NEW_LINE_SEPARATOR);

        for (String line : separator) {
            String[] lines = line.split(COMMA_SEPARATOR);
            if (lines[FIRST_INDEX].equals(SUPPLY_KEY_WORD)) {
                supply += Integer.parseInt(lines[SECOND_INDEX]);
            }
            if (lines[FIRST_INDEX].equals(BUY_KEY_WORD)) {
                buy += Integer.parseInt(lines[SECOND_INDEX]);
            }
        }
        int result = supply - buy;
        dataLines.append(SUPPLY_KEY_WORD).append(COMMA_SEPARATOR)
                .append(supply).append(NEW_LINE_SEPARATOR)
                .append(BUY_KEY_WORD).append(COMMA_SEPARATOR)
                .append(buy).append(NEW_LINE_SEPARATOR)
                .append("result").append(COMMA_SEPARATOR).append(result);
        return dataLines.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        try {
            Files.writeString(file.toPath(), report);
        } catch (IOException e) {
            throw new RuntimeException("Can`t write file: " + toFileName, e);
        }
    }
}
