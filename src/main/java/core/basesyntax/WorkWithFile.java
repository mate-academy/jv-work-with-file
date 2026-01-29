package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] sums = readData(fromFileName); // [0] = supply, [1] = buy
        int result = calculateResult(sums[0], sums[1]);
        writeReport(toFileName, sums[0], sums[1], result);
    }

    private int[] readData(String fileName) {
        int supplySum = 0;
        int buySum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0].trim();
                int value = Integer.parseInt(data[1].trim());

                if (SUPPLY.equals(type)) {
                    supplySum += value;
                } else if (BUY.equals(type)) {
                    buySum += value;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fileName, e);
        }

        return new int[]{supplySum, buySum};
    }

    private int calculateResult(int supply, int buy) {
        return supply - buy;
    }

    private void writeReport(String fileName, int supply, int buy, int result) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(SUPPLY + "," + supply);
            bw.newLine();
            bw.write(BUY + "," + buy);
            bw.newLine();
            bw.write(RESULT + "," + result);
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Can't write report to file " + fileName, e);
        }
    }
}



