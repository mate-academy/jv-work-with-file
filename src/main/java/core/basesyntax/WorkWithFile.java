package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SEPARATOR = ",";
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";

    public void getStatistic(String fromFileName, String toFileName) {
        File file = new File(fromFileName);
        int buyCount = 0;
        int supplyCount = 0;
        int resultCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(toFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(SEPARATOR);
                if (data[0].equals(SUPPLY)) {
                    supplyCount += Integer.parseInt(data[1]);
                } else if (data[0].equals(BUY)) {
                    buyCount += Integer.parseInt(data[1]);
                } else if (data[0].equals(RESULT)) {
                    resultCount = Integer.parseInt(data[1]);
                }
            }

            writer.write(SUPPLY + SEPARATOR + supplyCount);
            writer.newLine();
            writer.write(BUY + SEPARATOR + buyCount);
            writer.newLine();
            writer.write(RESULT + SEPARATOR + (supplyCount - buyCount));
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
    }
}
