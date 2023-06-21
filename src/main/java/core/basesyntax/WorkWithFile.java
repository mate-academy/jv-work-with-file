package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final int OPERATION_INDEX = 0;
    private static final int AMOUNT_INDEX = 1;
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private int supplyAmount = 0;
    private int buyAmount = 0;

    public void getStatistic(String fromFileName, String toFileName) {
        supplyAmount = 0;
        buyAmount = 0;
        String[] dataArray = readFromFile(fromFileName).split(System.lineSeparator());
        for (String data : dataArray) {
            String[] dataPerElement = data.split(",");
            if (dataPerElement[OPERATION_INDEX].equals(SUPPLY)) {
                supplyAmount += Integer.parseInt(dataPerElement[AMOUNT_INDEX]);
            }
            if (dataPerElement[OPERATION_INDEX].equals(BUY)) {
                buyAmount += Integer.parseInt(dataPerElement[AMOUNT_INDEX]);
            }
        }
        writeToFile(toFileName);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            String data = reader.readLine();
            while (data != null) {
                builder.append(data).append(System.lineSeparator());
                data = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't find the file", e);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't read from file", e);
        }
        return builder.toString();
    }

    private void writeToFile(String toFileName) {
        int result = supplyAmount - buyAmount;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {
            writer.write("supply," + supplyAmount + System.lineSeparator());
            writer.write("buy," + buyAmount + System.lineSeparator());
            writer.write("result," + result);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't write data to file", e);
        }
    }
}
