package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

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

    public void writeReportToFile(String filePath, String report) {
        File myFile = new File(filePath);
        try (FileWriter writer = new FileWriter(myFile)) {
            writer.write(report);
        } catch (IOException e) {
            throw new RuntimeException("Cant`t write data to file", e);
        }
    }

    public String getFieldName(String record) {
        return record.substring(0, record.indexOf(","));
    }

    public int getIntValueFromRecord(String record) {
        String trimmedRecord = record.trim();
        String value = trimmedRecord
                .substring(trimmedRecord.indexOf(",") + 1);
        return Integer.parseInt(value);
    }

    public String getUniqueFieldsString(String[] data) {
        StringBuilder uniqueFields = new StringBuilder();
        for (String record : data) {
            if (!(uniqueFields.toString().contains(getFieldName(record)))) {
                uniqueFields.append(getFieldName(record)).append(" ");
            }
        }
        return uniqueFields.toString().trim();
    }

    public String[] getUniqueFieldsArray(String uniqueFields) {
        return uniqueFields.split(" ");
    }

    public int[] getContainerArray(String[] unuiqueFieldsArray) {
        return new int[unuiqueFieldsArray.length];
    }

    public int[] countItems(String[] uniqueFieldsArray, String[] data) {
        int[] countedItems = getContainerArray(uniqueFieldsArray);
        for (int i = 0; i < uniqueFieldsArray.length; i++) {
            for (String record : data) {
                if (uniqueFieldsArray[i].equals(getFieldName(record))) {
                    countedItems[i] += getIntValueFromRecord(record);
                }
            }
        }
        return countedItems;
    }

    public String generateReport(String[] uniqueFieldsArray, int[] valuesArray) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("supply,")
                .append(valuesArray[1])
                .append("buy,")
                .append(valuesArray[0]);
        int result = valuesArray[1] - valuesArray[0];
        result = Math.abs(result);
        stringBuilder.append("result,").append(result);
        return stringBuilder.toString().trim();
    }

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = getDataFromFile(fromFileName).split("\n");
        String uniqueFieldsString = getUniqueFieldsString(data);
        String[] uniqueFieldsArray = getUniqueFieldsArray(uniqueFieldsString);
        int[] countedItems = countItems(uniqueFieldsArray, data);
        String report = generateReport(uniqueFieldsArray, countedItems);
        writeReportToFile(toFileName,report);
    }
}
