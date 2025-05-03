package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final int TYPE_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {
        List<String> dataFromFile = readFromFile(fromFileName);
        String report = getReport(dataFromFile);
        writeToFile(toFileName, report);
    }

    public List<String> readFromFile(String fileName) {
        File file = new File(fileName);
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }
        return result;
    }

    public void writeToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + fileName, e);
        }
    }

    public String getReport(List<String> dataList) {
        int supply = 0;
        int buy = 0;
        for (String data : dataList) {
            String[] arr = data.split(",");
            int amount = Integer.parseInt(arr[AMOUNT_INDEX]);
            if (arr[TYPE_INDEX].equals(SUPPLY)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        return formatReport(supply, buy);
    }

    public String formatReport(int supply, int buy) {
        return "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);
    }
}
