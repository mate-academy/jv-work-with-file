package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String DELIMITER = ",";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final int PARTS = 2;

    public void getStatistic(String fromFileName, String toFileName) {
        StringBuilder sb = new StringBuilder();
        int buy = 0;
        int supply = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fromFileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length != PARTS) {
                    throw new RuntimeException(
                            "Can't parse line in file " + fromFileName + ": " + line
                    );
                }
                String operation = parts[0];
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(
                            "Can't parse amount in file " + fromFileName + ": " + line,
                            e
                    );
                }
                line = br.readLine();
                switch (operation) {
                    case "buy":
                        buy += quantity;
                        break;
                    case "supply":
                        supply += quantity;
                        break;
                    default:
                        throw new RuntimeException(
                                "Unknown operation in file " + fromFileName + ": " + line
                        );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read data from file " + fromFileName, e);
        }
        sb.append(SUPPLY).append(DELIMITER).append(supply).append(NEW_LINE);
        sb.append(BUY).append(DELIMITER).append(buy).append(NEW_LINE);
        sb.append(RESULT).append(DELIMITER).append(supply - buy).append(NEW_LINE);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toFileName))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file " + toFileName, e);
        }
    }
}
