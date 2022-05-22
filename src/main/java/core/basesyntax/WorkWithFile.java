package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String[] namesOfAction = new String[] {"supply", "buy"};
    private static final int COL_ACTIONS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        String inputData = this.readingFile(fromFileName);
        String dataToWrite = calculator(inputData);
        writtingFile(dataToWrite, toFileName);
    }

    private void writtingFile(String outputData, String toFileName) {
        File file = new File(toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(outputData);
        } catch (IOException e) {
            throw new RuntimeException("Can't write file " + toFileName, e);
        }
    }

    private String calculator(String inputData) {
        String[] values = new String[COL_ACTIONS];
        int[] sumOfRecords = new int[COL_ACTIONS];
        String[] records = inputData.split(System.lineSeparator());
        for (int i = 0; i < namesOfAction.length; i++) {
            for (int j = 0; j < records.length; j++) {
                String[] record = records[j].split(",");
                if (namesOfAction[i].equals(record[0])) {
                    sumOfRecords[i] += Integer.parseInt(record[1]);
                }
            }
        }
        StringBuilder calculatedData = new StringBuilder();
        for (int i = 0; i < namesOfAction.length; i++) {
            calculatedData.append(namesOfAction[i]).append(",").append(sumOfRecords[i])
                    .append(System.lineSeparator());
        }
        calculatedData.append("result,").append(sumOfRecords[0] - sumOfRecords[1]);
        return calculatedData.toString();
    }

    private String[] getNames(String data) {
        String[] linesData = data.split(System.lineSeparator());
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < linesData.length; i++) {
            if (stringBuilder.toString().matches(".*"
                    + (linesData[i].split(",")[0]) + ".*")) {
                continue;
            }
            stringBuilder.append(linesData[i].split(",")[0]).append(" ");
        }
        return stringBuilder.toString().trim().split(" ");
    }

    private String readingFile(String fromFileName) {
        File file = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFileName, e);
        }
        return builder.toString();
    }

}
