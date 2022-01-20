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
        String[] valluesArray = readFile(fromFileName).split("\\W+");
        for (int i = 0; i < valluesArray.length; i = i + 2) {
            if (valluesArray[i].equals("supply")) {
                supply += Integer.parseInt(valluesArray[i + 1]);
            } else if (valluesArray[i].equals("buy")) {
                buy += Integer.parseInt(valluesArray[i + 1]);
            }
        }
        StringBuilder builder = new StringBuilder();
        String toWrite = "supply," + supply + System.lineSeparator()
                + "buy," + buy + System.lineSeparator()
                + "result," + (supply - buy) + System.lineSeparator();
        writeFile(toWrite, toFileName);
    }

    private String readFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String read = bufferedReader.readLine();
            while (read != null) {
                builder.append(read).append(System.lineSeparator());
                read = bufferedReader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read file - " + fromFileName, e);
        }
        return builder.toString();
    }

    private void writeFile(String whatToWrite, String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write(whatToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Cannot write to file - " + toFileName, e);
        }
    }
}
