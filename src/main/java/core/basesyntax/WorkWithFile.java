package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> linesOfCsvFile = getDataFromCsvfile(fromFileName);
        int sumOfSupply = Constants.AMOUNT_STARTING_VALUE;
        int sumOfBuy = Constants.AMOUNT_STARTING_VALUE;
        if (!linesOfCsvFile.isEmpty()) {
            String currentType = "";
            int currentValue = 0;
            for (int i = 0; i < linesOfCsvFile.toArray().length; i++) {
                String[] itemDataSplit = linesOfCsvFile.get(i).split(Constants.COMMA_SEPARATOR);
                if (itemDataSplit.length == 2) {
                    currentType = itemDataSplit[Constants.ITEM_TYPE_INDEX];
                    try {
                        currentValue = Integer.parseInt(itemDataSplit[Constants.ITEM_VALUE_INDEX]);
                    } catch (NumberFormatException e) {
                        throw new RuntimeException(Constants.PARSE_INTEGER
                                + fromFileName + " for line: '" + i + "'", e);
                    }
                    if (currentType.equals(Constants.ITEM_SUPPLY_NAME)) {
                        sumOfSupply += currentValue;
                    }
                    if (currentType.equals(Constants.ITEM_BUY_NAME)) {
                        sumOfBuy += currentValue;
                    }
                }
            }
        }
        String report = generateReport(sumOfSupply, sumOfBuy);
        saveToCsvFile(toFileName, report);

    }

    private List<String> getDataFromCsvfile(String fromFileName) {
        Path pathOfCsvFile = Path.of(fromFileName);
        List<String> linesOfCsvFile;
        try {
            linesOfCsvFile = Files.readAllLines(pathOfCsvFile);
        } catch (IOException e) {
            throw new RuntimeException(Constants.CANT_FIND_CSV_FILE + fromFileName, e);
        }
        return linesOfCsvFile;
    }

    private void saveToCsvFile(String filename, String data) {
        File newCsvFile = new File(filename);
        try {
            newCsvFile.createNewFile();
            Files.write(newCsvFile.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(Constants.CANT_SAVE_CSV_FILE + filename, e);
        }
    }

    private String generateReport(int sumOfSupply, int sumOfBuy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.ITEM_SUPPLY_NAME)
                .append(Constants.COMMA_SEPARATOR)
                .append(sumOfSupply)
                .append(System.lineSeparator())
                .append(Constants.ITEM_BUY_NAME)
                .append(Constants.COMMA_SEPARATOR)
                .append(sumOfBuy)
                .append(System.lineSeparator())
                .append(Constants.ITEM_RESULT_NAME)
                .append(Constants.COMMA_SEPARATOR)
                .append(sumOfSupply - sumOfBuy);
        return stringBuilder.toString();
    }
}
