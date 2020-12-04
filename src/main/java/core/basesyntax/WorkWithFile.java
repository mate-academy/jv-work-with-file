package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";

    public void getStatistic(String fromFileName, String toFileName) {

        String[] buysSupplies = readerFromFile(fromFileName);
        int buy = supplyBuyResult(buysSupplies, BUY);
        int supply = supplyBuyResult(buysSupplies, SUPPLY);

        try (FileWriter writerToFile = new FileWriter(toFileName)) {
            writerToFile.append(SUPPLY).append(",")
                    .append(String.valueOf(supply))
                    .append(System.lineSeparator())
                    .append(BUY).append(",")
                    .append(String.valueOf(buy))
                    .append(System.lineSeparator())
                    .append("result,").append(String.valueOf(supply - buy));
        } catch (IOException e) {
            throw new RuntimeException(toFileName + " file wasn't found!", e);
        }
    }

    private String[] readerFromFile(String file) {
        StringBuilder csvFile = new StringBuilder();

        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(file))) {
            String value;
            while ((value = readerFromFile.readLine()) != null) {
                String[] data = value.split("\n");
                csvFile.append(data[0]).append(" ");
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + file, e);
        }
        return csvFile.toString().split(" ");
    }

    private int supplyBuyResult(String[] suppliesBuys, String compare) {
        int result = 0;

        for (String buySupply : suppliesBuys) {
            String[] data = buySupply.split(",");
            result += data[0].equals(compare) ? Integer.parseInt(data[1]) : 0;

        }
        return result;
    }
}
