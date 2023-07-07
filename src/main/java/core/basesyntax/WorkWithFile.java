package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final int GROUP_INDEX = 0;
    private static final int VALUE_INDEX = 1;
    private static final int START_VALUE = 0;
    private static final String SUPPLY_TEXT = "supply";
    private static final String BUY_TEXT = "buy";
    private static final String RESULT_TEXT = "result,";
    private static final String COMA_SEPARATOR = ",";
    private static final String SPACE_SEPARATOR = " ";
    private static final String READ_ERROR_MESSAGE = "Can`t read the file";
    private static final String WRITE_ERROR_MESSAGE = "Can`t write to the file";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder fileStringBuilder = new StringBuilder();
        fileStringBuilder = readFile(fromFileName, fileStringBuilder);
        String[] linesArray = fileStringBuilder.toString().split(SPACE_SEPARATOR);
        String resultString = formString(linesArray);
        writeFile(toFileName, resultString);
    }

    private void writeFile(String toFileName, String result) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException(WRITE_ERROR_MESSAGE + toFileName + e);
        }
    }

    private String formString(String[] linesArray) {
        int supply = START_VALUE;
        int buy = START_VALUE;
        for (String line : linesArray) {
            String[] soloLineArray = line.split(COMA_SEPARATOR);
            if (soloLineArray[GROUP_INDEX].equals(SUPPLY_TEXT)) {
                supply += Integer.parseInt(soloLineArray[VALUE_INDEX]);
            } else {
                buy += Integer.parseInt(soloLineArray[VALUE_INDEX]);
            }
        }
        return new StringBuilder().append(SUPPLY_TEXT).append(COMA_SEPARATOR)
                .append(supply).append(System.lineSeparator())
                .append(BUY_TEXT).append(COMA_SEPARATOR)
                .append(buy).append(System.lineSeparator())
                .append(RESULT_TEXT).append(supply - buy).toString();
    }

    private StringBuilder readFile(String fromFileName, StringBuilder fileStringBuilder) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                fileStringBuilder.append(line).append(SPACE_SEPARATOR);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(READ_ERROR_MESSAGE + fromFileName + e);
        }
        return fileStringBuilder;
    }
}
