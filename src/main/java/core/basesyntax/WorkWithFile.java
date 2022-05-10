package core.basesyntax;

import java.io.*;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        String stringLine;
        int[] values = {0, 0, 0};

        try (BufferedReader br = new BufferedReader(new FileReader(fromFile))) {
            while ((stringLine = br.readLine()) != null) {
                int value = Integer.parseInt(stringLine.substring(stringLine.indexOf(",") + 1));
                if (stringLine.substring(0, stringLine.indexOf(",")).equals("supply")) {
                    values[0] += value;
                }
                if (stringLine.substring(0, stringLine.indexOf(",")).equals("buy")) {
                    values[1] += value;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + fromFileName, e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        } finally {
            values[2] = values[0] - values[1];
        }

        try {
            writeToFile(toFileName, new String[]{"supply", "buy", "result"}, values);
        } catch (WorkWithFilesException e) {
            throw new RuntimeException("Can't run writeToFile method", e);
        }
    }

    public void writeToFile(String toFileName, String[] params, int[] values)
            throws WorkWithFilesException {
        if (params.length != values.length) {
            throw new WorkWithFilesException("Params and values has different length: params["
                    + params.length
                    + "] and values[" + values.length + "].");
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
