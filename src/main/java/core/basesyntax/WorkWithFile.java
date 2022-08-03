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
    private static final String COMMA_DELIMITER = "\\,";
    private static final String SUPPLY_NAME = "supply";
    private static final byte CATEGORY_NAME_INDEX = 0;
    private static final byte AMOUNT_INDEX = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        for (String string : readFileToList(fromFileName)) {
            String[] stringArray = string.split(COMMA_DELIMITER);
            String categoryName = stringArray[CATEGORY_NAME_INDEX];
            int amount = Integer.parseInt(stringArray[AMOUNT_INDEX]);
            if (categoryName.equals(SUPPLY_NAME)) {
                supply += amount;
            } else {
                buy += amount;
            }
        }
        writeToFile(toFileName, supply, buy);
    }

    private List<String> readFileToList(String fromFileName) {
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

    private void writeToFile(String toFileName, int supply, int buy) {
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
