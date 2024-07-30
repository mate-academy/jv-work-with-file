package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        String text;
        int supplyAmount = 0;
        int buyAmount = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((text = bufferedReader.readLine()) != null) {
                String[] splitLine = text.split(",");
                if (splitLine[0].equals("supply")) {
                    supplyAmount += Integer.parseInt(splitLine[1]);
                    //Check is the "supply" operation type and increase supply amount.
                } else if (splitLine[0].equals("buy")) {
                    buyAmount += Integer.parseInt(splitLine[1]);
                    //Check is the "buy" operation type and increase buy amount.
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file" + fromFileName, e);
        }
        builder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount); //Build final statistic string.
        String statistic = builder.toString();

        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(statistic);
            //Write final statistic to file.
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
