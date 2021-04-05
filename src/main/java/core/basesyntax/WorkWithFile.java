package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String COMMA = ",";
    private static final String SUPPLY = "supply";
    private static final String RESULT = "result";
    private static final int AMOUNT_INDEX = 1;
    private static final int OPERATION_INDEX = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String dataFromFile = analyseDataFromFile(fromFileName);
        writeDataToFile(dataFromFile, toFileName);
    }

    private void writeDataToFile(String dataToFile, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(dataToFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + fileName, e);
        }
    }

    private String analyseDataFromFile(String fileName) {
        int buy = 0;
        int supply = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] value = line.split(COMMA);
                if (value[OPERATION_INDEX].equals(BUY)) {
                    buy += Integer.parseInt(value[AMOUNT_INDEX]);
                } else if (value[OPERATION_INDEX].equals(SUPPLY)) {
                    supply += Integer.parseInt(value[AMOUNT_INDEX]);
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fileName, e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(SUPPLY).append(COMMA).append(supply)
                .append(System.lineSeparator())
                .append(BUY).append(COMMA).append(buy)
                .append(System.lineSeparator())
                .append(RESULT).append(COMMA).append(supply - buy);
        return stringBuilder.toString();
    }
}
