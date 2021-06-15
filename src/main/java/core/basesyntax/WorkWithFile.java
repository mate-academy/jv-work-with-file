package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int SUM_OPERATION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int supply = 0;
        int buy = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = br.readLine()) != null) { // loop for reading data from file while end

                if (line.contains("buy")) { // calculating total "buy"
                    String[] arrayBuy = line.split(",");
                    buy = buy + Integer.parseInt(arrayBuy[SUM_OPERATION]);
                }
                if (line.contains("supply")) { // calculating total "supply"
                    String[] arraySupply = line.split(",");
                    supply += Integer.parseInt(arraySupply[SUM_OPERATION]);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't correctly read data from file " + fromFileName, e);
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {
            String result = "supply," + supply + System.lineSeparator()
                    + "buy," + buy + System.lineSeparator()
                    + "result," + (supply - buy);// info for file
            bufferedWriter.write(result); // write info to file

        } catch (Exception e) {
            throw new RuntimeException("Can't correctly write to the file " + fromFileName, e);
        }
    }
}
