package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String COMA = ",";
    private static final int INDEX_OF_SUPPLY = 0;
    private static final int INDEX_OF_BUY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeDataToFile(toFileName, writeDataToSting(calculateDate(readFromFile(fromFileName))));
    }

    private String readFromFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder dataString = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String value = reader.readLine();
            while (value != null) {
                dataString.append(value).append(COMA);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file: " + fromFileName, e);
        }
        return dataString.toString();
    }

    private int[] calculateDate(String dataCalculate) {
        String[] arrayToSplit = dataCalculate.split(COMA);
        int countSupply = 0;
        int countBuy = 0;
        for (int i = 0; i < arrayToSplit.length; i += 2) {
            if (arrayToSplit[i].equals(SUPPLY)) {
                countSupply = countSupply + Integer.parseInt(arrayToSplit[i + 1]);
            } else if (arrayToSplit[i].equals(BUY)) {
                countBuy = countBuy + Integer.parseInt(arrayToSplit[i + 1]);
            }
        }
        int[] arrayData = {countSupply, countBuy};
        return arrayData;
    }

    private String writeDataToSting(int[] dataArray) {
        StringBuilder statisticCalculate = new StringBuilder();
        statisticCalculate.append(SUPPLY).append(COMA)
                .append(dataArray[INDEX_OF_SUPPLY]).append(System.lineSeparator())
                .append(BUY).append(COMA).append(dataArray[INDEX_OF_BUY])
                .append(System.lineSeparator()).append(RESULT).append(COMA)
                .append(dataArray[INDEX_OF_SUPPLY] - dataArray[INDEX_OF_BUY]);
        return statisticCalculate.toString();
    }

    private void writeDataToFile(String fileName, String dataArray) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(dataArray);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file: " + fileName, e);
        }
    }
}
