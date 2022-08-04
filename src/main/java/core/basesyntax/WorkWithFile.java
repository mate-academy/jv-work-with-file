package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String BUY = "buy";
    private static final String SUPPLY = "supply";
    private static final String COMA = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        String input = readFromFile(fromFileName);
        String[] report = makeReport(input);
        writeToFile(toFileName, report);
    }

    private String readFromFile(String fromFileName) {
        StringBuilder builder = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            int value = reader.read();
            while (value != -1) {
                builder.append((char) value);
                value = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read file", e);
        }
        return builder.toString();
    }

    private String[] makeReport(String input) {
        int buyWord = 0;
        int supplyWord = 0;
        String[] strings = input.split(System.lineSeparator());
        for (String row : strings) {
            String[] comaDivided = row.split(COMA);
            if (row.contains(BUY)) {
                buyWord += Integer.parseInt(comaDivided[1]);
            } else if (row.contains(SUPPLY)) {
                supplyWord += Integer.parseInt(comaDivided[1]);
            }
        }
        int differenceBuySupply = supplyWord - buyWord;
        String[] buysAndSupplies = new String[3];
        buysAndSupplies[0] = "supply," + supplyWord + System.lineSeparator();
        buysAndSupplies[1] = "buy," + buyWord + System.lineSeparator();
        buysAndSupplies[2] = "result," + differenceBuySupply;
        return buysAndSupplies;
    }

    private void writeToFile(String toFileName, String[] buysAndSupplies) {
        for (String buySupply : buysAndSupplies) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new
                    FileWriter(toFileName, true))) {
                bufferedWriter.write(buySupply);
            } catch (IOException e) {
                throw new RuntimeException("Can't write data to file", e);
            }
        }
    }
}
