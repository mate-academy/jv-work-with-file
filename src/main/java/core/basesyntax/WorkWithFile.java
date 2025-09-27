package core.basesyntax;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        List<String> linesOfCsvFile = getdatafromCsvfile(fromFileName);
        int sumOfSupply = Constants.AMOUNT_STARTING_VALUE;
        int sumOfBuy = Constants.AMOUNT_STARTING_VALUE;
        if (!linesOfCsvFile.isEmpty()) {
            String currentType;
            int currentValue;
            for (String item : linesOfCsvFile) {
                String[] itemDataSplit = item.split(Constants.COMA_SEPARATOR);
                currentType = itemDataSplit[Constants.ITEM_TYPE_INDEX];
                currentValue = Integer.parseInt(itemDataSplit[Constants.ITEM_VALUE_INDEX]);
                if (currentType.equals(Constants.ITEM_SUPPLY_NAME)) {
                    sumOfSupply += currentValue;
                }
                if (currentType.equals(Constants.ITEM_BUY_NAME)) {
                    sumOfBuy += currentValue;
                }
            }
        }
        String report = generateReport(sumOfSupply, sumOfBuy);
        saveToCsvFile(toFileName, report);

    }

    private List<String> getdatafromCsvfile(String fromFileName) {
        Path pathOfCsvFile = Path.of(fromFileName);
        List<String> linesOfCsvFile;
        try {
            linesOfCsvFile = Files.readAllLines(pathOfCsvFile);
        } catch (IOException e) {
            throw new RuntimeException(Constants.CANT_FIND_CSV_FILE, e);
        }
        return linesOfCsvFile;
    }

    private void saveToCsvFile(String filename, String data) {
        File newCsvFile = new File(filename);
        try {
            newCsvFile.createNewFile();
            Files.write(newCsvFile.toPath(), data.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(Constants.CANT_SAVE_CSV_FILE, e);
        }
    }

    private String generateReport(int sumOfSupply, int sumOfBuy) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.ITEM_SUPPLY_NAME)
                .append(Constants.COMA_SEPARATOR)
                .append(sumOfSupply)
                .append(System.lineSeparator())
                .append(Constants.ITEM_BUY_NAME)
                .append(Constants.COMA_SEPARATOR)
                .append(sumOfBuy)
                .append(System.lineSeparator())
                .append(Constants.ITEM_RESULT_NAME)
                .append(Constants.COMA_SEPARATOR)
                .append(sumOfSupply - sumOfBuy);
        return stringBuilder.toString();
    }
}
