package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        writeToFile(getFromFile(fromFileName),toFileName);
    }

    private  String getFromFile(String fromFileName) {
        File file = new File(fromFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = reader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(";");
                value = reader.readLine();
            }
            String fromFileString = stringBuilder.toString();
            if (fromFileString.isEmpty()) {
                return new String();
            }
            return countFromString(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("can't read file");
        }
    }

    private  String countFromString (String input) {
        final String SUPPLY_WORD = "supply";
        final String BUY_WORD = "buy";
        final String RESULT_WORD = "result";
        final int SUPPLY_INDEX = 0;
        final int BUY_INDEX = 1;
        final int NAME_COLUMN_INDEX = 0;
        final int VALUE_COLUMN_INDEX = 1;
        int[] resultValues = new int[]{0,0};
        String[] records = input.split(";");
        for (String record:records) {
            String[] dataRecord = record.split(",");
            if (dataRecord[NAME_COLUMN_INDEX].equals(SUPPLY_WORD)) {
                resultValues[SUPPLY_INDEX] += Integer.parseInt(dataRecord[VALUE_COLUMN_INDEX]);
            } else if (dataRecord[NAME_COLUMN_INDEX].equals(BUY_WORD)) {
                resultValues[BUY_INDEX] += Integer.parseInt(dataRecord[VALUE_COLUMN_INDEX]);
            }
        }
        int resultAmount =  resultValues[SUPPLY_INDEX] - resultValues[BUY_INDEX];
        StringBuilder builder = new StringBuilder();
        builder.append(SUPPLY_WORD).append(",").append(resultValues[SUPPLY_INDEX]);
        builder.append(System.lineSeparator());
        builder.append(BUY_WORD).append(",").append(resultValues[BUY_INDEX]);
        builder.append(System.lineSeparator());
        builder.append(RESULT_WORD).append(",").append(resultAmount);
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    private void writeToFile (String output,String toFileName) {
        File file = new File (toFileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(output);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
