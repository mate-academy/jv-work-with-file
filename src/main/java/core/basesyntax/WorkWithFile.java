package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class WorkWithFile {
    private static final int SUM_OPERATION = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            File file = new File(fromFileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int buy = 0;
            int supply = 0;

            while (true) { // loop for reading data from file while end
                line = br.readLine();
                if (line == null) { // check null
                    break;
                }
                if (line.contains("buy")) { // calculating total "buy"
                    String[] arrayBuy = line.split(",");
                    buy = buy + Integer.parseInt(arrayBuy[SUM_OPERATION]);
                }
                if (line.contains("supply")) { // calculating total "buy"
                    String[] arraySupply = line.split(",");
                    supply += Integer.parseInt(arraySupply[SUM_OPERATION]);
                }
            }

            StringBuilder result = new StringBuilder(); // info for file
            result
                    .append("supply,").append(supply).append(System.lineSeparator())
                    .append("buy,").append(buy).append(System.lineSeparator())
                    .append("result,").append(supply - buy);

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName));
            bufferedWriter.write(result.toString()); // write info to file
            bufferedWriter.close();

        } catch (Exception e) {
            throw new RuntimeException("Can't correctly read data from file " + fromFileName, e);
        }
    }
}

