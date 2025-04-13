package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            String line = bufferedReader.readLine();
            int supply = 0;
            int buy = 0;
            while (line != null) {
                String[] spllitedLine = line.split(",");

                if ("supply".equals(spllitedLine[0])) {
                    supply += Integer.parseInt(spllitedLine[1]);
                } else {
                    buy += Integer.parseInt(spllitedLine[1]);
                }
                line = bufferedReader.readLine();
            }

            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply - buy));
            bufferedWriter.flush();
            bufferedWriter.close();
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException("Cant read or write file", e);
        }
    }
}
