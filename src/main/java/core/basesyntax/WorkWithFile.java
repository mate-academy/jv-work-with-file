package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = getStringFromFile(fromFileName);
        int supply = getAmount(data, SUPPLY);
        int buy = getAmount(data, BUY);
        String result = SUPPLY + SEPARATOR + supply + System.lineSeparator() +
                BUY + SEPARATOR + buy + System.lineSeparator() +
                "result" + SEPARATOR + (supply - buy);
        writeStringToFile(result, toFileName);
    }

    private String[] getStringFromFile(String fromFile) {
        try {
            String str = Files.readString(Paths.get(fromFile));
            return str.split(System.lineSeparator());
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
            if (forPars[0].equals(element)) {
                amount += Integer.parseInt(forPars[1]);
            }
        }
        return amount;
    }
}
