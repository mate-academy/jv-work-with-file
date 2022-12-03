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
    private static final String CSV_SEPARATOR = ",";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final byte OPERATION_TYPE_INDEX = 0;
    private static final byte AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(toFileName, createReport(readFromFile(fromFileName)));
    }

    private String readFromFile(String file) {
        File fileReadFrom = new File(file);
        StringBuilder stringBuilder = new StringBuilder();
        String csvLine;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileReadFrom))) {
            while ((csvLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(csvLine).append(LINE_SEPARATOR);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from the file: " + file, e);
        }
        return stringBuilder.toString();
    }

    private void writeToFile(String fileName, String stringData) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(stringData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file: " + fileName, e);
        }
    }

    private String createReport(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] dataArray = string.split(LINE_SEPARATOR);
        String[] csvRowData;
        int totalSupply = 0;
        int totalBuy = 0;
        for (String data : dataArray) {
            csvRowData = data.split(CSV_SEPARATOR);
            if (csvRowData[OPERATION_TYPE_INDEX].equals(SUPPLY)) {
                totalSupply += Integer.parseInt(csvRowData[AMOUNT_INDEX]);
                continue;
            }
            totalBuy += Integer.parseInt(csvRowData[AMOUNT_INDEX]);
        }
        stringBuilder
                .append(SUPPLY).append(CSV_SEPARATOR).append(totalSupply).append(LINE_SEPARATOR)
                .append(BUY).append(CSV_SEPARATOR).append(totalBuy).append(LINE_SEPARATOR)
                .append(RESULT).append(CSV_SEPARATOR).append(totalSupply - totalBuy);
        return stringBuilder.toString();
    }
}
