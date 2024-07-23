package core.basesyntax;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        Path pathOfCsvFile = Path.of(fromFileName);
        try {
            List<String> lines = Files.readAllLines(pathOfCsvFile);
            String[] valueFromList = lines.toArray(new String[]{});
            String report = createReport(valueFromList);
            writeToFile(report, toFileName);
        } catch (IOException e) {
            throw new RuntimeException("can't read a file", e);
        }
    }

    private String createReport(String[] getString) {
        int supply = 0;
        int buy = 0;
        for (String data : getString) {
            String[] calculationString = new String[2];
            String[] splitData = data.split(",");
            calculationString[0] = splitData[0];
            calculationString[1] = splitData[1];
            if (calculationString[0].equals(SUPPLY)) {
                supply += Integer.parseInt(calculationString[1]);
            }
            if (calculationString[0].equals(BUY)) {
                buy += Integer.parseInt(calculationString[1]);

            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY).append(COMMA).append(supply).append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy).append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy);
        return builder.toString();
    }

    private void writeToFile(String report, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}






