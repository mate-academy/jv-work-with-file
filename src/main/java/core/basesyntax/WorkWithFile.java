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

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        for (String string : readFileToList(fromFileName)) {
            String[] stringArray = string.split("\\,");
            int amount = Integer.parseInt(stringArray[1]);
            if (stringArray[0].equals("supply")) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        writeToFile(toFileName, supply, buy);
    }

    private static List<String> readFileToList(String fromFileName) {
        File fromFile = new File(fromFileName);
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile))) {
            String value = reader.readLine();
            while (value != null) {
                list.add(value);
                value = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFile, e);
        }
        return list;
    }

    private static void writeToFile(String toFileName, int supply, int buy) {
        File toFile = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFile, e);
        }
    }
}
