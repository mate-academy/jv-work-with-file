package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String[] OPERATIONS_TYPE = {"supply","buy"};

    public void getStatistic(String fromFileName, String toFileName) {
        String[] dataFromFile = readDataFromFile(fromFileName);
        String dataToFile = getReport(calculateStatistic(dataFromFile));
        writeDataToFile(dataToFile, toFileName);
    }

    private String getReport(int[] operationValues) {
        int result = operationValues[0];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < OPERATIONS_TYPE.length; i++) {
            builder.append(OPERATIONS_TYPE[i]).append(",").append(operationValues[i])
                    .append(LINE_SEPARATOR);
            if (i != 0) {
                result -= operationValues[i];
            }
        }
        builder.append("result,").append(result).append(LINE_SEPARATOR);
        return builder.toString();
    }

    private int[] calculateStatistic(String[] dateFromFile) {
        int[] result = new int[OPERATIONS_TYPE.length];
        for (String data: dateFromFile) {
            for (int i = 0; i < OPERATIONS_TYPE.length; i++) {
                String[] temp = data.split(",");
                if (temp[0].equals(OPERATIONS_TYPE[i])) {
                    result[i] += Integer.parseInt(temp[1]);
                }
            }
        }
        return result;
    }

    private String[] readDataFromFile(String fileName) {
        File fileFrom = new File(fileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileFrom))) {
            int value = reader.read();
            if (value == -1) {
                return new String[0];
            }
            while (value != -1) {
                builder.append((char)value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
        return builder.toString().split(LINE_SEPARATOR);
    }

    private void writeDataToFile(String data, String fileName) {
        File fileTo = new File(fileName);
        if (!fileTo.exists() && fileTo.isDirectory()) {
            fileTo.mkdirs();
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileTo))) {
            bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file" + fileName, e);
        }
    }
}

