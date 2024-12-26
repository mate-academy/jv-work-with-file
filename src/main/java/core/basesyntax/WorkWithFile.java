package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] line = value.split(",");
                if (line.length == 2) {
                    String word = line[0].trim();
                    int number = Integer.parseInt(line[1].trim());

                    if ("supply".equals(word)) {
                        supply += number;
                    } else if ("buy".equals(word)) {
                        buy += number;
                    }
                }
                value = bufferedReader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Cannot find file", e);
        } catch (IOException e) {
            throw new RuntimeException("Cannot find file", e);
        }

        int result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply);
            bufferedWriter.newLine();
            bufferedWriter.write("buy," + buy);
            bufferedWriter.newLine();
            bufferedWriter.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write file", e);
        }

    }
}
