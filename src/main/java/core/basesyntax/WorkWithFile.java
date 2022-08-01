package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        List<String> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFile));
            String value = reader.readLine();
            while (value != null) {
                list.add(value);
                value = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file" + fromFile, e);
        }
        int supply = 0;
        int buy = 0;
        for (String string : list) {
            String[] stringArray = string.split("\\,");
            if (stringArray[0].equals("supply")) {
                supply += Integer.parseInt(stringArray[1]);
            } else {
                buy += Integer.parseInt(stringArray[1]);
            }
        }
        int result = supply - buy;
        File toFile = new File(toFileName);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(toFile));
            writer.write("supply," + supply + System.lineSeparator());
            writer.write("buy," + buy + System.lineSeparator());
            writer.write("result," + result);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file" + toFile, e);
        }
    }
}
