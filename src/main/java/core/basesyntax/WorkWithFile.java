package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    private static final String SUPPLY = "supply";
    private static final String BUY = "buy";
    private static final String RESULT = "result";
    private static final String DELIMITER = ",";

    public void getStatistic(String fromFileName, String toFileName) {
        int[] totals = readFile(fromFileName);
        writeFile(toFileName, totals[0], totals[1]);
    }

    private int[] readFile(String fromFileName) {
        int supply = 0;
        int buy = 0;

        File file = new File(fromFileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] array = line.split(",");
                if (array[0].equals("supply")) {
                    supply += Integer.parseInt(array[1].trim().replace("]",""));
                } else if (array[0].equals("buy")) {
                    buy += Integer.parseInt(array[1].trim().replace("]",""));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file", e);
        }
        return new int[]{supply, buy};
    }

    private void writeFile(String toFileName, int supply, int buy) {
        try (FileWriter writer = new FileWriter(toFileName)) {
            writer.write(SUPPLY + DELIMITER + supply + System.lineSeparator());
            writer.write(BUY + DELIMITER + buy + System.lineSeparator());
            writer.write(RESULT + DELIMITER + (supply - buy) + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't write to file", e);
        }
    }
}
