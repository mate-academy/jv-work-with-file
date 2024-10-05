package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;
        int result = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] array = line.split(",");
                if (array.length == 2) {
                    String type = array[0].trim();
                    int value = Integer.parseInt(array[1].trim());

                    if (type.equals("supply")) {
                        supply += value;
                    } else if (type.equals("buy")) {
                        buy += value;
                    }
                }
            }

            result = supply - buy;

            String[] results = new String[]{"supply," + supply, "buy," + buy, "result," + result};
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
                for (String res : results) {
                    bufferedWriter.write(res);
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't write to file", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
    }
}
