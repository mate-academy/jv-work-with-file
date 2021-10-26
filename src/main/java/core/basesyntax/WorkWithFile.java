package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private static final List<String> listDataFile = new ArrayList<>();

    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        int result;
        readerFile(fromFileName);
        for (String step : listDataFile) {
            String[] splitData = step.split(",");
            if (splitData[0].equals("buy")) {
                buy += Integer.parseInt(splitData[1]);
            } else {
                supply += Integer.parseInt(splitData[1]);
            }
        }
        result = supply - buy;
        writerFile(toFileName, buy, supply, result);
        listDataFile.clear();
    }

    public void readerFile(String fromFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            String read;
            while ((read = reader.readLine()) != null) {
                listDataFile.add(read);
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
