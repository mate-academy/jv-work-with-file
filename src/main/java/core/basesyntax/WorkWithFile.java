package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder array = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            do {
                array.append(line).append(" ");
                line = bufferedReader.readLine();
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file", e);
        }

        String[] allLines = array.toString().split(" ");

        int supplySum = 0;
        int buySum = 0;
        int resultSum = 0;

        for (int i = 0; i < allLines.length; i++) {
            String[] singleLine = allLines[i].split(",");
            if (singleLine[0].equals("supply")) {
                supplySum += Integer.valueOf(singleLine[1]);
            }
            if (singleLine[0].equals("buy")) {
                buySum += Integer.valueOf(singleLine[1]);
            }
            if (singleLine[0].equals("return")) {
                resultSum += Integer.valueOf(singleLine[1]);
            }
        }

        try (BufferedWriter bufferedWr = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWr.write("supply,");
            bufferedWr.write(supplySum);
            bufferedWr.newLine();

            bufferedWr.write("buy,");
            bufferedWr.write(buySum);
            bufferedWr.newLine();

            bufferedWr.write("result,");
            bufferedWr.write(resultSum);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file", e);
        }

    }
}
