package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String SUPPLY_KEYWORD = "supply";
    private static final String BUY_KEYWORD = "buy";
    private static final String RESULT_KEYWORD = "result";
    private static final String DELIMITER_REGEX = "\\W+";

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContens = readFromFile(fromFileName);
        String report = createReport(fileContens);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fileName) {
        File file = new File(fileName);
        try {
            return Files.readAllLines(file.toPath()).toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file " + fileName, e);
        }
    }

    private String createReport(String strings) {
        String[] tokens = strings.split(DELIMITER_REGEX);
        int supply = 0;
        int buy = 0;
        int result;
        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals(SUPPLY_KEYWORD)) {
                supply += Integer.parseInt(tokens[i + 1]);
            } else if (tokens[i].equals(BUY_KEYWORD)) {
                buy += Integer.parseInt(tokens[i + 1]);
            }
        }
        result = supply - buy;
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_KEYWORD).append(",").append(supply).append(System.lineSeparator())
                .append(BUY_KEYWORD).append(",").append(buy).append(System.lineSeparator())
                .append(RESULT_KEYWORD).append(",").append(result);
        return builder.toString();
    }

    private void writeToFile(String fileName, String report) {
        File file = new File(fileName);
        try {
            Files.write(file.toPath(), report.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file: " + fileName, e);
        }
    }
}
