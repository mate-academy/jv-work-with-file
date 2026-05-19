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
        File file = new File(fromFileName);
        String line = null;
        int supply = 0;
        int buy = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            line = br.readLine();
            while (line != null) {
                String[] record = line.split(",");
                if(record[0].equals("supply")){
                    supply += Integer.parseInt(record[1]);
                } else {
                    buy += Integer.parseInt(record[1]);
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't dead file", e);
        }

        String result = "supply," +supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy);

        try (BufferedWriter bf = new BufferedWriter(new FileWriter(toFileName))) {
            bf.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't create file", e);
        }
    }
}
