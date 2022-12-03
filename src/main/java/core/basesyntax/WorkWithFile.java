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
    private static final int ONE = 1;
    private static final String B_LETTER = "b";
    private static final String S_LETTER = "s";
    private static final String COMMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = readFromFile(fromFileName);
        String report = createReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    private String createReport(String dataFromFile) {
        String[] linesFromFile = dataFromFile.split(System.lineSeparator());
        int supply = 0;
        int buy = 0;
        int result;
        for (String lineFromFile : linesFromFile) {
            String[] arrayValues = lineFromFile.split(",");
            String stringValue = arrayValues[ONE];
            int value = Integer.parseInt(stringValue);
            if (lineFromFile.startsWith(S_LETTER)) {
                supply += value;
            } else if (lineFromFile.startsWith(B_LETTER)) {
                buy += value;
            }
        }
        result = supply - buy;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator()).append(BUY).append(COMMA)
                .append(buy).append(System.lineSeparator()).append(RESULT)
                .append(COMMA).append(result);
        return stringBuilder.toString();
    }

    private void writeToFile(String toFileName, String report) {
        File file = new File(toFileName);
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.write(report);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close BufferedWriter", e);
                }
            }
        }
    }

    private String readFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                value = value.toLowerCase();
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("No such file");
        }
        return stringBuilder.toString();
    }
}
