package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private final String SUPPLY_STRING = "supply";
    private final String BUY_STRING ="buy,";
    private final String RESULT_STRING = "result,";
    private final String FILE_NOT_FOUND = "File not found";
    private final String READING_FILE_ERROR = "Reading file error";

    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputData = getDataFromFile(fromFileName);
        int supply = 0;
        int buy = 0;
        for (String line: inputData) {
            String[] record = line.split(",");
            if (record[0].equals(SUPPLY_STRING)) {
                supply += Integer.parseInt(record[1]);
            } else {
                buy += Integer.parseInt(record[1]);
            }
        }
        writeDataToFile(toFileName, supply, buy);
    }

    private String[] getDataFromFile(String fromFileName) {
        File fileToRead = new File(fromFileName);
        StringBuilder builder = new StringBuilder();
        String[] inputData;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                builder.append(value).append(" ");
                value = bufferedReader.readLine();
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

    private void writeDataToFile(String toFileName, int supply, int buy) {
        File fileToWrite = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            writer.write(SUPPLY_STRING + "," + supply + System.lineSeparator());
            writer.write(BUY_STRING + buy + System.lineSeparator());
            writer.write(RESULT_STRING + (supply - buy));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(FILE_NOT_FOUND, e);
        } catch (IOException e) {
            throw new RuntimeException(READING_FILE_ERROR, e);
        }
    }
}
