package core.basesyntax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";
    private static final String NEW_LINE = "\n";

    public void getStatistic(String fromFileName, String toFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fromFileName));
            FileWriter writer = new FileWriter(toFileName);

            String line = reader.readLine();
            int supplyTotal = 0;
            int buyTotal = 0;

            while (line != null) {
                String[] values = line.split(DELIMITER);
                if (values[0].equals(SUPPLY)) {
                    supplyTotal += Integer.parseInt(values[1]);
                } else if (values[0].equals(BUY)) {
                    buyTotal += Integer.parseInt(values[1]);
                }
                line = reader.readLine();
            }

            int result = supplyTotal - buyTotal;

            writer.write(SUPPLY + DELIMITER + supplyTotal + NEW_LINE);
            writer.write(BUY + DELIMITER + buyTotal + NEW_LINE);
            writer.write(RESULT + DELIMITER + result + NEW_LINE);

            reader.close();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file: " + toFileName, e);
        }
    }
}
