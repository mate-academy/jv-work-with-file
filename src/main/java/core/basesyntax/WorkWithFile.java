package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int START_AMOUNT_VALUE = 0;
    private static final int SPLIT_POINT_0 = 0;
    private static final int SPLIT_POINT_1 = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder builder = new StringBuilder();
        String text;
        int supplyAmount = START_AMOUNT_VALUE;
        int buyAmount = START_AMOUNT_VALUE;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            while ((text = bufferedReader.readLine()) != null) {
                String[] splitLine = text.split(",");
                if (splitLine[SPLIT_POINT_0].equals("supply")) {
                    supplyAmount += Integer.parseInt(splitLine[SPLIT_POINT_1]);
                    //Check is the "supply" operation type and increase supply amount.
                } else if (splitLine[SPLIT_POINT_0].equals("buy")) {
                    buyAmount += Integer.parseInt(splitLine[SPLIT_POINT_1]);
                    //Check is the "buy" operation type and increase buy amount.
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read a file " + fromFileName, e);
        }
        builder.append("supply,").append(supplyAmount).append(System.lineSeparator())
                .append("buy,").append(buyAmount).append(System.lineSeparator())
                .append("result,").append(supplyAmount - buyAmount); //Build final statistic string.
        String statistic = builder.toString();
        writeFile(statistic, toFileName);
    }

    private void writeFile(String data, String toFileName) {
        try (FileWriter fileWriter = new FileWriter(toFileName)) {
            fileWriter.write(data);
            //Write final statistic to file.
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
