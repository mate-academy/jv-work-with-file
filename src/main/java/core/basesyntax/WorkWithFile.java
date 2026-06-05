package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final char SUPPLY_FIRST_CHAR = 's';

    public void getStatistic(String fromFileName, String toFileName) {
        int supply = 0;
        int buy = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toFileName))) {

            String line = bufferedReader.readLine();

            while (line != null) {
                int amountStartIndex = line.charAt(0) == SUPPLY_FIRST_CHAR ? 7 : 4;
                int amount = Integer.parseInt(line.substring(amountStartIndex));

                if (line.charAt(0) == SUPPLY_FIRST_CHAR) {
                    supply += amount;
                } else {
                    buy += amount;
                }

                line = bufferedReader.readLine();
            }

            writeRecord(bufferedWriter, SUPPLY, supply);
            writeRecord(bufferedWriter, BUY, buy);
            writeRecord(bufferedWriter, RESULT, supply - buy);
        } catch (IOException e) {
            throw new RuntimeException("Can't process file", e);
        }
    }

    private void writeRecord(BufferedWriter bufferedWriter,
                             String key, int value) throws IOException {
        bufferedWriter.write(key + "," + value);
        bufferedWriter.newLine();
    }
}
