package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int NAME_POS = 0;
    private static final int NUM_POS = 1;
    private int buySum;
    private int supplySum;
    private int result;

    public void getStatistic(String fromFileName, String toFileName) {
        File toFile = new File(toFileName);
        readFile(fromFileName);
        writeFile(buySum, supplySum, result, toFile);
    }

    private void readFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            String value = bufferedReader.readLine();
            while (value != null) {
                String[] lineArray = value.split(",");
                if (lineArray[NAME_POS].equals("supply")) {
                    supplySum += Integer.parseInt(lineArray[NUM_POS]);
                } else {
                    buySum += Integer.parseInt(lineArray[NUM_POS]);
                }
                value = bufferedReader.readLine();
            }
            result = supplySum - buySum;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeFile(int buySum, int supplySum, int result, File toFile) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFile))) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("supply,").append(supplySum).append(System.lineSeparator())
                    .append("buy,").append(buySum).append(System.lineSeparator())
                    .append("result,").append(result);
            bufferedWriter.write(stringBuilder.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.buySum = 0;
        this.supplySum = 0;
        this.result = 0;
    }
}
