package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private int supply = 0;
    private int buy = 0;

    private void getDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] name = value.split(",", 0);
                if (name[0].equals("supply")) {
                    supply += Integer.parseInt(name[1]);
                }
                if (name[0].equals("buy")) {
                    buy += Integer.parseInt(name[1]);
                }
                value = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Error file read ", e);
        } catch (IOException e) {
            throw new RuntimeException("Error read line", e);
        }
    }

    private void writeData(String toFileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply - buy));
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }

    public void getStatistic(String fromFileName,String toFileName) {
        getDataFromFile(fromFileName);
        writeData(toFileName);
    }
}
