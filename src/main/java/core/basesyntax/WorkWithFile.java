package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY_TEXT = "supply";
    private static final String BUY_TEXT = "buy";
    private static final String RESULT_TEXT = "result,";
    private static final String COMA_SEPARATOR = ",";
    private static final String SPACE_SEPARATOR = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder fileStringBuilder = new StringBuilder();
        fileStringBuilder = readFile(fromFileName, fileStringBuilder);
        String[] linesArray = fileStringBuilder.toString().split(SPACE_SEPARATOR);
        String resultString = fillString(linesArray);
        writeFile(toFileName, resultString);
    }

    private void writeFile(String toFileName, String result) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String fillString(String[] linesArray) {
        int supply = 0;
        int buy = 0;
        for (String line : linesArray) {
            String[] soloLineArray = line.split(COMA_SEPARATOR);
            if (soloLineArray[0].equals(SUPPLY_TEXT)) {
                supply += Integer.parseInt(soloLineArray[1]);
            } else {
                buy += Integer.parseInt(soloLineArray[1]);
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
            throw new RuntimeException(e);
        }
        return fileStringBuilder;
    }
}
