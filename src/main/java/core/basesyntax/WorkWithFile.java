package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public static final int WORD_PART = 0;
    public static final int NUMBER_PART  = 1;
    public void getStatistic(String fromFileName, String toFileName) {
        int totalSupplies = 0;
        int totalBuys = 0;
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] singleRecord = line.split(",");
                if (singleRecord[WORD_PART].equals("supply")) {
                    totalSupplies += Integer.parseInt(singleRecord[NUMBER_PART]);
                } else {
                    totalBuys += Integer.parseInt(singleRecord[NUMBER_PART]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can`t read from file", e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            bufferedWriter.write("supply," + totalSupplies + System.lineSeparator());
            bufferedWriter.write("buy," + totalBuys + System.lineSeparator());
            bufferedWriter.write("result," + (totalSupplies - totalBuys));
        } catch (IOException e) {
            throw new RuntimeException("Can`t write to file", e);
        }
    }
}

