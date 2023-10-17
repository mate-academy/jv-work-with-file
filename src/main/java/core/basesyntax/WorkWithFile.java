package core.basesyntax;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class WorkWithFile {
    static final String SEPARATOR = ",";
    static final String SUPPLY = "supply,";
    static final String BUY = "buy,";
    static final String RESULT = "result,";
    static final int OPERATION_TYPE_INDEX = 0;
    static final int AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> listFromFile = readFromFile(fromFileName);
        String reportFromList = makeReport(listFromFile);
        writeToFile(reportFromList, toFileName);
    }

    private List<String> readFromFile(String fromFileName) {
        try {
            return Files.readAllLines(Paths.get(fromFileName));
        } catch (IOException e) {
            throw new RuntimeException("Cant read from file", e);
        }
    }

    private String makeReport(List<String> listFromFile) {
        int supply = 0;
        int buy = 0;
        for (String line : listFromFile) {
            String[] split = line.split(SEPARATOR);
            if (split[OPERATION_TYPE_INDEX].equals("supply")) {
                supply += Integer.parseInt(split[AMOUNT_INDEX]);
            } else {
                buy += Integer.parseInt(split[AMOUNT_INDEX]);
            }
        }
        return SUPPLY + supply + System.lineSeparator()
                + BUY + buy + System.lineSeparator()
                + RESULT + (supply - buy);
    }

    private void writeToFile(String dataToWrite, String toFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Cant write to file" + toFileName, e);
        }
    }
}
