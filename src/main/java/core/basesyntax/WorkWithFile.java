package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY_STRING = "supply";
    private static final String FILE_NOT_FOUND = "File not found";
    private static final String READING_FILE_ERROR = "Reading file error";
    private static final String COMA = ",";
    private static final int POSITION_OF_SUPPLY = 0;
    private static final int POSITION_OF_BUY = 1;
    private static final int POSITION_OF_QUANTITY = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputData = getDataFromFile(fromFileName);
        int[] countResult = countData(inputData);
        writeDataToFile(toFileName, countResult);
    }

    private String[] getDataFromFile(String fromFileName) {
        File fileToRead = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        String[] inputData;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String value;
            while ((value = bufferedReader.readLine()) != null) {
                builder.append(value).append(" ");
            }
            String line = new String(builder);
            inputData = line.split(" ");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(FILE_NOT_FOUND, e);
        } catch (IOException e) {
            throw new RuntimeException(READING_FILE_ERROR, e);
        }
        return inputData;
    }

    private void writeDataToFile(String toFileName, int[] countResult) {
        File fileToWrite = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            writer.write(createReport(countResult));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(FILE_NOT_FOUND, e);
        } catch (IOException e) {
            throw new RuntimeException(READING_FILE_ERROR, e);
        }
    }

    private int[] countData(String[] inputData) {
        int supply = 0;
        int buy = 0;
        for (String line: inputData) {
            String[] record = line.split(COMA);
            if (record[0].equals(SUPPLY_STRING)) {
                supply += Integer.parseInt(record[POSITION_OF_QUANTITY]);
            } else {
                buy += Integer.parseInt(record[POSITION_OF_QUANTITY]);
            }
        }
        return new int[] {supply, buy};
    }

    private String createReport(int[] countResult) {
        String result = SUPPLY_STRING + COMA + countResult[POSITION_OF_SUPPLY]
                + System.lineSeparator();
        result += "buy," + countResult[POSITION_OF_BUY] + System.lineSeparator();
        result += "result," + (countResult[POSITION_OF_SUPPLY] - countResult[POSITION_OF_BUY]);
        return result;
    }
}
