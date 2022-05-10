package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public static final int SUPPLY_INDEX = 0;
    public static final int BUY_INDEX = 1;
    public static final int RESULT_INDEX = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        String stringLine;
        int[] values = {0, 0, 0};

        try (BufferedReader br = new BufferedReader(new FileReader(fromFile))) {
            while ((stringLine = br.readLine()) != null) {
                if (stringLine.split(",")[0].equals("supply")) {
                    values[SUPPLY_INDEX] += Integer.valueOf(stringLine.split(",")[1]);
                }
                if (stringLine.split(",")[0].equals("buy")) {
                    values[BUY_INDEX] += Integer.valueOf(stringLine.split(",")[1]);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        } finally {
            values[RESULT_INDEX] = values[SUPPLY_INDEX] - values[BUY_INDEX];
        }
        writeToFile(toFileName, new String[]{"supply", "buy", "result"}, values);
    }

    public void writeToFile(String toFileName, String[] params, int[] values) {
        if (params.length != values.length) {
            System.out.println("Params and values has different length: params["
                    + params.length
                    + "] and values[" + values.length + "].");
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            stringBuilder.append(params[i]).append(",").append(values[i])
                    .append(System.lineSeparator());
        }

        File file = new File(toFileName);
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;

        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write or close the file " + toFileName, e);
        }
    }
}
