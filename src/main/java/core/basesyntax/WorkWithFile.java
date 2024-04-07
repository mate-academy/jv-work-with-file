package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String NAME_SUPPLY = "supply";
    private static final String NAME_BUY = "buy";
    private static final int INDEX_BUY = 0;
    private static final int INDEX_SUPPLY = 1;
    private static final int INDEX_TOTAL = 2;
    private static final int INDEX_STRING = 0;
    private static final int INDEX_INT = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String fileContent = readFile(fromFileName);
        int[] values = getValues(fileContent);
        String report = createReport(values);
        writeFile(report, toFileName);
    }

    private static String readFile(String fromFileName) {
        StringBuilder dataContainer = new StringBuilder();
        try {
            BufferedReader readFile = new BufferedReader(new FileReader(fromFileName));
            int value = readFile.read();
            while (value != -1) {
                dataContainer.append((char) value);
                value = readFile.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read a file", e);
        }
        return dataContainer.toString();
    }

    private int[] getValues(String fileToString) {
        int sumBuy = 0;
        int sumSupply = 0;
        int total;
        int temporaryValue;
        String temporaryValueName;
        String[] stringToArray = fileToString.split("\\r?\\n");
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
        try (BufferedWriter statistic = new BufferedWriter(new FileWriter(toFileName))) {
            statistic.write(record);
        } catch (IOException e) {
            throw new RuntimeException("Can`t create a file", e);
        }
    }

    public static String createReport(int[] values) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append(System.lineSeparator())
                .append(NAME_SUPPLY)
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
        return reportBuilder.toString();
    }
}
