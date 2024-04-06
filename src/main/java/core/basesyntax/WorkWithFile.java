package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class WorkWithFile {
    private static final String NAME_SUPPLY = "supply";
    private static final String NAME_BUY = "buy";
    private static final int INDEX_BUY = 0;
    private static final int INDEX_SUPPLY = 1;
    private static final int INDEX_TOTAL = 2;
    private static final int INDEX_STRING = 0;
    private static final int INDEX_INT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int[] values = getValues(fromFileName);
        StringBuilder finalStatistic = new StringBuilder();
        finalStatistic.append(NAME_SUPPLY)
                .append(",")
                .append(values[INDEX_SUPPLY])
                .append(System.lineSeparator())
                .append(NAME_BUY)
                .append(",")
                .append(values[INDEX_BUY])
                .append(System.lineSeparator())
                .append("result")
                .append(",")
                .append(values[INDEX_TOTAL]);
        String recordToFile = finalStatistic.toString();
        writeFile(recordToFile, toFileName);
    }

    private static String readFile(String fromFileName) {
        File allData = new File(fromFileName);
        String fileToString;
        try {
            BufferedReader readFile = new BufferedReader(new FileReader(allData));
            StringBuilder dataContainer = new StringBuilder();
            int value = readFile.read();
            while (value != -1) {
                dataContainer.append((char) value);
                value = readFile.read();
            }
            fileToString = dataContainer.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can`t read file", e);
        }
        return fileToString;
    }

    private int[] getValues(String fromFileName) {
        int sumBuy = 0;
        int sumSupply = 0;
        int total = 0;
        int temporaryValue = 0;
        String temporaryValueName = "";
        String fileToString = readFile(fromFileName);
        String[] stringToArray = fileToString.split("\n");
        for (int i = 0; i < stringToArray.length; i++) {
            String[] splitByComa = stringToArray[i].split(",");
            temporaryValueName = splitByComa[INDEX_STRING];
            temporaryValue = Integer.parseInt(splitByComa[INDEX_INT]);
            if (temporaryValueName.equals(NAME_BUY)) {
                sumBuy += temporaryValue;
            } else {
                sumSupply += temporaryValue;
            }
        }
        total = sumSupply - sumBuy;
        return new int[]{sumBuy, sumSupply, total};
    }

    private static void writeFile(String record, String toFileName) {
        File statistic = new File(toFileName);
        try {
            statistic.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Can`t create a file", e);
        }
        try {
            Files.write(statistic.toPath(),record.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Can`t write a file", e);
        }
    }
}
