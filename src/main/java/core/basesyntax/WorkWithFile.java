package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int SUPPLY = 0;
    private static final int BUY = 1;
    private static final int RESULT = 2;
    private static final int NUMBER_OF_VALUES = 3;
    private static final String SEPARATOR = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeFile(toFileName, createReport(readFile(fromFileName)));
    }

    private StringBuilder readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder dataBuilder = new StringBuilder();
            String readLine = reader.readLine();
            while (readLine != null) {
                String[] arrReadLine = readLine.split(SEPARATOR);
                for (String wordFromLine : arrReadLine) {
                    dataBuilder.append(wordFromLine)
                            .append(System.lineSeparator());
                }
                readLine = reader.readLine();
            }
            return dataBuilder;
        } catch (IOException e) {
            throw new RuntimeException("Can't reach file", e);
        }
    }

    private String[] createReport(StringBuilder dataValuesBuilder) {
        String[] splitData = dataValuesBuilder.toString()
                .split(System.lineSeparator());
        int[] values = new int[NUMBER_OF_VALUES];
        for (int i = 0; i < splitData.length; i++) {
            if (splitData[i].equals("supply")) {
                values[SUPPLY] += Integer.parseInt(splitData[i + 1]);
            } else if (splitData[i].equals("buy")) {
                values[BUY] += Integer.parseInt(splitData[i + 1]);
            }
        }
        values[RESULT] = values[SUPPLY] - values[BUY];
        String[] formattedResult = new String[NUMBER_OF_VALUES];
        String[] titles = new String[]{"supply", "buy", "result"};
        for (int i = 0; i < formattedResult.length; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(titles[i]).append(SEPARATOR).append(values[i]);
            formattedResult[i] = builder.toString();
        }
        return formattedResult;
    }

    private void writeFile(String toFileName, String[] formattedResult) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            for (String result : formattedResult) {
                bufferedWriter.write(result + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't write file", e);
        }
    }
}
