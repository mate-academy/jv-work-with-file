package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String NEWLINE = System.lineSeparator();
    private static final String SEPARATOR = ",";

    public String getDataFromFile(String filePath) {
        File myFile = new File(filePath);
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(myFile)) {
            BufferedReader reader = new BufferedReader(fileReader);
            int value = reader.read();
            while (value != -1) {
                stringBuilder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t find file", e);
        }
        return stringBuilder.toString();

    }

    public String getFieldName(String record) {
        return record.substring(0, record.indexOf(","));
    }

    public void writeReportToFile(String filePath, String report) {
        File myFile = new File(filePath);
        try (FileWriter writer = new FileWriter(myFile)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant`t write data to file", e);
        }
    }

    public int getIntValueFromRecord(String record) {
        String trimmedRecord = record.trim();
        String value = trimmedRecord
                .substring(trimmedRecord.indexOf(",") + 1);
        return Integer.parseInt(value);
    }

    public int[] countItems(String[] data) {
        int supplyCounter = 0;
        int buyCounter = 0;
        for (String record : data) {
            if (getFieldName(record).equals(SUPPLY)) {
                supplyCounter += getIntValueFromRecord(record);
            }
            if (getFieldName(record).equals(BUY)) {
                buyCounter += getIntValueFromRecord(record);
            }
        }
        return new int[] {supplyCounter, buyCounter};
    }

    public String generateReport(String[] data) {
        int[] countedItems = countItems(data);
        StringBuilder stringBuilder = new StringBuilder();
        int supply = countedItems[0];
        int buy = countedItems[1];
        stringBuilder.append(SUPPLY)
                .append(SEPARATOR)
                .append(supply)
                .append(NEWLINE)
                .append(BUY)
                .append(SEPARATOR)
                .append(buy)
                .append(NEWLINE)
                .append(RESULT)
                .append(SEPARATOR)
                .append(supply - buy);
        return stringBuilder.toString();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = getDataFromFile(fromFileName).split("\n");
        String report = generateReport(data);
        writeReportToFile(toFileName,report);
    }

}
