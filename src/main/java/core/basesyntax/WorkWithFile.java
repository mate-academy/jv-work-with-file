package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final String SUPPLY = "supply";
    public static final String BUY = "buy";
    public static final String RESULT = "result";
    public static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        writeData(toFileName, processData(readData(fromFileName)));
    }

    private String processData(String[] value) {
        int buy = 0;
        int supply = 0;
        String[] arrayData;
        for (String words : value) {
            arrayData = words.split(DELIMITER);
            if (arrayData[0].equals(BUY)) {
                buy += Integer.parseInt(arrayData[1]);
            } else {
                supply += Integer.parseInt(arrayData[1]);
            }
        }
        return SUPPLY + DELIMITER + supply + System.lineSeparator()
                    + BUY + DELIMITER + buy + System.lineSeparator()
                    + RESULT + DELIMITER + (supply - buy);
    }

    private String[] readData(String fromFileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                stringBuilder.append(value).append(System.lineSeparator());
                value = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file: " + fromFileName, e);
        }
        return stringBuilder.toString().split(System.lineSeparator());
    }

    private void writeData(String toFileName, String dataToWrite) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(dataToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data: " + toFileName, e);
        }
    }
}
