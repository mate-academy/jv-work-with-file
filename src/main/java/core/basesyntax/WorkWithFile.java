package core.basesyntax;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String SYMBOL_SEPARATOR = ",";
    private static final int OPERATION_TYPE = 0;
    private static final int AMOUNT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] rowsTable = readAndTransformToArray(fromFileName);
        String report = createReport(countSupply(rowsTable), countBuy(rowsTable));
        writeStringToFile(report, toFileName);
    }

    private String[] readAndTransformToArray(String fromFileName) {
        String fileContent = getStringFromFile(fromFileName);
        return fileContent.split("\\n");
    }

    private String getStringFromFile(String fromFile) {
        try {
            return Files.readString(Paths.get(fromFile));
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fromFile, e);
        }
    }

    private String createReport(int supply, int buy) {
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY + SYMBOL_SEPARATOR + supply + System.lineSeparator());
        builder.append(BUY + SYMBOL_SEPARATOR + buy + System.lineSeparator());
        builder.append(RESULT + SYMBOL_SEPARATOR + (supply - buy));
        return builder.toString();
    }

    private int countSupply(String[] rowsTable) {
        return getAmountOfElement(rowsTable, SUPPLY);
    }

    private int countBuy(String[] rowsTable) {
        return getAmountOfElement(rowsTable, BUY);
    }

    private int getAmountOfElement(String[] strings, String element) {
        int amount = 0;
        for (String line : strings) {
            String[] dividedIntoColumnsRow = line.split(SYMBOL_SEPARATOR);
            if (element.equals(dividedIntoColumnsRow[OPERATION_TYPE])) {
                amount += Integer.parseInt(dividedIntoColumnsRow[AMOUNT]);
            }
        }
        return amount;
    }

    private void writeStringToFile(String content, String toFileName) {
        Path filePath = Paths.get(toFileName);
        try {
            Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to the file " + toFileName, e);
        }
    }
}
