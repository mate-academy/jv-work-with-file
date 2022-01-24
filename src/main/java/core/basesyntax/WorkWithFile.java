package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int INDEX_ARRAY_ZERO = 0;
    private static final int INDEX_ARRAY_ONE = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] data = readFile(fromFileName).split(System.lineSeparator());
        String[] lineData;
        int supply = 0;
        int buy = 0;
        int result;
        for (int i = 0; i < data.length; i++) {
            lineData = data[i].split(SEPARATOR);
            if (lineData[INDEX_ARRAY_ZERO].startsWith(SUPPLY)) {
                supply += Integer.parseInt(lineData[INDEX_ARRAY_ONE]);
            } else if (lineData[INDEX_ARRAY_ZERO].startsWith(BUY)) {
                buy += Integer.parseInt(lineData[INDEX_ARRAY_ONE]);
            }
        }
        result = supply - buy;
        writeData(toFileName, supply, buy, result);
    }

    private String readFile(String file) {
        File fileName = new File(file);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                stringBuilder.append(value).append(System.lineSeparator());
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }

    private void writeData(String file, int supply, int buy, int result) {
        File fileName = new File(file);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SUPPLY).append(SEPARATOR).append(supply)
                    .append(System.lineSeparator()).append(BUY).append(SEPARATOR)
                    .append(buy).append(System.lineSeparator()).append(RESULT)
                    .append(SEPARATOR).append(result);
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file", e);
        }
    }
}
