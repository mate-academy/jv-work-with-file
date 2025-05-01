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
    private static final String COMMA_SEPARATOR = ",";
    private static final int TYPE_INDEX = 0;
    private static final int VALUE_INDEX = 1;

    private int supply = 0;
    private int buy = 0;
    private int result = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        String data = readFromFile(fromFileName);
        calculateStatistics(data);
        writeStatisticToFile(toFileName);
    }

    private String readFromFile(String fileName) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from the file " + fileName, e);
        }
        return data.toString();
    }

    private void calculateStatistics(String data) {
        supply = 0;
        buy = 0;
        String[] lines = data.split(System.lineSeparator());
        for (String line : lines) {
            String[] parts = line.split(COMMA_SEPARATOR);
            String type = parts[TYPE_INDEX];
            int value = Integer.parseInt(parts[VALUE_INDEX]);
            if (type.equals(SUPPLY)) {
                supply += value;
            } else if (type.equals(BUY)) {
                buy += value;
            }
        }
        result = supply - buy;
    }

    private void writeStatisticToFile(String toFileName) {
        File file = new File(toFileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Cannot create file " + toFileName, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file " + toFileName, e);
        }
    }
}
