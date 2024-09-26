package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class WorkWithFile {
    private static final String COMMA_DELIMITER = ",";
    private static final String LINE_DELIMITER = System.lineSeparator();

    public void getStatistic(String fromFileName, String toFileName) {
        String report = formReport(readFile(fromFileName));
        writeToFile(toFileName, report);
    }

    private String[] readFile(String fromFileName) {
        File sourceFile = new File(fromFileName);
        StringBuilder stringBuilder = new StringBuilder();
        String[] data;
        try {
            String fileContent = Files.readString(sourceFile.toPath());
            data = fileContent.toLowerCase().split(LINE_DELIMITER);
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + sourceFile, e);
        }
        return data;
    }

    private String formReport(String[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        int supplySum = 0;
        int buySum = 0;
        for (String string : data) {
            String operationType = string.split(COMMA_DELIMITER)[0];
            int amount = Integer.parseInt(string.split(COMMA_DELIMITER)[1]);
            if (operationType.equals("supply")) {
                supplySum += amount;
            } else {
                buySum += amount;
            }
        }
        int result = supplySum - buySum;
        stringBuilder.append("supply")
                .append(COMMA_DELIMITER)
                .append(supplySum)
                .append(LINE_DELIMITER)
                .append("buy")
                .append(COMMA_DELIMITER)
                .append(buySum)
                .append(LINE_DELIMITER)
                .append("result")
                .append(COMMA_DELIMITER)
                .append(result);

        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String formedReport) {
        File targetFile = new File(toFileName);
        Path path = Paths.get(toFileName);
        try {
            Files.write(path, Collections.singleton(formedReport));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data in the file " + targetFile, e);
        }
    }
}
