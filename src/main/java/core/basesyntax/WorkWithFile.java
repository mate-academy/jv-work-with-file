package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        String[] inputData = getDataFromFile(fromFileName);
        int supply = 0;
        int buy = 0;
        for (String line: inputData) {
            String[] record = line.split(",");
            if (record[0].equals("supply")) {
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
            throw new RuntimeException("File not Found", e);
        } catch (IOException e) {
            throw new RuntimeException("Reading file error", e);
        }
        return inputData;
    }

    private void writeDataToFile(String toFileName, int supply, int buy) {
        File fileToWrite = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + (supply - buy));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not Found", e);
        } catch (IOException e) {
            throw new RuntimeException("Writing file error", e);
        }
    }
}
