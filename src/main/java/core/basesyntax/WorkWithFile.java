package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int buy = 0;
        int supply = 0;
        int result = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            StringBuilder allLine = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                allLine.append(line).append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            String [] data = allLine.toString().split(System.lineSeparator());
            String [] temp = new String[2];
            for (int i = 0; i < data.length; i++) {
                temp = data[i].split(",");
                if (temp[0].equals("buy")) {
                    buy += Integer.parseInt(temp[1]);
                }
                if (temp[0].equals("supply")) {
                    supply += Integer.parseInt(temp[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        result = supply - buy;

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
