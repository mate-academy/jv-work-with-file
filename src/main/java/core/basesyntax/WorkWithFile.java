package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String NAME_OF_FIRST_LINE;
    private static final String NAME_OF_SECOND_LINE;
    private static final String NAME_OF_THIRD_LINE;
    private static final String COMMA;
    private static final int SUPPLY_INDEX;
    private static final int BUY_INDEX;
    private static final int RESULT_INDEX;
    private static final int NAME_INDEX;
    private static final int AMOUNT_INDEX;

    static {
        NAME_OF_FIRST_LINE = "supply";
        NAME_OF_SECOND_LINE = "buy";
        NAME_OF_THIRD_LINE = "result";
        COMMA = ",";
        NAME_INDEX = SUPPLY_INDEX = 0;
        AMOUNT_INDEX = BUY_INDEX = 1;
        RESULT_INDEX = 2;
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String entity = formTable(parseLinesFromFile(fromFileName));
        writeToFile(entity, toFileName);
    }

    private void writeToFile(String entity, String toFileName) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), entity.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("File cannot be written in file: " + toFileName, e);
        }
    }

    private String formTable(String[] parsedLines) {
        String[][] stringArraySheet = new String[RESULT_INDEX + 1][AMOUNT_INDEX + 1];
        stringArraySheet[SUPPLY_INDEX][NAME_INDEX] = NAME_OF_FIRST_LINE;
        stringArraySheet[BUY_INDEX][NAME_INDEX] = NAME_OF_SECOND_LINE;
        stringArraySheet[RESULT_INDEX][NAME_INDEX] = NAME_OF_THIRD_LINE;
        for (String line : parsedLines) {
            updateSheet(stringArraySheet, line);
        }
        return formTable(stringArraySheet);
    }

    private String formTable(String[][] stringArraySheet) {
        String resultSum = String.valueOf(Integer.parseInt(
                stringArraySheet[SUPPLY_INDEX][AMOUNT_INDEX])
                - Integer.parseInt(stringArraySheet[BUY_INDEX][AMOUNT_INDEX]));
        return NAME_OF_FIRST_LINE + COMMA + stringArraySheet[SUPPLY_INDEX][AMOUNT_INDEX]
                + System.lineSeparator()
                + NAME_OF_SECOND_LINE + COMMA + stringArraySheet[BUY_INDEX][AMOUNT_INDEX]
                + System.lineSeparator()
                + NAME_OF_THIRD_LINE + COMMA + resultSum
                + System.lineSeparator();
    }

    private void updateSheet(String[][] sheet, String line) {
        String[] parts = line.split(COMMA);
        for (int i = 0; i < sheet.length; i++) {
            if (sheet[i][NAME_INDEX].equals(parts[NAME_INDEX])) {
                sheet[i][AMOUNT_INDEX] = (sheet[i][AMOUNT_INDEX] != null)
                        ? String.valueOf(Integer.parseInt(sheet[i][AMOUNT_INDEX])
                        + Integer.parseInt(parts[AMOUNT_INDEX]))
                        : parts[AMOUNT_INDEX];
            }
        }
    }

    private String[] parseLinesFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder lines = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            return lines.toString().split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read from file: " + fromFileName, e);
        }
    }
}
