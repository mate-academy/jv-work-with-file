package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final List<String> list = new ArrayList<>();

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        int result;
        readerFile(fromFileName);
        for (String step : list) {
            String[] arr = step.split(",");
            if (arr[0].equals("buy")) {
                buy += Integer.parseInt(arr[1]);
            } else {
                supply += Integer.parseInt(arr[1]);
            }
        }
        result = supply - buy;
        writerFile(toFileName, buy, supply, result);
        list.clear();
    }

    public void readerFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String read;
            while ((read = reader.readLine()) != null) {
                list.add(read);
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file" + fromFileName, e);
        }
    }

    public void writerFile(String toFileName, int buy, int supply, int result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write data to file" + toFileName, e);
        }
    }
}
