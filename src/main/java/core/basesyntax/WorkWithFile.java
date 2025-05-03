package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        WorkWithFile file = new WorkWithFile();
        String[] allLines = file.readDataFromFile(fromFileName).split(" ");

        String supply = new String();
        String buy = new String();
        String result = new String();
        supply = "supply," + calculateSupply(allLines);
        buy = "buy," + calculateBuy(allLines);
        result = "result," + calculateResult(calculateSupply(allLines), calculateBuy(allLines));

        file.writeDataToFile(toFileName, supply, buy, result);
    }

    public String readDataFromFile(String fromFileName) {
        StringBuilder array = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String line = bufferedReader.readLine();
            do {
                array.append(line).append(" ");
                line = bufferedReader.readLine();
            } while (line != null);
        } catch (IOException e) {
            throw new RuntimeException("Can't read the file" + fromFileName, e);
        }
        return array.toString();
    }

    public void writeDataToFile(String toFileName, String supply, String buy, String result) {
        try (BufferedWriter bufferedWr = new BufferedWriter(new FileWriter(toFileName, true))) {
            bufferedWr.write(supply);
            bufferedWr.newLine();
            bufferedWr.write(buy);
            bufferedWr.newLine();
            bufferedWr.write(result);
        } catch (IOException e) {
            throw new RuntimeException("Can't write to the file" + toFileName, e);
        }
    }

    public int calculateSupply(String[] allLines) {
        int supplySum = 0;

        for (int i = 0; i < allLines.length; i++) {
            String[] singleLine = allLines[i].split(",");
            if (singleLine[0].equals("supply")) {
                supplySum += Integer.valueOf(singleLine[1]);
            }
        }
        return supplySum;
    }

    public int calculateBuy(String[] allLines) {
        int buySum = 0;
        for (int i = 0; i < allLines.length; i++) {
            String[] singleLine = allLines[i].split(",");
            if (singleLine[0].equals("buy")) {
                buySum += Integer.valueOf(singleLine[1]);
            }
        }
        return buySum;
    }

    public int calculateResult(int supply, int buy) {
        return supply - buy;
    }
}
