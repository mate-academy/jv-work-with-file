package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SEPARATOR = ",";
    private static final int AMOUND_INDEX = 1;
    private static final int OPERATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = getStringFromFile(fromFileName);
        int supply = getAmount(data, SUPPLY);
        int buy = getAmount(data, BUY);
        String report = generateReport(supply, buy);
        writeStringToFile(report, toFileName);
    }

    private String[] getStringFromFile(String fromFile) {
        try {
            String data = Files.readString(Paths.get(fromFile));
            return data.split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFile, e);
        }
    }

    private void writeStringToFile(String content, String toFileName) {
        Path filePath = Paths.get(toFileName);
        try {
            Files.write(filePath, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }

    private int getAmount(String[] data, String element) {
        int amount = 0;
        for (String line : data) {
            String[] forPars = line.split(SEPARATOR);
            if (forPars[OPERATION_INDEX].equals(element)) {
                amount += Integer.parseInt(forPars[AMOUND_INDEX]);
            }
        }
        return amount;
    }

    private String generateReport(int supply, int buy) {
        return SUPPLY + SEPARATOR + supply + System.lineSeparator()
                + BUY + SEPARATOR + buy + System.lineSeparator()
                + RESULT + SEPARATOR + (supply - buy);
    }
}
