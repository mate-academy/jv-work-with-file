package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

public class WorkWithFile {

    private static final String COMA = ",";
    private static final int INDEX_OF_SUPPLY_OR_BUY = 0;
    private static final int INDEX_OF_QUANTITY = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private int buyValue = 0;
    private int supplyValue = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        readFromFile(fromFileName);
        StringBuilder report = createReport();
        writeToFile(report, toFileName);
    }

    private void readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        String[] splitLine;
        try {
            List<String> allLines = Files.readAllLines(file.toPath());
            for (String allLine : allLines) {
                splitLine = allLine.split(COMA);
                if (splitLine[INDEX_OF_SUPPLY_OR_BUY].equals(SUPPLY)) {
                    supplyValue = supplyValue + Integer.parseInt(splitLine[INDEX_OF_QUANTITY]);
                } else if (splitLine[INDEX_OF_SUPPLY_OR_BUY].equals(BUY)) {
                    buyValue = buyValue + Integer.parseInt(splitLine[INDEX_OF_QUANTITY]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
    }

    private StringBuilder createReport() {
        return new StringBuilder()
                .append(SUPPLY + COMA).append(supplyValue).append(System.lineSeparator())
                .append(BUY + COMA).append(buyValue).append(System.lineSeparator())
                .append(RESULT + COMA).append(supplyValue - buyValue);
    }

    private void writeToFile(StringBuilder report, String toFileName) {
        File file = new File(toFileName);
        try {
            Files.write(file.toPath(), Collections.singleton(report));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file!", e);
        }
    }
}
