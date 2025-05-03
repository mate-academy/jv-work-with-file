package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> data = readFromFile(fromFileName);
        String report = createReport(data);
        writeReport(toFileName, report);
    }

    private List<String> readFromFile(String fromFileName) {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Path.of(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFileName, e);
        }
        return lines;
    }

    private String createReport(List<String> inputData) {
        int supplyInt = 0;
        int buyInt = 0;
        for (String string : inputData) {
            String[] stringSplit = string.split(COMMA);
            if (stringSplit[0].equals(SUPPLY)) {
                supplyInt += Integer.parseInt(stringSplit[1]);
            } else {
                buyInt += Integer.parseInt(stringSplit[1]);
            }
        }
        int resultInt = supplyInt - buyInt;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(supplyInt).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buyInt).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(resultInt);
        return stringBuilder.toString();
    }

    private void writeReport(String toFileName, String report) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
