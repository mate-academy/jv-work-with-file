package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        int [] dataFrom = getResult(fromFileName);
        updateData(dataFrom, toFileName);
    }

    private int [] getResult(String fromFileN) {
        int supply = 0;
        int buy = 0;
        int result;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFileN))) {
            String data = null;
            while ((data = reader.readLine()) != null) {
                String [] array = data.split(",");
                if (array[0].equals("supply")) {
                    supply += Integer.parseInt(array[1]);
                }
                if (array[0].equals("buy")) {
                    buy += Integer.parseInt(array[1]);
                }
            }
            result = supply - buy;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Can't find file",e);
        } catch (IOException e) {
            throw new RuntimeException("Can't read file",e);
        }
        return new int[] {supply, buy, result};
    }

    private void updateData(int [] source, String toThisFileName) {
        String supply = "supply," + source [0];
        String buy = "buy," + source [1];
        String result = "result," + source [2];

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toThisFileName))) {
            String textToWrite = supply + System.lineSeparator()
                    + buy + System.lineSeparator()
                    + result + System.lineSeparator();
            writer.write(textToWrite);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }

    }
}
