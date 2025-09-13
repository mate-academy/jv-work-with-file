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
        String buyer = "buy";
        String supplier = "supply";
        int buy = 0;
        int supply = 0;

        File file = new File(fromFileName);
        try {
            BufferedReader readLine = new BufferedReader(new FileReader(file));
            String value = readLine.readLine();
            while (value != null) {
                if (value.contains(supplier)) {
                    int index = value.indexOf(",") + 1;
                    String name = value.substring(index, value.length());
                    supply += Integer.parseInt(name);
                } else if (value.contains(buyer)) {
                    int index = value.indexOf(",") + 1;
                    String name = value.substring(index, value.length());
                    buy += Integer.parseInt(name);
                }
                value = readLine.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can`t read document",e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file1 = new File(toFileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write(supplier + "," + supply + System.lineSeparator()
                    + buyer + "," + buy + System.lineSeparator()
                    + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }

}
