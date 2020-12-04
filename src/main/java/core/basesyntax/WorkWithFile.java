package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileName))) {
            int supply = 0;
            int buy = 0;
            String line = reader.readLine();
            while (line != null) {
                String[] info = line.split(",");
                if (info[0].equals("supply")) {
                    supply += Integer.parseInt(info[1]);
                }
                if (info[0].equals("buy")) {
                    buy += Integer.parseInt(info[1]);
                }
                line = reader.readLine();
            }
            createResultFile(toFileName, supply, buy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private void createResultFile(String toFileName, int supply, int buy) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator() + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write data to file", e);
        }
    }
}
