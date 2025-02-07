package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;
        File file = new File(fromFileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String word = "";
            int indexOf = 0;

            while ((line = br.readLine()) != null) {
                indexOf = line.indexOf(',');
                word = line.substring(0, indexOf);
                if (word.equals("supply")) {
                    supply += Integer.parseInt(line.substring(indexOf + 1, line.length()));
                } else if (word.equals("buy")) {
                    buy += Integer.parseInt(line.substring(indexOf + 1, line.length()));
                }

            }
            result = supply - buy;

        } catch (IOException e) {
            throw new RuntimeException("Error reading from file: " + fromFileName, e);
        }

        File toFile = new File(toFileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {

            bufferedWriter.write("supply," + supply + System.lineSeparator());
            bufferedWriter.write("buy," + buy + System.lineSeparator());
            bufferedWriter.write("result," + result + System.lineSeparator());

        } catch (IOException e) {
            throw new RuntimeException("Error writing to file: " + toFileName, e);
        }

    }
}
