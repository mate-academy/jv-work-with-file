package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int POSITION_NAME = 0;
    private static final int POSITION_SUM = 1;

    public void getStatistic(String fromFileName, String toFileName) {
        File fromFile = new File(fromFileName);
        File toFile = new File(toFileName);
        StringBuilder strBuilder = new StringBuilder();
        int supplyTotal = 0;
        int buyTotal = 0;
        int result;

        try (BufferedReader reader = new BufferedReader(new FileReader(fromFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFile))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineArray = line.split(",");
                if (lineArray[POSITION_NAME].equals("supply")) {
                    supplyTotal += Integer.parseInt(lineArray[POSITION_SUM]);
                } else {
                    buyTotal += Integer.parseInt(lineArray[POSITION_SUM]);
                }
                line = reader.readLine();
            }
            result = supplyTotal - buyTotal;

            strBuilder.append("supply,").append(supplyTotal).append(System.lineSeparator())
                    .append("buy,").append(buyTotal).append(System.lineSeparator())
                    .append("result,").append(result);

            writer.write(strBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file " + toFileName, e);
        }
    }
}
