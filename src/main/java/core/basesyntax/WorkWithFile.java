package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final int ONE = 1;
    private static final int ZERO = 0;
    private static final String COMMA = ",";
    private static final String WHITESPACE = " ";

    public void getStatistic(String fromFileName, String toFileName) {

        String[] buysSupplies = readFromFile(fromFileName);
        int buy = getSupplyBuyResult(buysSupplies, BUY);
        int supply = getSupplyBuyResult(buysSupplies, SUPPLY);

        try (FileWriter writerToFile = new FileWriter(toFileName)) {
            writerToFile.append(SUPPLY).append(COMMA)
                    .append(String.valueOf(supply))
                    .append(System.lineSeparator())
                    .append(BUY).append(COMMA)
                    .append(String.valueOf(buy))
                    .append(System.lineSeparator())
                    .append("result,").append(String.valueOf(supply - buy));
        } catch (IOException e) {
            throw new RuntimeException(toFileName + " file wasn't found!", e);
        }
    }

    private String[] readFromFile(String file) {
        StringBuilder csvFile = new StringBuilder();

        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(file))) {
            String value;
            while ((value = readerFromFile.readLine()) != null) {
                csvFile.append(value).append(WHITESPACE);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + file, e);
        }
        return csvFile.toString().split(WHITESPACE);
    }

    private int getSupplyBuyResult(String[] suppliesBuys, String targetString) {
        int result = ZERO;

        for (String buySupply : suppliesBuys) {
            String[] data = buySupply.split(COMMA);
            result += data[ZERO].equals(targetString) ? Integer.parseInt(data[ONE]) : ZERO;
        }
        return result;
    }
}
