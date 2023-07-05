package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkWithFile {
    private static final String SUPPLY_TEXT = "supply,";
    private static final String BUY_TEXT = "buy,";
    private static final String RESULT_TEXT = "result,";
    private static final String COMA_SEPARATOR = ",";
    private static final String SPACE_SEPARATOR = " ";

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder fileStringBuilder = new StringBuilder();
        readFile(fromFileName, fileStringBuilder);
        String[] linesArray = fileStringBuilder.toString().split(SPACE_SEPARATOR);
        StringBuilder resultString = new StringBuilder();
        resultString.append(SUPPLY_TEXT).append(0).append(System.lineSeparator())
                .append(BUY_TEXT).append(0).append(System.lineSeparator());
        fillString(linesArray, resultString);
        writeFile(toFileName, resultString);
    }

    private static void writeFile(String toFileName, StringBuilder result) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(toFileName))) {
            writer.write(result.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void fillString(String[] linesArray, StringBuilder result) {
        for (String line : linesArray) {
            String[] soloLineArray = line.split(COMA_SEPARATOR);
            int startIndex = result.indexOf(soloLineArray[0]) + soloLineArray[0].length() + 1;
            int endIndex = result.indexOf(System.lineSeparator(), startIndex);
            result.replace(
                    startIndex,
                    endIndex,
                    String.valueOf(
                            Integer.parseInt(result.substring(startIndex, endIndex))
                    + Integer.parseInt(soloLineArray[1])));
        }
        int supplyCount = Integer.parseInt(result.substring(result.indexOf(SUPPLY_TEXT) + 7,
                        result.indexOf(System.lineSeparator(), result.indexOf(SUPPLY_TEXT))));
        int buyCount = Integer.parseInt(result.substring(result.indexOf(BUY_TEXT) + 4,
                        result.indexOf(System.lineSeparator(), result.indexOf(BUY_TEXT))));
        result.append(RESULT_TEXT).append(supplyCount - buyCount);
    }

    private static void readFile(String fromFileName, StringBuilder fileStringBuilder) {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fromFileName))) {
            String line = reader.readLine();
            while (line != null) {
                fileStringBuilder.append(line).append(SPACE_SEPARATOR);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
